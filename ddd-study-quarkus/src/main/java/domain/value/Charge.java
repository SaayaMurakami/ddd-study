package domain.value;

/**
 * @author s-murakami
 */
public final class Charge {
	
	private final int charge;

	public Charge(int charge) {
		if (charge < 0) {
			throw new IllegalArgumentException("マイナスの数は不正。fee: " + charge);
		}
		this.charge = charge;
	}

	public int getFee() {
		return charge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + charge;
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
		Charge other = (Charge) obj;
		if (charge != other.charge)
			return false;
		return true;
	}
}
