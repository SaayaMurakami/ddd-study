package domain.value;

/**
 * @author s-murakami
 */
public final class TalkTime {
	
	private final long seconds;

	public TalkTime(long seconds) {
		if (seconds < 1) {
			throw new IllegalArgumentException("1未満の数は不正。talkTime: " + seconds);
		}
		this.seconds = seconds;
	}

	public long getSeconds() {
		return seconds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (seconds ^ (seconds >>> 32));
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
		TalkTime other = (TalkTime) obj;
		if (seconds != other.seconds)
			return false;
		return true;
	}
}
