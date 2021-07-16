package domain.value;

/**
 * @author s-murakami
 */
public final class PhoneNumber {
	
	private static final String REGEX = "[0-9]{10}|[0-9]{11}";
	
	private final String phoneNumber;
	
	public PhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.isBlank()) {
			throw new IllegalArgumentException("null, 空文字, 空白は不正。msisdn: " + phoneNumber);
		}
		if (phoneNumber.matches(REGEX)) {
			throw new IllegalArgumentException("10桁または11桁の数字だけで構成される文字列以外は不正。phoneNumber: " + phoneNumber);
		}
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		PhoneNumber other = (PhoneNumber) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
}
