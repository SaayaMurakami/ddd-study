package domain.model.talkbill;

import java.time.YearMonth;

import domain.value.Charge;
import domain.value.ContructorId;
import domain.value.TalkBillId;
import domain.value.TalkCategory;

/**
 * @author s-murakami
 */
public class TalkBill {
	
	private final TalkBillId talkBillId;
	private final YearMonth useYearMonth;
	private final Charge totalCharge;
	private final TalkCategory talkCategory;
	private final ContructorId contructorId;
	
	public TalkBill(TalkBillId talkBillId, YearMonth useYearMonth, Charge totalCharge, TalkCategory talkCategory,
			ContructorId contructorId) {
		this.talkBillId = talkBillId;
		this.useYearMonth = useYearMonth;
		this.totalCharge = totalCharge;
		this.talkCategory = talkCategory;
		this.contructorId = contructorId;
	}

	public TalkBillId getTalkBillId() {
		return talkBillId;
	}

	public YearMonth getUseYearMonth() {
		return useYearMonth;
	}

	public Charge getTotalCharge() {
		return totalCharge;
	}

	public TalkCategory getTalkCategory() {
		return talkCategory;
	}

	public ContructorId getContructorId() {
		return contructorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((talkBillId == null) ? 0 : talkBillId.hashCode());
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
		TalkBill other = (TalkBill) obj;
		if (talkBillId == null) {
			if (other.talkBillId != null)
				return false;
		} else if (!talkBillId.equals(other.talkBillId))
			return false;
		return true;
	}
}
