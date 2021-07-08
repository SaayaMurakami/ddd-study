package prefixcall.domain.talkdetail;
import java.time.LocalDateTime;
import java.time.YearMonth;

import prefixcall.domain.prefixcall.Msisdn;
import prefixcall.domain.prefixcall.PrefixcallId;

public class TalkDetail {

	private final TalkDetailId callDetailId;
	private final PrefixcallId prefixcallId;
	private final Msisdn msisdn;
	private final PhoneNumber destinationCommunicationPhoneNumber;
	private final Charge talkCharge;
	private final TalkTime talkTime;
	private final LocalDateTime callStartDatetime;
	private final YearMonth useYearMonth;
	private final TalkCategory callCategory;
	
	public TalkDetail(TalkDetailId callDetailId, PrefixcallId prefixcallId, Msisdn msisdn,
			PhoneNumber destinationCommunicationPhoneNumber, Charge callingFee, TalkTime talkTime,
			LocalDateTime callStartDatetime, YearMonth useYearMonth, TalkCategory callCategory) {
		this.callDetailId = callDetailId;
		this.prefixcallId = prefixcallId;
		this.msisdn = msisdn;
		this.destinationCommunicationPhoneNumber = destinationCommunicationPhoneNumber;
		this.talkCharge = callingFee;
		this.talkTime = talkTime;
		this.callStartDatetime = callStartDatetime;
		this.useYearMonth = useYearMonth;
		this.callCategory = callCategory;
	}

	public TalkDetailId getCallDetailId() {
		return callDetailId;
	}

	public PrefixcallId getPrifixcallId() {
		return prefixcallId;
	}

	public Msisdn getMsisdn() {
		return msisdn;
	}

	public PhoneNumber getDestinationCommunicationPhoneNumber() {
		return destinationCommunicationPhoneNumber;
	}

	public Charge getCallingFee() {
		return talkCharge;
	}

	public TalkTime getTalkTime() {
		return talkTime;
	}

	public LocalDateTime getCallStartDatetime() {
		return callStartDatetime;
	}

	public YearMonth getUseYearMonth() {
		return useYearMonth;
	}

	public TalkCategory getCallCategory() {
		return callCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callDetailId == null) ? 0 : callDetailId.hashCode());
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
		TalkDetail other = (TalkDetail) obj;
		if (callDetailId == null) {
			if (other.callDetailId != null)
				return false;
		} else if (!callDetailId.equals(other.callDetailId))
			return false;
		return true;
	}
}
