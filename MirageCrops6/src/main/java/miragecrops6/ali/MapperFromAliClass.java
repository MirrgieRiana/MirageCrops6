package miragecrops6.ali;

import java.lang.reflect.Field;
import java.util.Map.Entry;
import java.util.stream.Stream;

import miragecrops6.api.ApiMirageCrops.IMapper;
import mirrg.struct.hydrogen.Tuple;

class MapperFromAliClass<T> implements IMapper<T>
{

	private Class<?> clazz;

	public MapperFromAliClass(Class<?> clazz)
	{
		this.clazz = clazz;
	}

	@Override
	public Stream<String> getKeys()
	{
		return Stream.of(clazz.getFields())
			.map(Field::getName);
	}

	@Override
	public Stream<T> getValues()
	{
		return Stream.of(clazz.getFields())
			.map(this::getFromField);
	}

	@Override
	public Stream<Entry<String, ? extends T>> getEntries()
	{
		return Stream.of(clazz.getFields())
			.map(field -> new Tuple<>(field.getName(), getFromField(field)));
	}

	@Override
	public T get(String name)
	{
		return getFromName(clazz, name);
	}

	@SuppressWarnings("unchecked")
	private T getFromField(Field field)
	{
		try {
			return (T) field.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("null")
	private T getFromName(Class<?> clazz, String name)
	{
		try {
			return getFromField(clazz.getField(name));
		} catch (NoSuchFieldException e) {
			return (T) null;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
