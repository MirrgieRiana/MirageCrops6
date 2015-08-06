package mirrg.struct.hydrogen;

import java.io.Serializable;
import java.util.Map;

public class Tuple<X, Y> implements Serializable, Map.Entry<X, Y>
{

	private static final long serialVersionUID = -1479288488157485165L;

	private X x;
	private Y y;

	public Tuple(X x, Y y)
	{
		this.x = x;
		this.y = y;
	}

	public X getX()
	{
		return x;
	}

	public void setX(X x)
	{
		this.x = x;
	}

	public Tuple<X, Y> modifyX(X x)
	{
		return new Tuple<>(x, y);
	}

	public Y getY()
	{
		return y;
	}

	public void setY(Y y)
	{
		this.y = y;
	}

	public Tuple<X, Y> modifyY(Y y)
	{
		return new Tuple<>(x, y);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Tuple<?, ?> other = (Tuple<?, ?>) obj;
		if (x == null) {
			if (other.x != null) return false;
		} else if (!x.equals(other.x)) return false;
		if (y == null) {
			if (other.y != null) return false;
		} else if (!y.equals(other.y)) return false;
		return true;
	}

	@Override
	public X getKey()
	{
		return x;
	}

	@Override
	public Y getValue()
	{
		return y;
	}

	@Override
	public Y setValue(Y value)
	{
		throw new UnsupportedOperationException();
	}

}
