package miragecrops6.crop;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class HCropCardMirageCrops
{

	public static void setGain(CropCardMirageCrops cropCard, Function<ICropTile, ItemStack> function)
	{
		setGain(cropCard, array(cropCard.maxSize()), function);
	}

	//////////////////////////// sized //////////////////////////

	public static void setTextureRespect(CropCardMirageCrops cropCard, int[] sizes,
		CropCardMirageCrops target)
	{
		IntFunction<String> old = cropCard.lambda_getSpriteName;
		cropCard.lambda_getSpriteName = size -> {
			if (contains(sizes, size)) {
				return target.getSpriteName(size);
			}
			return old.apply(size);
		};
	}

	public static void setSizeAfterHarvest(CropCardMirageCrops cropCard, int[] sizes,
		int after)
	{
		setSizeAfterHarvest(cropCard, sizes, cropTile -> after);
	}

	public static void setSizeAfterHarvest(CropCardMirageCrops cropCard, int[] sizes,
		ToIntFunction<ICropTile> function)
	{
		{
			ToIntFunction<ICropTile> old = cropCard.lambda_getSizeAfterHarvest;
			cropCard.lambda_getSizeAfterHarvest = cropTile -> {
				if (contains(sizes, cropTile.getSize())) return function.applyAsInt(cropTile);
				return old.applyAsInt(cropTile);
			};
		}
	}

	public static void setGain(CropCardMirageCrops cropCard, int[] sizes,
		Function<ICropTile, ItemStack> function)
	{
		cropCard.lambda_getOptimalHavestSize = cropTile -> {
			return sizes[sizes.length - 1];
		};
		{
			Predicate<ICropTile> old = cropCard.lambda_canBeHarvested;
			cropCard.lambda_canBeHarvested = cropTile -> {
				if (contains(sizes, cropTile.getSize())) return true;
				return old.test(cropTile);
			};
		}
		{
			Function<ICropTile, ItemStack> old = cropCard.lambda_getGain;
			cropCard.lambda_getGain = cropTile -> {
				if (contains(sizes, cropTile.getSize())) return function.apply(cropTile);
				return old.apply(cropTile);
			};
		}
	}

	public static void setGrowthDurationRatio(CropCardMirageCrops cropCard, int[] sizes,
		double ratio)
	{
		{
			ToIntFunction<ICropTile> old = cropCard.lambda_growthDuration;
			cropCard.lambda_growthDuration = cropTile -> {
				if (contains(sizes, cropTile.getSize())) return (int) (old.applyAsInt(cropTile) * ratio);
				return old.applyAsInt(cropTile);
			};
		}
	}

	public static void setEmittedLight(CropCardMirageCrops cropCard, int[] sizes,
		int emittedLight)
	{
		{
			ToIntFunction<ICropTile> old = cropCard.lambda_getEmittedLight;
			cropCard.lambda_getEmittedLight = cropTile -> {
				if (contains(sizes, cropTile.getSize())) return emittedLight;
				return old.applyAsInt(cropTile);
			};
		}
	}

	public static void setGrowthLightRegion(CropCardMirageCrops cropCard, int[] sizes,
		int min, int max)
	{
		{
			Predicate<ICropTile> old = cropCard.lambda_canGrow;
			cropCard.lambda_canGrow = cropTile -> {
				if (contains(sizes, cropTile.getSize())) {
					if (cropTile.getLightLevel() < min) return false;
					if (cropTile.getLightLevel() > max) return false;
				}
				return old.test(cropTile);
			};
		}
	}

	public static void setTickerNagae(CropCardMirageCrops cropCard, int[] sizes)
	{
		{
			Consumer<ICropTile> old = cropCard.lambda_tick;
			cropCard.lambda_tick = cropTile -> {
				if (contains(sizes, cropTile.getSize())) {
					World world = cropTile.getWorld();

					//if (!world.isRemote) {
					if (world.isRaining() || world.isThundering()) {
						if (world.canBlockSeeTheSky(
							cropTile.getLocation().posX,
							cropTile.getLocation().posY + 1,
							cropTile.getLocation().posZ)) {

							EntityLightningBolt entity = new EntityLightningBolt(world,
								cropTile.getLocation().posX,
								cropTile.getLocation().posY + 1,
								cropTile.getLocation().posZ);

							world.addWeatherEffect(entity);

							cropTile.setNutrientStorage(Math.min(100, cropTile.getNutrientStorage() + 100));
							cropTile.setSize((byte) (cropTile.getSize() + 1));

							cropTile.getWorld().playAuxSFX(2005,
								cropTile.getLocation().posX,
								cropTile.getLocation().posY,
								cropTile.getLocation().posZ, 0);

						}
					}
					//}

					old.accept(cropTile);
				}
			};
		}
	}

	////////////////////////////// collision left/right-click events /////////////////////////////

	public static final int COLLISION = 0x01;
	public static final int LEFT_CLICK = 0x02;
	public static final int RIGHT_CLICK = 0x04;
	public static final int CLICK = 0x06;

	public static void addCropComponent(
		CropCardMirageCrops cropCard, int size, int timing, BiConsumer<ICropTile, EntityLivingBase> comsumer)
	{
		addCropComponent(cropCard, new int[] {
			size
		}, timing, comsumer);
	}

	public static void addCropComponent(
		CropCardMirageCrops cropCard, int[] sizes, int timing, BiConsumer<ICropTile, EntityLivingBase> comsumer)
	{
		if ((timing & COLLISION) != 0) {
			BiConsumer<ICropTile, EntityLivingBase> old = cropCard.lambda_onCollisionEntityLiving;
			cropCard.lambda_onCollisionEntityLiving = (cropTile, entityLiving) -> {
				if (contains(sizes, cropTile.getSize())) {
					comsumer.accept(cropTile, entityLiving);
				}

				old.accept(cropTile, entityLiving);
			};
		}
		if ((timing & LEFT_CLICK) != 0) {
			BiConsumer<ICropTile, EntityPlayer> old = cropCard.lambda_onLeftClick;
			cropCard.lambda_onLeftClick = (cropTile, player) -> {
				if (contains(sizes, cropTile.getSize())) {
					comsumer.accept(cropTile, player);
				}

				old.accept(cropTile, player);
			};
		}
		if ((timing & RIGHT_CLICK) != 0) {
			BiConsumer<ICropTile, EntityPlayer> old = cropCard.lambda_onRightClick;
			cropCard.lambda_onRightClick = (cropTile, player) -> {
				if (contains(sizes, cropTile.getSize())) {
					comsumer.accept(cropTile, player);
				}

				old.accept(cropTile, player);
			};
		}
	}

	private static boolean contains(int[] sizes, int size)
	{
		for (int size2 : sizes) {
			if (size2 == size) return true;
		}
		return false;
	}

	public static void addCropComponentCactus(CropCardMirageCrops cropCard, int[] sizes, int timing,
		float damage)
	{
		addCropComponent(cropCard, sizes, timing, (cropTile, entityLiving) -> {
			entityLiving.attackEntityFrom(DamageSource.cactus, damage);
		});
	}

	public static void addCropComponentSlow(CropCardMirageCrops cropCard, int[] sizes, int timing)
	{
		addCropComponent(cropCard, sizes, timing, (cropTile, entityLiving) -> {
			entityLiving.addPotionEffect(new PotionEffect(2, 6, 3, true));
		});
	}

	public static void addCropComponentFire(CropCardMirageCrops cropCard, int[] sizes, int timing,
		int duration)
	{
		addCropComponent(cropCard, sizes, timing, (cropTile, entityLiving) -> {
			entityLiving.setFire(duration);
		});
	}

	protected static final DamageSource DAMAGESOURCE = new DamageSource("Sarracenia");

	public static void addCropComponentSarracenia(CropCardMirageCrops cropCard, int[] sizes, int timing,
		float damage, Predicate<EntityLivingBase> predicateEntityLiving)
	{
		addCropComponent(cropCard, sizes, timing, (cropTile, entityLiving) -> {
			if (cropTile.getNutrientStorage() < 20) {
				if (predicateEntityLiving.test(entityLiving)) {
					entityLiving.attackEntityFrom(DAMAGESOURCE, damage);
					if (entityLiving.isDead) {
						cropTile.setNutrientStorage(
							Math.min(100, cropTile.getNutrientStorage() +
								(int) (10 * entityLiving.getMaxHealth())));
						cropTile.getWorld().playAuxSFX(2005,
							cropTile.getLocation().posX,
							cropTile.getLocation().posY,
							cropTile.getLocation().posZ, 0);
					}
				}
			}
		});
	}

	//////////////////////////////////////// helpers ///////////////////////////////////////

	public static int[] array(int... integers)
	{
		return integers;
	}

	public static int[] max(int max)
	{
		return range(1, max);
	}

	public static int[] min(CropCard cropCard, int min)
	{
		return range(min, cropCard.maxSize());
	}

	public static int[] range(int min, int max)
	{
		int[] tmp = new int[max - min + 1];
		for (int i = min; i <= max; i++) {
			tmp[i - min] = i;
		}
		return tmp;
	}

}
