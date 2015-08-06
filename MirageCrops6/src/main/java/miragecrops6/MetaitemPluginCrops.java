package miragecrops6;

import java.util.List;

import mirrg.minecraft.item.multi.copper.IMetaitem;
import mirrg.minecraft.item.multi.copper.Metaitem;
import mirrg.minecraft.nbt.mir60.HNbt;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

class MetaitemPluginCrops extends Metaitem
{

	public static class Arguments
	{

		private final String textureName;
		private final int color;
		private final String name;

		public Arguments(String textureName, int color, String name)
		{
			this.textureName = textureName;
			this.color = color;
			this.name = name;
		}

	}

	private Arguments arguments;

	public MetaitemPluginCrops(IMetaitem _super, int metaid, Arguments arguments)
	{
		super(_super, metaid);
		this.arguments = arguments;
	}

	private IIcon icon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		icon = iconRegister.registerIcon(arguments.textureName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return arguments.color;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean isShift)
	{
		String key = "item." + arguments.name + ".information";

		if (StatCollector.canTranslate(key)) {
			strings.add(StatCollector.translateToLocal(key));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "item." + arguments.name;
	}

	protected static int getNBTInteger(ItemStack itemStack, String path, int _default)
	{
		return HNbt.getInteger(itemStack.getTagCompound(), path).orElse(_default);
	}

	protected static String getNBTString(ItemStack itemStack, String path, String _default)
	{
		return HNbt.getString(itemStack.getTagCompound(), path).orElse(_default);
	}

}
