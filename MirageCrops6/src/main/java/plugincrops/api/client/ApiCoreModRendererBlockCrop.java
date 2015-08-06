package plugincrops.api.client;

import ic2.api.crops.CropCard;

import java.util.ArrayList;
import java.util.Hashtable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ApiCoreModRendererBlockCrop
{
  public static Hashtable<CropCard, IHandlerRendering> tableCropCardToHandlerRendering = new Hashtable();
  public static ArrayList<IHandlerRendering> handlerRenderings = new ArrayList();
  public static IHandlerRendering handlerRenderingDefault;
  public static Hashtable<String, IHandlerRendering> registerHandlerRendering = new Hashtable();
  public static IHandlerRendering handler;
}
