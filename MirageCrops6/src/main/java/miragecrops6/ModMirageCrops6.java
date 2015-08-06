package miragecrops6;

import java.util.ArrayList;

import miragecrops6.ali.InitializerApiMirageCrops;
import miragecrops6.alis.AliItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModMirageCrops6.MODID, version = ModMirageCrops6.VERSION)
public class ModMirageCrops6
{

	public static final String MODID = "MirageCrops6";
	public static final String DOMAIN = MODID.toLowerCase();
	public static final String VERSION = "1.0";

	public static CreativeTabs creativeTab = new CreativeTabs("mirageCrops6") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return AliItem.multiCrops;
		}
	};

	@SidedProxy(clientSide = "miragecrops6.ProxyClient", serverSide = "miragecrops6.ProxyServer")
	public static IProxy proxy;

	public ModMirageCrops6()
	{
		InitializerApiMirageCrops.init();
	}

	@EventHandler
	public void handle(FMLPreInitializationEvent event)
	{

		// アイテム
		AliItem.multiCrops = LoaderItemMultiCrops.loadItemMultiCrops();

		// Crop
		InitializerCrops.registerCropCards();

	}

	public static ArrayList<Runnable> onInit = new ArrayList<>();

	@EventHandler
	public void handle(FMLInitializationEvent event)
	{
		onInit.forEach(Runnable::run);
	}

	public static ArrayList<Runnable> onPostInit = new ArrayList<>();

	@EventHandler
	public void handle(FMLPostInitializationEvent event)
	{
		onPostInit.forEach(Runnable::run);
	}

}
