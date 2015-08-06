package miragecrops6.crop;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import miragecrops6.ModMirageCrops6;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class CropCardMirageCrops extends CropCard
{

	//////////////////////////// common /////////////////////////////

	private String owner;
	private String name;
	private int tier;
	private String discoveredBy;
	private int maxSize;
	private int[] stats;
	private String[] attributes;

	public CropCardMirageCrops(CropCardMirageCropsParameter parameterObject)
	{
		this.owner = ModMirageCrops6.MODID;
		this.name = parameterObject.name;
		this.tier = parameterObject.tier;
		this.discoveredBy = "Mirrgie Riana";
		this.maxSize = parameterObject.maxSize;
		this.stats = parameterObject.stats;
		this.attributes = parameterObject.atributes;
	}

	public static class CropCardMirageCropsParameter
	{

		public String name;
		public int tier;
		public int maxSize;
		public int[] stats;
		public String[] atributes;

		public CropCardMirageCropsParameter(
			String name,
			int tier,
			int maxSize,
			int[] stats,
			String[] atributes)
		{
			this.name = name;
			this.tier = tier;
			this.maxSize = maxSize;
			this.stats = stats;
			this.atributes = atributes;
		}

	}

	@Override
	public String owner()
	{
		return owner;
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public String displayName()
	{
		return "crop." + owner() + ":" + name() + ".name";
	}

	@Override
	public int tier()
	{
		return tier;
	}

	@Override
	public String discoveredBy()
	{
		return discoveredBy;
	}

	@Override
	public int maxSize()
	{
		return maxSize;
	}

	@Override
	public int stat(int n)
	{
		if (n < 0) return 0;
		if (n >= stats.length) return 0;
		return stats[n];
	}

	@Override
	public String[] attributes()
	{
		return attributes;
	}

	//////////////////////////// icon /////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister)
	{
		textures = new IIcon[maxSize()];

		for (int i = 1; i <= textures.length; i++) {
			textures[i - 1] = iconRegister.registerIcon(getSpriteName(i));
		}
	}

	//

	public IntFunction<String> lambda_getSpriteName = size -> ModMirageCrops6.MODID + ":crop/" + name() + "." + size;

	public String getSpriteName(int size)
	{
		return lambda_getSpriteName.apply(size);
	}

	public Function<ICropTile, IIcon> lambda_getSprite = crop -> super.getSprite(crop);

	@Override
	public IIcon getSprite(ICropTile crop)
	{
		return lambda_getSprite.apply(crop);
	}

	////////////////////////////// grow ///////////////////////////

	public Predicate<ICropTile> lambda_canGrow = crop -> crop.getSize() < maxSize();

	@Override
	public boolean canGrow(ICropTile crop)
	{
		return lambda_canGrow.test(crop);
	}

	public ToIntFunction<ICropTile> lambda_growthDuration = crop -> super.growthDuration(crop);

	@Override
	public int growthDuration(ICropTile crop)
	{
		return lambda_growthDuration.applyAsInt(crop);
	}

	////////////////////////////// harvest ///////////////////////////

	public ToIntFunction<ICropTile> lambda_getOptimalHavestSize = crop -> maxSize() + 1;

	@Override
	public int getOptimalHavestSize(ICropTile crop)
	{
		return lambda_getOptimalHavestSize.applyAsInt(crop);
	}

	public Predicate<ICropTile> lambda_canBeHarvested = crop -> false;

	@Override
	public boolean canBeHarvested(ICropTile crop)
	{
		return lambda_canBeHarvested.test(crop);
	}

	public Function<ICropTile, ItemStack> lambda_getGain = crop -> null;

	@Override
	public ItemStack getGain(ICropTile crop)
	{
		return lambda_getGain.apply(crop);
	}

	public ToIntFunction<ICropTile> lambda_getSizeAfterHarvest = crop -> super.getSizeAfterHarvest(crop);

	@Override
	public byte getSizeAfterHarvest(ICropTile crop)
	{
		return (byte) lambda_getSizeAfterHarvest.applyAsInt(crop);
	}

	///////////////////////////// collision ////////////////////////////

	public BiConsumer<ICropTile, Entity> lambda_onCollisionEntity = (crop, entity) -> {};

	public BiConsumer<ICropTile, EntityLivingBase> lambda_onCollisionEntityLiving = (crop, entityLiving) -> {};

	public BiPredicate<ICropTile, Entity> lambda_isSprintingResister = (crop, entity) -> false;

	@Override
	public boolean onEntityCollision(ICropTile crop, Entity entity)
	{
		lambda_onCollisionEntity.accept(crop, entity);

		if (entity instanceof EntityLivingBase) {
			lambda_onCollisionEntityLiving.accept(crop, (EntityLivingBase) entity);
		}

		if (lambda_isSprintingResister.test(crop, entity)) return false;
		return super.onEntityCollision(crop, entity);
	}

	//

	public BiConsumer<ICropTile, EntityPlayer> lambda_onLeftClick = (crop, player) -> {};

	public BiPredicate<ICropTile, EntityPlayer> lambda_isLeftClickCanceled = (crop, player) -> false;

	@Override
	public boolean leftclick(ICropTile crop, EntityPlayer player)
	{
		lambda_onLeftClick.accept(crop, player);

		if (lambda_isLeftClickCanceled.test(crop, player)) return false;
		return super.leftclick(crop, player);
	}

	public BiConsumer<ICropTile, EntityPlayer> lambda_onRightClick = (crop, player) -> {};

	public BiPredicate<ICropTile, EntityPlayer> lambda_isRightClickCanceled = (crop, player) -> false;

	@Override
	public boolean rightclick(ICropTile crop, EntityPlayer player)
	{
		lambda_onRightClick.accept(crop, player);

		if (lambda_isRightClickCanceled.test(crop, player)) return false;
		return super.rightclick(crop, player);
	}

	public Consumer<ICropTile> lambda_tick = crop -> {};

	@Override
	public void tick(ICropTile crop)
	{
		lambda_tick.accept(crop);
		super.tick(crop);
	}

	///////////////////////////// emission ////////////////////////////

	public ToIntFunction<ICropTile> lambda_emitRedstone = crop -> super.emitRedstone(crop);

	@Override
	public int emitRedstone(ICropTile crop)
	{
		return lambda_emitRedstone.applyAsInt(crop);
	}

	public ToIntFunction<ICropTile> lambda_getEmittedLight = crop -> super.getEmittedLight(crop);

	@Override
	public int getEmittedLight(ICropTile crop)
	{
		return lambda_getEmittedLight.applyAsInt(crop);
	}

}
