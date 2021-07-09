package prefixcall.domain.talkbill;

/**
 * @author s-murakami
 */
public final class TalkBillId {
	
	private final long id;

	public TalkBillId(long id) {
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
		TalkBillId other = (TalkBillId) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
