package prefixcall.domain.prefixcall;

import java.time.LocalDate;
import java.time.LocalDateTime;

import prefixcall.domain.contructor.ContructorId;

public class Prefixcall {
	
	// ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
	private final PrefixcallId prefixcallId;
	private final ContructorId contructorId;
	private final Msisdn msisdn;
	private Plan plan;
	private LocalDateTime applyDateTime;
	private PrefixcallStatus status;
	private Plan nextPlan;
	private LocalDate planChangeDate;
	
	// ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
	public Prefixcall(ContructorId contructorId, Msisdn msisdn, Plan plan, LocalDateTime applyDateTime, PrefixcallStatus status, Plan nextPlan, LocalDate planChangDate) {
		this.prefixcallId = issueId();
		this.contructorId = contructorId;
		this.msisdn = msisdn;
		this.plan = plan;
		this.applyDateTime = applyDateTime;
		this.status = status;
		this.nextPlan = nextPlan;
		this.planChangeDate = planChangDate;
	}
	
	// ===================================================================================
    //                                                                        Domain Event
    //                                                                        ============
	/**
	 * プラン変更を予約する
	 * @param plan 変更予定のプラン
	 */
	void reservePlanChange(Plan plan) {
		this.nextPlan = plan;
		this.planChangeDate = LocalDate.now().plusMonths(1).withDayOfMonth(1); // プランによって月初か月末にかわるが一旦月初適用とする
	}
	
	/**
	 * プラン変更を適用する
	 */
	void confirmPlanChange() {
		this.plan = this.nextPlan;
		this.nextPlan = null;
		this.planChangeDate = null;
	}
	
	// ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
	private PrefixcallId issueId() {
		return new PrefixcallId(999L); // TODO 実装する
	}
	
	// ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prefixcallId == null) ? 0 : prefixcallId.hashCode());
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
		Prefixcall other = (Prefixcall) obj;
		if (prefixcallId == null) {
			if (other.prefixcallId != null)
				return false;
		} else if (!prefixcallId.equals(other.prefixcallId))
			return false;
		return true;
	}
	
	// ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
	public PrefixcallId getPrefixcallId() {
		return prefixcallId;
	}
 
	public ContructorId getContructorId() {
		return contructorId;
	}

	public Msisdn getMsisdn() {
		return msisdn;
	}

	public Plan getPlan() {
		return plan;
	}

	public LocalDateTime getApplyDateTime() {
		return applyDateTime;
	}

	public PrefixcallStatus getStatus() {
		return status;
	}

	public Plan getNextPlan() {
		return nextPlan;
	}

	public LocalDate getPlanChangeDate() {
		return planChangeDate;
	}
	
	public void terminate() {
		this.status = PrefixcallStatus.TERMINATED;
	}
	
	public void cancel() {
		this.status = PrefixcallStatus.CANCELD;
	}
	
	public void useStart() {
		this.status = PrefixcallStatus.RUNNING;
	}
}
