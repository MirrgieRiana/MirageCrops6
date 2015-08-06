package mirrg.struct.hydrogen;

import java.io.Serializable;
import java.util.function.Supplier;

public class Struct<X> implements Supplier<X>, Serializable
{

	private static final long serialVersionUID = -1479288488157485165L;

	public X x;

	public Struct(X x)
	{
		this.x = x;
	}

	public Struct()
	{

	}

	@Override
	public X get()
	{
		return x;
	}

	public X getX()
	{
		return x;
	}

	public void setX(X x)
	{
		this.x = x;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Struct<?> other = (Struct<?>) obj;
		if (x == null) {
			if (other.x != null) return false;
		} else if (!x.equals(other.x)) return false;
		return true;
	}

}
