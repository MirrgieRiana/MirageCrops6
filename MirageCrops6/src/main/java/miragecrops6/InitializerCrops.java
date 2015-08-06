package miragecrops6;

import static miragecrops6.crop.HCropCardMirageCrops.*;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

import miragecrops6.alis.AliCrop;
import miragecrops6.alis.AliItemStack;
import miragecrops6.crop.CropCardMirageCrops;
import miragecrops6.crop.CropCardMirageCrops.CropCardMirageCropsParameter;
import mirrg.minecraft.itemstack.mir60.HItemStack;
import mirrg.struct.hydrogen.Tuple;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

class InitializerCrops
{

	public static void registerCropCards()
	{
		registerSpinaches();
		registerSarracenias();
		registerFlowers();
		registerIndustrials();
		registerCactuses();
		registerWeeds();
		registerOthers();
	}

	private static void registerSpinaches()
	{
		CropCardMirageCrops c;
		String a1 = "Spinach";
		String a2 = "SpinachGroup";

		c = r("spinach", 2, 4, s(0, 4, 0, 1, 0), a(a1, a2, "Food", "Edible"));
		setGain(c, cropTile -> AliItemStack.cropSpinach.copy());
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropSpinach, 4));

		c = r("spinachRed", 3, 4, s(2, 2, 0, 3, 0), a(a1, a2, "Red", "Undead", "Blood"));
		setGain(c, cropTile -> AliItemStack.cropSpinachRed.copy());
		setTextureRespect(c, max(3), AliCrop.spinach);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropSpinachRed, 4));

		c = r("spinachBlue", 5, 4, s(4, 1, 0, 3, 0), a(a1, a2, "Blue", "Magic"));
		setGain(c, cropTile -> AliItemStack.cropSpinachBlue.copy());
		setTextureRespect(c, max(3), AliCrop.spinach);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropSpinachBlue, 4));

		c = r("spinachPoison", 8, 4, s(6, 0, 4, 1, 0), a(a1, a2, "Purple", "Poizon", "Toxic"));
		setGain(c, cropTile -> AliItemStack.cropSpinachPoison.copy());
		setTextureRespect(c, max(3), AliCrop.spinach);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropSpinachPoison, 4));

		c = r("spinachFire", 10, 4, s(2, 0, 6, 1, 0), a(a1, a2, "Orange", "Fire", "Flame"));
		setGain(c, cropTile -> AliItemStack.cropSpinachFire.copy());
		setTextureRespect(c, max(3), AliCrop.spinach);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropSpinachFire, 4));

		c = r("spinachIce", 12, 4, s(1, 1, 1, 1, 0), a(a1, a2, "White", "Blue", "Ice"));
		setGain(c, cropTile -> AliItemStack.cropSpinachIce.copy());
		setTextureRespect(c, max(3), AliCrop.spinach);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropSpinachIce, 4));

	}

	private static void registerSarracenias()
	{
		CropCardMirageCrops c;
		String a1 = "Sarracenia";
		String a2 = "SarraceniaGroup";

		c = r("sarracenia", 3, 5, s(0, 0, 4, 1, 2), a(a1, a2, "Green"));
		setGain(c, cropTile -> AliItemStack.cropSarracenia.copy());
		setGain(c, array(4), cropTile -> AliItemStack.cropSarraceniaImmature.copy());
		setEmittedLight(c, array(5), 0);
		addCropComponentSarracenia(c, array(4, 5), COLLISION, 2,
			entityLiving -> entityLiving.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD);

		c = r("sarraceniaLightning", 6, 5, s(4, 0, 5, 2, 1), a(a1, a2, "Purple", "Lightning"));
		setGain(c, cropTile -> AliItemStack.cropSarraceniaLightning.copy());
		setGain(c, array(4), cropTile -> AliItemStack.cropSarraceniaImmature.copy());
		setTextureRespect(c, max(4), AliCrop.sarracenia);
		setEmittedLight(c, array(5), 12);
		addCropComponentSarracenia(c, array(4, 5), COLLISION, 5,
			entityLiving -> entityLiving.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD);

		c = r("sarraceniaNagae", 10, 5, s(0, 4, 7, 5, 0), a(a1, a2, "Fish", "Food", "Edible", "Lightning", "Cloth"));
		setGain(c, cropTile -> {
			if (cropTile.getWorld().rand.nextInt(5) == 0) {
				return new ItemStack(Items.fish);
			} else {
				return AliItemStack.cropSarraceniaNagae.copy();
			}
		});
		setGain(c, array(4), cropTile -> AliItemStack.cropSarraceniaImmature.copy());
		setTextureRespect(c, max(4), AliCrop.sarracenia);
		setEmittedLight(c, array(5), 8);
		setTickerNagae(c, array(4));

		c = r("sarraceniaManeater", 8, 5, s(0, 0, 6, 3, 1), a(a1, a2, "Pink", "Red", "Blood", "Undead"));
		setGain(c, cropTile -> AliItemStack.cropSarraceniaManeater.copy());
		setGain(c, array(4), cropTile -> AliItemStack.cropSarraceniaImmature.copy());
		setTextureRespect(c, max(4), AliCrop.sarracenia);
		setEmittedLight(c, array(5), 2);
		addCropComponentSarracenia(c, array(4, 5), COLLISION, 3, entityLiving -> entityLiving instanceof EntityZombie);
		addCropComponentSarracenia(c, array(4, 5), COLLISION, 3, entityLiving -> entityLiving instanceof EntityVillager);
		addCropComponentSarracenia(c, array(4, 5), COLLISION, 1, entityLiving -> entityLiving instanceof EntityPlayerMP);

		c = r("sarraceniaDevil", 14, 5, s(0, 0, 8, 3, 0), a(a1, a2, "Red", "Redstone", "Undead", "Blood"));
		setGain(c, cropTile -> AliItemStack.cropSarraceniaDevil.copy());
		setGain(c, array(4), cropTile -> AliItemStack.cropSarraceniaImmature.copy());
		setTextureRespect(c, max(4), AliCrop.sarracenia);
		setEmittedLight(c, array(5), 4);
		addCropComponentSarracenia(c, array(4, 5), COLLISION, 3,
			entityLiving -> entityLiving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD);

	}

	private static void registerFlowers()
	{
		CropCardMirageCrops c;
		String a1 = "Chrysanthum";
		String a2 = "ChrysanthumGroup";
		String a3 = "Flower";
		String a4 = "White";

		c = r("roseQuartz", 6, 4, s(1, 0, 3, 4, 0), a("Flower", "Purple", "Rose", "Quartz", "Silica", "Crystal"));
		setGain(c, cropTile -> w()
			.entry(95, () -> AliItemStack.cropRoseQuartz.copy())
			.entry(1, () -> AliItemStack.gemCertusQuartz.copy())
			.entry(4, () -> new ItemStack(Items.quartz))
			.get(cropTile.getWorld().rand).get());

		c = r("chrysanthum", 2, 4, s(1, 1, 0, 5, 1), a(a1, a2, a3, a4));
		setGain(c, cropTile -> new ItemStack(Blocks.red_flower, 1, 8));

		c = r("chrysanthumErodium", 8, 4, s(3, 1, 0, 5, 1), a(a1, a2, a3, a4));
		setGain(c, cropTile -> w()
			.entry(50, () -> new ItemStack(Blocks.red_flower, 1, 8))
			.entry(50, () -> null)
			.get(cropTile.getWorld().rand).get());
		setTextureRespect(c, max(4), AliCrop.chrysanthum);

		c = r("chrysanthumIridium", 17, 4, s(6, 0, 0, 5, 0), a(a1, a2, a3, a4, "Iridium", "Metal"));
		setGain(c, cropTile -> w()
			.entry(99, () -> new ItemStack(Blocks.red_flower, 1, 8))
			.entry(1, () -> null)
			.get(cropTile.getWorld().rand).get());
	}

	private static void registerIndustrials()
	{
		CropCardMirageCrops c;
		String a1 = "Industrial";

		c = r("reedCircuit", 14, 4, s(1001, 0, 0, 0, 0), a(a1, "Metal", "Circuit", "Reed"));
		setGain(c, cropTile -> AliItemStack.cropReedCircuit.copy());
		setGain(c, range(2, 3), cropTile -> copy(AliItemStack.cropReedWire, cropTile.getSize() - 1));
		setSizeAfterHarvest(c, array(4), cropTile -> rBw(cropTile, 1, 3));

		c = r("berriesMatter", 9, 4, s(1002, 0, 0, 6, 0), a(a1, "Matter", "Purple"));
		setGain(c, cropTile -> w()
			.entry(100000, () -> new ItemStack(Items.stick))

			.entry(10000, () -> new ItemStack(Blocks.stone))
			.entry(10000, () -> new ItemStack(Blocks.dirt))
			.entry(10000, () -> new ItemStack(Blocks.sand))
			.entry(10000, () -> new ItemStack(Blocks.gravel))
			.entry(5000, () -> new ItemStack(Blocks.netherrack))
			.entry(2000, () -> new ItemStack(Blocks.end_stone))
			.entry(500, () -> new ItemStack(Items.iron_ingot))
			.entry(50, () -> new ItemStack(Items.gold_ingot))
			.entry(10, () -> new ItemStack(Items.diamond))
			.entry(500, () -> new ItemStack(Items.quartz))
			.entry(1000, () -> new ItemStack(Items.redstone))
			.entry(300, () -> new ItemStack(Items.glowstone_dust))
			.entry(200, () -> new ItemStack(Items.dye, 1, 4))
			.get(cropTile.getWorld().rand).get());

		c = r("wartGlass", 8, 3, s(1000, 0, 0, 0, 0), a(a1, "Glass", "Silica"));
		setGain(c, cropTile -> w()
			.entry(100, () -> AliItemStack.cropWartGlass.copy())
			.entry(10, () -> AliItemStack.dustGlass.copy())
			.entry(5, () -> new ItemStack(Items.glass_bottle))
			.entry(1, () -> new ItemStack(Blocks.glass))
			.get(cropTile.getWorld().rand).get());

		c = r("wartMatter", 15, 7, s(1003, 0, 0, 6, 0), a(a1, "Matter", "Purple"));
		setGain(c, cropTile -> w()
			.entry(10000, () -> new ItemStack(Items.stick))

			.entry(10000, () -> new ItemStack(Blocks.stone))
			.entry(10000, () -> new ItemStack(Blocks.dirt))
			.entry(10000, () -> new ItemStack(Blocks.sand))
			.entry(10000, () -> new ItemStack(Blocks.gravel))
			.entry(5000, () -> new ItemStack(Blocks.netherrack))
			.entry(2000, () -> new ItemStack(Blocks.end_stone))
			.entry(500, () -> new ItemStack(Items.iron_ingot))
			.entry(50, () -> new ItemStack(Items.gold_ingot))
			.entry(10, () -> new ItemStack(Items.diamond))
			.entry(500, () -> new ItemStack(Items.quartz))
			.entry(1000, () -> new ItemStack(Items.redstone))
			.entry(300, () -> new ItemStack(Items.glowstone_dust))
			.entry(200, () -> new ItemStack(Items.dye, 1, 4))
			.get(cropTile.getWorld().rand).get());
	}

	private static void registerCactuses()
	{
		CropCardMirageCrops c;
		String a1 = "Cactus";
		String a2 = "CactusGroup";

		c = r("cactus", 2, 4, s(1, 0, 4, 4, 1), a(a1, a2, "Green"));
		setGain(c, cropTile -> AliItemStack.cropCactus.copy());
		addCropComponentCactus(c, array(4), COLLISION, 1.0f);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropCactus, 1));

		c = r("cactusObsidian", 7, 4, s(3, 0, 6, 3, 0), a(a1, a2, "Purple", "Black", "Obsidian", "Glass", "Fire"));
		setGain(c, cropTile -> AliItemStack.cropCactusObsidian.copy());
		setTextureRespect(c, max(3), AliCrop.cactus);
		addCropComponentCactus(c, array(4), COLLISION, 2.0f);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropCactusObsidian, 1));

		c = r("cactusSnow", 6, 5, s(2, 0, 5, 2, 0), a(a1, a2, "White", "Ice", "Snow"));
		setGain(c, cropTile -> w()
			.entry(100, () -> new ItemStack(Items.snowball))
			.entry(10, () -> new ItemStack(Blocks.ice))
			.entry(5, () -> new ItemStack(Blocks.snow))
			.entry(1, () -> new ItemStack(Blocks.snow_layer))
			.get(cropTile.getWorld().rand).get());
		setGain(c, array(4), cropTile -> AliItemStack.cropCactusSnow.copy());
		setSizeAfterHarvest(c, array(5), 4);
		setTextureRespect(c, max(3), AliCrop.cactus);
		addCropComponentCactus(c, array(4, 5), COLLISION | CLICK, 1.0f);
		addCropComponentSlow(c, array(5), COLLISION);
		registerBaseSeed(c, () -> HItemStack.copy(AliItemStack.cropCactusSnow, 1));
	}

	private static void registerWeeds()
	{
		CropCardMirageCrops c;
		String a5 = "Green";

		{
			String a1 = "Fern";
			String a2 = "FernGroup";

			c = r("fern", 1, 4, s(0, 0, 0, 1, 4), a(a1, a2, a5, "Weed"));
			setGain(c, cropTile -> new ItemStack(Blocks.tallgrass, 1, 2));
			registerBaseSeed(c, () -> new ItemStack(Blocks.tallgrass, 1, 2));

			c = r("fernHoney", 5, 5, s(0, 3, 0, 1, 2), a(a1, a2, a5, "Bee", "Honey", "Edible"));
			setGain(c, cropTile -> null);
			setTextureRespect(c, max(4), AliCrop.fern);
		}

		{
			String a3 = "Vine";
			String a4 = "VineGroup";

			c = r("vine", 1, 4, s(0, 0, 1, 0, 4), a(a3, a4, a5, "Weed"));
			setGain(c, cropTile -> new ItemStack(Blocks.vine));
			registerBaseSeed(c, () -> new ItemStack(Blocks.vine));

			c = r("vineApatite", 6, 4, s(3, 0, 0, 2, 1), a(a3, a4, a5, "Blue", "Apatite", "Crystal", "Phosphorus"));
			setGain(c, cropTile -> w()
				.entry(1, () -> AliItemStack.gemApatite.copy())
				.get(cropTile.getWorld().rand).get());
			setSizeAfterHarvest(c, array(4), 3);
			setGrowthLightRegion(c, array(3), 15, 15);
			setGrowthDurationRatio(c, array(3), 5);
			setTextureRespect(c, max(3), AliCrop.vine);

			c = r("vineFluoroberries", 9, 4, s(6, 0, 4, 3, 1), a(a3, a4, "Yellow", "Fluorine", "Berry"));
			setGain(c, cropTile -> copy(AliItemStack.cropVineFluoroberries, rBw(cropTile, 1, 10)));
		}
	}

	private static void registerOthers()
	{
		CropCardMirageCrops c;

		c = r("wheatFire", 5, 9, s(4, 1, 3, 1, 0), a("Wheat", "Orange", "Yellow", "Fire", "Flame"));
		setGain(c, array(9), cropTile -> w()
			.entry(1, () -> new ItemStack(Items.dye, 1, 15))
			.get(cropTile.getWorld().rand).get());
		setGain(c, array(8), cropTile -> w()
			.entry(1, () -> new ItemStack(Blocks.torch))
			.get(cropTile.getWorld().rand).get());
		setGain(c, array(7), cropTile -> w()
			.entry(1, () -> w()
				.entry(1, () -> new ItemStack(Items.fire_charge))
				.entry(1, () -> AliItemStack.gemSulfur.copy())
				.get(cropTile.getWorld().rand).get())
			.get(cropTile.getWorld().rand).get());
		setEmittedLight(c, array(8), 14);
		setGrowthDurationRatio(c, array(8), 0.15);
		addCropComponentFire(c, array(8), COLLISION | CLICK, 8);

		c = r("rice", 3, 7, s(0, 5, 0, 0, 0), a("Wheat", "Food", "Yellow", "Edible", "Rice"));
		setGain(c, cropTile -> null);

		c = r("coffeeJava", 6, 4, s(1, 4, 0, 1, 0), a("Coffee", "Leaves", "Java"));
		setGain(c, cropTile -> null);

		c = r("mandrake", 5, 4, s(5, 3, 5, 2, 3), a("Mandrake"));
		setGain(c, cropTile -> AliItemStack.cropMandrake.copy());

		c = r("dreamflower", 7, 4, s(0, 0, 0, 3, 0), a("Dream", "Flower", "Mirage"));
		setGain(c, cropTile -> null);
		setEmittedLight(c, array(4), 8);
	}

	private static class Weighter<T>
	{

		private double sumWeight;
		private ArrayList<Tuple<Double, T>> entries = new ArrayList<>();

		private Weighter<T> entry(double weight, T t)
		{
			sumWeight += weight;
			entries.add(new Tuple<>(weight, t));
			return this;
		}

		private T get(Random random)
		{
			double i = sumWeight * random.nextDouble();

			for (Tuple<Double, T> entry : entries) {
				if (i < entry.getKey()) {
					return entry.getValue();
				} else {
					i -= entry.getKey();
				}
			}

			return entries.get(entries.size() - 1).getY();
		}

	}

	private static Weighter<Supplier<ItemStack>> w()
	{
		return new Weighter<>();
	}

	static ItemStack copy(ItemStack itemStack, int stackSize)
	{
		ItemStack tmp = itemStack.copy();
		tmp.stackSize = stackSize;
		return tmp;
	}

	private static String[] a(String... arguments)
	{
		return arguments;
	}

	private static int[] s(
		int statChemical, int statFood, int statDefensive, int statColor, int statWeed)
	{
		return new int[] {
			statChemical, statFood, statDefensive, statColor, statWeed,
		};
	}

	private static <T extends CropCard> T registerCrop(T crop)
	{
		Crops crops = Crops.instance;
		if (crops != null) crops.registerCrop(crop);

		try {
			AliCrop.class.getField(crop.name()).set(null, crop);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}

		return crop;
	}

	private static CropCardMirageCrops r(
		String name, int tier, int maxSize, int[] stats, String[] atributes)
	{
		return registerCrop(new CropCardMirageCrops(new CropCardMirageCropsParameter(
			name, tier, maxSize, stats, atributes)));
	}

	private static int rBw(ICropTile cropTile, int min, int max)
	{
		return cropTile.getWorld().rand.nextInt(max - min + 1) + min;
	}

	private static void registerBaseSeed(CropCardMirageCrops cropCardMirageCrops, Supplier<ItemStack> itemStack)
	{
		Crops.instance.registerBaseSeed(itemStack.get(), cropCardMirageCrops, 1, 0, 0, 0);
	}

}
