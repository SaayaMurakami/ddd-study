package domain.value;

/**
 * @author s-murakami
 */
public final class ContructorId {

	private final long constructorId;

	public ContructorId(long constructorId) {
		this.constructorId = constructorId;
	}

	public long getConstructorId() {
		return constructorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (constructorId ^ (constructorId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContructorId other = (ContructorId) obj;
		if (constructorId != other.constructorId)
			return false;
		return true;
	}
}
