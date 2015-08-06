package mirrg.minecraft.nbt.mir60;

import java.util.Optional;
import java.util.function.BiFunction;

import net.minecraft.nbt.NBTTagCompound;

/**
 * sample: getInteger(nbt, "directory/property")
 */
public class HNbt
{

	public static NbtType<Void> END = new NbtType<>(0, null);
	public static NbtType<Byte> BYTE = new NbtType<>(1, NBTTagCompound::getByte); // primitive
	public static NbtType<Short> SHORT = new NbtType<>(2, NBTTagCompound::getShort); // primitive
	public static NbtType<Integer> INT = new NbtType<>(3, NBTTagCompound::getInteger); // primitive
	public static NbtType<Long> LONG = new NbtType<>(4, NBTTagCompound::getLong); // primitive
	public static NbtType<Float> FLOAT = new NbtType<>(5, NBTTagCompound::getFloat); // primitive
	public static NbtType<Double> DOUBLE = new NbtType<>(6, NBTTagCompound::getDouble); // primitive
	public static NbtType<byte[]> BYTE_ARRAY = new NbtType<>(7, NBTTagCompound::getByteArray); // array
	public static NbtType<String> STRING = new NbtType<>(8, NBTTagCompound::getString); // object
	public static NbtType<Void> LIST = new NbtType<>(9, null);
	public static NbtType<NBTTagCompound> COMPOUND = new NbtType<>(10, NBTTagCompound::getCompoundTag); // object
	public static NbtType<int[]> INT_ARRAY = new NbtType<>(11, NBTTagCompound::getIntArray); // array

	public static class NbtType<T>
	{

		public int number;
		public BiFunction<NBTTagCompound, String, T> getter;

		private NbtType(int number, BiFunction<NBTTagCompound, String, T> getter)
		{
			this.number = number;
			this.getter = getter;
		}

	}

	public static <T> Optional<T> getItem(NBTTagCompound nbt, String path, NbtType<T> type)
	{
		if (nbt == null) return Optional.empty();

		String[] dirs = path.split("/");

		for (int i = 0; i < dirs.length - 1; i++) {
			if (nbt.hasKey(dirs[i], COMPOUND.number)) {
				nbt = nbt.getCompoundTag(dirs[i]);
			} else {
				return Optional.empty();
			}
		}

		if (nbt.hasKey(dirs[dirs.length - 1], type.number)) {
			return Optional.of(type.getter.apply(nbt, dirs[dirs.length - 1]));
		} else {
			return Optional.empty();
		}
	}

	public static Optional<Integer> getInteger(NBTTagCompound nbt, String path)
	{
		return getItem(nbt, path, INT);
	}

	public static Optional<String> getString(NBTTagCompound nbt, String path)
	{
		return getItem(nbt, path, STRING);
	}

}
