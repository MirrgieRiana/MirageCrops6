package miragecrops6;

import java.util.ArrayList;
import java.util.function.Supplier;

import miragecrops6.MetaitemPluginCrops.Arguments;
import miragecrops6.ali.InitializerApiMirageCrops;
import miragecrops6.alis.AliItem;
import miragecrops6.alis.AliItemStack;
import mirrg.minecraft.item.multi.copper.ItemMulti;
import mirrg.minecraft.item.multi.copper.Metaitem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SuppressWarnings("static-method")
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

	public ModMirageCrops6()
	{
		InitializerApiMirageCrops.init();
	}

	@EventHandler
	public void handle(FMLPreInitializationEvent event)
	{
		
		
		AliItem.multiCrops = loadItemMultiCrops();

		InitializerCrops.registerCropCards();
	}

	public ArrayList<Runnable> onInit = new ArrayList<>();

	@EventHandler
	public void handle(FMLInitializationEvent event)
	{
		onInit.forEach(Runnable::run);
	}

	public ArrayList<Runnable> onPostInit = new ArrayList<>();

	@EventHandler
	public void handle(FMLPostInitializationEvent event)
	{
		onPostInit.forEach(Runnable::run);
	}

	private final static int PARTITION_SIZE = 32;
	private final static int DOMAIN_SIZE = 1024;

	@SuppressWarnings("unused")
	private ItemMulti loadItemMultiCrops()
	{
		ItemMulti multiCrops = new ItemMulti();
		multiCrops.setCreativeTab(creativeTab);

		{
			int domain = 0;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;
				addMetaitem(multiCrops, i++, "cropSpinach", true);
				addMetaitem(multiCrops, i++, "cropSpinachRed", true);
				addMetaitem(multiCrops, i++, "cropSpinachBlue", true);
				addMetaitem(multiCrops, i++, "cropSpinachPoison", true);
				addMetaitem(multiCrops, i++, "cropSpinachFire", true);
				addMetaitem(multiCrops, i++, "cropSpinachIce", true);
			}
		}
		{
			int domain = 1;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;
				addMetaitem(multiCrops, i++, "cropSarracenia", true);
				addMetaitem(multiCrops, i++, "cropSarraceniaImmature", true);
				addMetaitem(multiCrops, i++, "cropSarraceniaLightning", true);
				addMetaitem(multiCrops, i++, "cropSarraceniaManeater", true);
				addMetaitem(multiCrops, i++, "cropSarraceniaNagae", true);
				addMetaitem(multiCrops, i++, "cropSarraceniaDevil", true);
			}
		}
		{
			int domain = 2;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;
				addMetaitem(multiCrops, i++, "cropRose", true);
				addMetaitem(multiCrops, i++, "cropRoseQuartz", true);
				addMetaitem(multiCrops, i++, "gemCertusQuartz", true);
			}
		}
		{
			int domain = 3;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;

				addMetaitem(multiCrops, i++, "cropReedCircuit", true);
				onInit.add(() -> {
					OreDictionary.registerOre("circuitBasic", AliItemStack.cropReedCircuit);
				});

				addMetaitem(multiCrops, i++, "cropReedWire", true);
				//onInit.add(() -> {
				//	OreDictionary.registerOre("wireCoveredCopper", AliItemStack.cropReedWire);
				//}); // TODO

			}
			{
				int partition = 1;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;

				addMetaitem(multiCrops, i++, "cropWartGlass", false);
				onPostInit.add(() -> {
					GameRegistry.addSmelting(
						InitializerCrops.copy(AliItemStack.cropWartGlass, 4),
						new ItemStack(Blocks.glass), 0);
				});

				addMetaitem(multiCrops, i++, "dustGlass", true);
				onPostInit.add(() -> {
					GameRegistry.addSmelting(AliItemStack.dustGlass, new ItemStack(Blocks.glass), 0);
				});
			}
		}
		{
			int domain = 4;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;

				addMetaitem(multiCrops, i++, "cropCactus", false);
				onPostInit.add(() -> {
					GameRegistry.addRecipe(new ShapelessOreRecipe(
						Blocks.cactus,
						AliItemStack.cropCactus));
				});

				addMetaitem(multiCrops, i++, "cropCactusObsidian", false);
				onPostInit.add(() -> {
					GameRegistry.addRecipe(new ShapedOreRecipe(
						Blocks.obsidian,
						"XXX",
						"XXX",
						"XXX",
						'X', AliItemStack.cropCactusObsidian));
					GameRegistry.addRecipe(new ShapedOreRecipe(
						new ItemStack(Items.stick, 32),
						"X",
						"X",
						'X', AliItemStack.cropCactusObsidian));
				});

				addMetaitem(multiCrops, i++, "cropCactusSnow", false);
				onPostInit.add(() -> {
					GameRegistry.addRecipe(new ShapedOreRecipe(
						Blocks.ice,
						"XX",
						"XX",
						'X', AliItemStack.cropCactusSnow));
					GameRegistry.addRecipe(new ShapedOreRecipe(
						Blocks.packed_ice,
						"XXX",
						"XXX",
						"XXX",
						'X', AliItemStack.cropCactusSnow));
				});

			}
		}
		{
			int domain = 5;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;
			}
			{
				int partition = 1;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;
				addMetaitem(multiCrops, i++, "gemApatite", true);
				addMetaitem(multiCrops, i++, "cropVineFluoroberries", true);
			}
		}
		{
			int domain = 6;
			{
				int partition = 0;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;
				addMetaitem(multiCrops, i++, "cropMandrake", true);
				addMetaitem(multiCrops, i++, "gemSulfur", true);
			}
		}

		GameRegistry.registerItem(multiCrops, "multiCrops");

		return multiCrops;
	}

	private void addMetaitem(ItemMulti itemMulti, int metaid, String name, boolean registerOreDictionary)
	{
		while (metaid >= itemMulti.metaitems.size()) {
			itemMulti.metaitems.add(null);
		}

		if (itemMulti.metaitems.get(metaid) != null) {
			throw new RuntimeException("duplicate registration");
		}

		itemMulti.metaitems.set(metaid, createMetaitem(itemMulti, metaid, name));

		schedule(name, () -> {
			ItemStack itemStack = new ItemStack(AliItem.multiCrops, 1, metaid);

			if (registerOreDictionary) {
				OreDictionary.registerOre(name, itemStack);
			}

			return itemStack;
		});
	}

	private Metaitem createMetaitem(ItemMulti itemMulti, int metaid, String name)
	{
		return new MetaitemPluginCrops(
			itemMulti.getSuper(),
			metaid,
			new Arguments(DOMAIN + ":crop/" + name, 0xffffff, name));
	}

	private void schedule(String name, Supplier<ItemStack> supplier)
	{
		onInit.add(() -> {
			try {
				AliItemStack.class.getField(name).set(null, supplier.get());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}
