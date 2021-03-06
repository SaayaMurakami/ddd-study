package domain.value;

/**
 * @author s-murakami
 */
public final class TalkDetailId {
	
	private final long id;
	
	public TalkDetailId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		TalkDetailId other = (TalkDetailId) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
