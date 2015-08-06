package ic2.api.recipe;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RecipeInputItemStack
  implements IRecipeInput
{
  public final ItemStack input;
  public final int amount;
  
  public RecipeInputItemStack(ItemStack aInput)
  {
    this(aInput, aInput.stackSize);
  }
  
  public RecipeInputItemStack(ItemStack aInput, int aAmount)
  {
    if (aInput.getItem() == null) {
      throw new IllegalArgumentException("Invalid item stack specfied");
    }
    this.input = aInput.copy();
    this.amount = aAmount;
  }
  
  public boolean matches(ItemStack subject)
  {
    return (subject.getItem() == this.input.getItem()) && ((subject.getItemDamage() == this.input.getItemDamage()) || (this.input.getItemDamage() == 32767));
  }
  
  public int getAmount()
  {
    return this.amount;
  }
  
  public List<ItemStack> getInputs()
  {
    return Arrays.asList(new ItemStack[] { this.input });
  }
  
  public String toString()
  {
    ItemStack stack = this.input.copy();
    this.input.stackSize = this.amount;
    return "RInputItemStack<" + stack + ">";
  }
}
