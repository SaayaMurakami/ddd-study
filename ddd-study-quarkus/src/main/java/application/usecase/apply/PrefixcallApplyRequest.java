package application.usecase.apply;

import domain.value.ContructorId;
import domain.value.Msisdn;
import domain.value.Plan;

/**
 * @author s-murakami
 */
public class PrefixcallApplyRequest {

	private final ContructorId contructorId;
	private final Msisdn msisdn;
	private final Plan plan;

	public PrefixcallApplyRequest(ContructorId contructorId, Msisdn msisdn, Plan plan) {
		this.contructorId = contructorId;
		this.msisdn = msisdn;
		this.plan = plan;
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
}
