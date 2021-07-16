package application.usecase.planchenge;

import domain.value.ContructorId;

/**
 * @author s-murakami
 */
public class PrefixcallPlanChangeResponse {

	// TODO なにを返すんだろう…
	private final ContructorId contructorId;

	public PrefixcallPlanChangeResponse(ContructorId contructorId) {
		this.contructorId = contructorId;
	}

	public ContructorId getContructorId() {
		return contructorId;
	}
}
