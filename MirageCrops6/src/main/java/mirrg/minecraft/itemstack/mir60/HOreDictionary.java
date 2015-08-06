package mirrg.minecraft.itemstack.mir60;

import java.util.ArrayList;
import java.util.function.Consumer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;

public class HOreDictionary
{

	public static Optional<ItemStack> get(String name)
	{
		ArrayList<ItemStack> ores = OreDictionary.getOres(name);
		if (ores.size() >= 1) return Optional.of(ores.get(0));
		return Optional.absent();
	}

	public static void findFirst(String name, Consumer<ItemStack> consumer)
	{
		ArrayList<ItemStack> ores = OreDictionary.getOres(name);
		if (ores.size() >= 1) consumer.accept(ores.get(0));
	}

}
