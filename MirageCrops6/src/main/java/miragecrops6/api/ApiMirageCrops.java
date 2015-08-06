package miragecrops6.api;

import ic2.api.crops.CropCard;

import java.util.Map;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ApiMirageCrops
{

	public static IApiMirageCrops instance;

	public static interface IApiMirageCrops
	{

		public IMapper<CropCard> getMapperCropCard();

		public IMapper<Item> getMapperItem();

		public IMapper<Block> getMapperBlock();

		public IMapper<ItemStack> getMapperItemStack();

	}

	public static interface IMapper<T>
	{

		public T get(String name);

		public Stream<String> getKeys();

		public Stream<? extends T> getValues();

		public Stream<Map.Entry<String, ? extends T>> getEntries();

	}

}
