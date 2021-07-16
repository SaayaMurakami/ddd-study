package application.usecase.planchenge;

import domain.value.ContructorId;
import domain.value.Plan;
import domain.value.PrefixcallId;

/**
 * @author s-murakami
 */
public class PrefixcallPlanChangeRequest {

	private final ContructorId contructorId;
	private final PrefixcallId prefixcallId;
	private final Plan plan;

	public PrefixcallPlanChangeRequest(long contructorId, long prefixcallId, String plan) {
		this.contructorId = new ContructorId(contructorId);
		this.prefixcallId = new PrefixcallId(prefixcallId);
		this.plan = Plan.valueOf(plan);
	}

	public ContructorId getContructorId() {
		return contructorId;
	}

	public PrefixcallId getPrefixcallId() {
		return prefixcallId;
	}

	public Plan getPlan() {
		return plan;
	}
}
