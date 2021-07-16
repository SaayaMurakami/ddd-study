package application.usecase.apply;

import domain.value.ContructorId;

/**
 * @author s-murakami
 */
public class PrefixcallApplyResponse {

	// TODO なにを返すんだろう…
	private final ContructorId contructorId;

	public PrefixcallApplyResponse(ContructorId contructorId) {
		this.contructorId = contructorId;
	}

	public ContructorId getContructorId() {
		return contructorId;
	}

	// private final PrefixcallId prefixcallId;
	// private final ContructorId contructorId;
	// private final Msisdn msisdn;
	// private Plan plan;
	// private final LocalDateTime applyDateTime;
	// private PrefixcallStatus status;
	// private Plan nextPlan;
	// private LocalDate planChangeDate;
	//
	// public PrefixcallApplyResponse(PrefixcallId prefixcallId, ContructorId
	// contructorId, Msisdn msisdn, Plan plan,
	// LocalDateTime applyDateTime, PrefixcallStatus status, Plan nextPlan,
	// LocalDate planChangeDate) {
	// this.prefixcallId = prefixcallId;
	// this.contructorId = contructorId;
	// this.msisdn = msisdn;
	// this.plan = plan;
	// this.applyDateTime = applyDateTime;
	// this.status = status;
	// this.nextPlan = nextPlan;
	// this.planChangeDate = planChangeDate;
	// }
	//
	// public PrefixcallId getPrefixcallId() {
	// return prefixcallId;
	// }
	//
	// public ContructorId getContructorId() {
	// return contructorId;
	// }
	//
	// public Msisdn getMsisdn() {
	// return msisdn;
	// }
	//
	// public Plan getPlan() {
	// return plan;
	// }
	//
	// public LocalDateTime getApplyDateTime() {
	// return applyDateTime;
	// }
	//
	// public PrefixcallStatus getStatus() {
	// return status;
	// }
	//
	// public Plan getNextPlan() {
	// return nextPlan;
	// }
	//
	// public LocalDate getPlanChangeDate() {
	// return planChangeDate;
	// }
}
