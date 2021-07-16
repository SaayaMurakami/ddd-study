package domain.value;

/**
 * @author s-murakami
 */
public final class Msisdn {
	
	private static final String REGEX = "0[789]0[0-9]{8}";
	
	private final String msisdn;
	
	public Msisdn(String msisdn) {
		if (msisdn == null || msisdn.isEmpty() || msisdn.isBlank()) {
			throw new IllegalArgumentException("null, 空文字, 空白は不正。msisdn: " + msisdn);
		}
		if (msisdn.matches(REGEX)) {
			throw new IllegalArgumentException("070, 080, 090以外で始まる11桁の数字だけで構成される文字列以外は不正。msisdn: " + msisdn);
		}
		this.msisdn = msisdn;
	}

	public String getMsisdn() {
		return msisdn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
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
		Msisdn other = (Msisdn) obj;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		return true;
	}
}
