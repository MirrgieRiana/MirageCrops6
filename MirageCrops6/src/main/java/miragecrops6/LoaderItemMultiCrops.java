package miragecrops6;

import java.util.function.Supplier;

import miragecrops6.MetaitemPluginCrops.Arguments;
import miragecrops6.alis.AliItem;
import miragecrops6.alis.AliItemStack;
import mirrg.minecraft.item.multi.copper.ItemMulti;
import mirrg.minecraft.item.multi.copper.Metaitem;
import mirrg.minecraft.itemstack.mir60.HItemStack;
import mirrg.minecraft.itemstack.mir60.HOreDictionary;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

@SuppressWarnings("static-method")
class LoaderItemMultiCrops
{

	private ModMirageCrops6 mod;

	public LoaderItemMultiCrops(ModMirageCrops6 mod)
	{
		this.mod = mod;
	}

	@SuppressWarnings("unused")
	public ItemMulti loadItemMultiCrops()
	{
		ItemMulti multiCrops = new ItemMulti();
		multiCrops.setCreativeTab(ModMirageCrops6.creativeTab);

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
				mod.onPostInit.add(() -> {
					HOreDictionary.findFirst("circuitBasic", ore -> {
						GameRegistry.addRecipe(new ShapelessOreRecipe(
							ore,
							AliItemStack.cropReedCircuit));
					});
				});

				addMetaitem(multiCrops, i++, "cropReedWire", true);
				mod.onPostInit.add(() -> {
					HOreDictionary.findFirst("craftingWireCopper", ore -> {
						GameRegistry.addRecipe(new ShapelessOreRecipe(
							ore,
							AliItemStack.cropReedWire));
					});
				});

			}
			{
				int partition = 1;
				int i = partition * PARTITION_SIZE + domain * DOMAIN_SIZE;

				addMetaitem(multiCrops, i++, "cropWartGlass", false);
				mod.onPostInit.add(() -> {
					GameRegistry.addSmelting(
						HItemStack.copy(AliItemStack.cropWartGlass, 4),
						new ItemStack(Blocks.glass), 0);
				});

				addMetaitem(multiCrops, i++, "dustGlass", true);
				mod.onPostInit.add(() -> {
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
				mod.onPostInit.add(() -> {
					GameRegistry.addRecipe(new ShapelessOreRecipe(
						Blocks.cactus,
						AliItemStack.cropCactus));
				});

				addMetaitem(multiCrops, i++, "cropCactusObsidian", false);
				mod.onPostInit.add(() -> {
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
				mod.onPostInit.add(() -> {
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

	private final static int PARTITION_SIZE = 32;
	private final static int DOMAIN_SIZE = 1024;

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
			new Arguments(ModMirageCrops6.DOMAIN + ":crop/" + name, 0xffffff, name));
	}

	private void schedule(String name, Supplier<ItemStack> supplier)
	{
		mod.onInit.add(() -> {
			try {
				AliItemStack.class.getField(name).set(null, supplier.get());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}
