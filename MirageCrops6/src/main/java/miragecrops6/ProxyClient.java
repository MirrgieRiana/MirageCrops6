package miragecrops6;

import ic2.api.crops.CropCard;
import plugincrops.api.client.ApiCoreModRendererBlockCrop;
import plugincrops.api.client.IHandlerRendering;
import cpw.mods.fml.common.FMLLog;

public class ProxyClient implements IProxy
{

	@Override
	public void setCrossing(CropCard cropCard)
	{
		try {
			Class.forName("plugincrops.coremod.client.Renderer");

			ModMirageCrops6.onPostInit.add(() -> {
				IHandlerRendering handlerRendering =
					ApiCoreModRendererBlockCrop.registerHandlerRendering.get("crossing");
				if (handlerRendering != null) {
					ApiCoreModRendererBlockCrop.tableCropCardToHandlerRendering.put(cropCard, handlerRendering);
				} else {
					FMLLog.warning("can not find rendering handler: %s (miragecrops6.ProxyClient)", "crossing");
				}
			});

		} catch (ClassNotFoundException e) {}
	}

}
