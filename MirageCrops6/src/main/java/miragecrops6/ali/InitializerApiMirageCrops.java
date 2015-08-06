package miragecrops6.ali;

import ic2.api.crops.CropCard;
import miragecrops6.alis.AliCrop;
import miragecrops6.alis.AliItem;
import miragecrops6.alis.AliItemStack;
import miragecrops6.api.ApiMirageCrops;
import miragecrops6.api.ApiMirageCrops.IApiMirageCrops;
import miragecrops6.api.ApiMirageCrops.IMapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InitializerApiMirageCrops
{

	public static void init()
	{
		ApiMirageCrops.instance = new IApiMirageCrops() {

			private IMapper<CropCard> mapperCropCard = new MapperFromAliClass<>(AliCrop.class);
			private IMapper<Item> mapperItem = new MapperFromAliClass<>(AliItem.class);
			private IMapper<Block> mapperBlock = new MapperFromAliClass<>(AliItemStack.class);
			private IMapper<ItemStack> mapperItemStack = null;

			@Override
			public IMapper<CropCard> getMapperCropCard()
			{
				return mapperCropCard;
			}

			@Override
			public IMapper<Item> getMapperItem()
			{
				return mapperItem;
			}

			@Override
			public IMapper<Block> getMapperBlock()
			{
				return mapperBlock;
			}

			@Override
			public IMapper<ItemStack> getMapperItemStack()
			{
				return mapperItemStack;
			}

		};
	}

}
