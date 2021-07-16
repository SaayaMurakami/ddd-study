package presentation.controller;

import javax.validation.constraints.NotNull;

/**
 * @author s-murakami
 */
public class PrefixcallApplyRequestViewModel {

	/** 契約者ID e.g. 1 */
	public Long contructor_id;

	/** 電話番号 e.g. 09012345678 */
	@NotNull
	public String msisdn;

	/** プランコード e.g. BASIC */
	@NotNull
	public String plan_code;
}
