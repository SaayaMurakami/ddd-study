package presentation.controller;

import javax.validation.constraints.NotNull;

/**
 * @author s-murakami
 */
public class PrefixcallPlanChangeRequestViewModel {

	/** 契約者ID e.g. 1 */
	@NotNull
	public Long contructor_id;

	/** プレフィクスコールID e.g. 1 */
	@NotNull
	public Long prefixcall_id;

	/** プランコード e.g. BASIC */
	@NotNull
	public String plan_code;

}
