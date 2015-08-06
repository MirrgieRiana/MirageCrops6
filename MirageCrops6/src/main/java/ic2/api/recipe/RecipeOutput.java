package ic2.api.recipe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class RecipeOutput
{
  public final List<ItemStack> items;
  public final NBTTagCompound metadata;
  
  public RecipeOutput(NBTTagCompound metadata1, List<ItemStack> items1)
  {
    assert (!items1.contains(null));
    
    this.metadata = metadata1;
    this.items = items1;
  }
  
  public RecipeOutput(NBTTagCompound metadata1, ItemStack... items1)
  {
    this(metadata1, Arrays.asList(items1));
  }
  
  public boolean equals(Object obj)
  {
    if ((obj instanceof RecipeOutput))
    {
      RecipeOutput ro = (RecipeOutput)obj;
      if ((this.items.size() == ro.items.size()) && (((this.metadata == null) && (ro.metadata == null)) || ((this.metadata != null) && (ro.metadata != null) && (this.metadata.equals(ro.metadata)))))
      {
        Iterator<ItemStack> itA = this.items.iterator();
        Iterator<ItemStack> itB = ro.items.iterator();
        while ((itA.hasNext()) && (itB.hasNext()))
        {
          ItemStack stackA = (ItemStack)itA.next();
          ItemStack stackB = (ItemStack)itB.next();
          if (ItemStack.areItemStacksEqual(stackA, stackB)) {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }
  
  public String toString()
  {
    return "ROutput<" + this.items + "," + this.metadata + ">";
  }
}
