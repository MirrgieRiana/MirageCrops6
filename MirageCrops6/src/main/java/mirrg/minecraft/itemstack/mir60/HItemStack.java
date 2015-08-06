package mirrg.minecraft.itemstack.mir60;

import net.minecraft.item.ItemStack;

public class HItemStack
{

	public static ItemStack copy(ItemStack src, int count)
	{
		ItemStack dest = src.copy();
		dest.stackSize = count;
		return dest;
	}

}
