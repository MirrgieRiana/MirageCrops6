package plugincrops.api.client;

import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract interface IHandlerRendering
{
  public abstract Boolean renderBlock(IIcon paramIIcon, int paramInt1, int paramInt2, int paramInt3);
}
