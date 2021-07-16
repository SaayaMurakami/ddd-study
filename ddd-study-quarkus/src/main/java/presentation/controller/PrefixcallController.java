package presentation.controller;

import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import application.usecase.apply.IPrefixcallApplyUseCase;
import application.usecase.apply.PrefixcallApplyRequest;
import application.usecase.apply.PrefixcallApplyResponse;
import application.usecase.planchenge.IPrefixcallPlanChengeUseCase;
import application.usecase.planchenge.PrefixcallPlanChangeRequest;
import application.usecase.planchenge.PrefixcallPlanChangeResponse;
import domain.value.ContructorId;
import domain.value.Msisdn;
import domain.value.Plan;

/**
 * プレフィクスコール
 * 
 * @author s-murakami
 */
@Path("/prefixcall")
public class PrefixcallController {

	private IPrefixcallApplyUseCase prefixcallApplyUseCase;
	private IPrefixcallPlanChengeUseCase prefixcallPlanChengeUseCase;

	public PrefixcallController(IPrefixcallApplyUseCase prefixcallApplyUseCase,
			IPrefixcallPlanChengeUseCase prefixcallPlanChengeUseCase) {
		this.prefixcallApplyUseCase = prefixcallApplyUseCase;
		this.prefixcallPlanChengeUseCase = prefixcallPlanChengeUseCase;
	}

	@POST
	@Transactional
	@Path("/apply")
	public Response applyPrefixcall(PrefixcallApplyRequestViewModel requestViewModel) {
		PrefixcallApplyRequest request = new PrefixcallApplyRequest(new ContructorId(requestViewModel.contructor_id),
				new Msisdn(requestViewModel.msisdn), Plan.valueOf(requestViewModel.plan_code));

		PrefixcallApplyResponse response = prefixcallApplyUseCase.applyPrefixcall(request);

		PrefixcallApplyResponseViewModel responseViewModel = new PrefixcallApplyResponseViewModel();
		responseViewModel.contructor_id = response.getContructorId().getConstructorId();

		return Response.ok(responseViewModel).status(200).build();
	}

	@POST
	@Transactional
	@Path("/planChange")
	public Response planChangePrefixcall(PrefixcallPlanChangeRequestViewModel requestViewModel) {
		PrefixcallPlanChangeRequest request = new PrefixcallPlanChangeRequest(requestViewModel.contructor_id,
				requestViewModel.prefixcall_id, requestViewModel.plan_code);

		PrefixcallPlanChangeResponse response = prefixcallPlanChengeUseCase.planChenge(request);

		PrefixcallApplyResponseViewModel responseViewModel = new PrefixcallApplyResponseViewModel();
		responseViewModel.contructor_id = response.getContructorId().getConstructorId();

		return Response.ok(responseViewModel).status(200).build();
	}
}
