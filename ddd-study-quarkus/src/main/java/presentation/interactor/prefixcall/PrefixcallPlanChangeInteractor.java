package presentation.interactor.prefixcall;

import java.util.Optional;

import application.usecase.planchenge.IPrefixcallPlanChengeUseCase;
import application.usecase.planchenge.PrefixcallPlanChangeRequest;
import application.usecase.planchenge.PrefixcallPlanChangeResponse;
import domain.model.prefixcall.Contructor;
import domain.repository.IPrefixcallRepository;
import domain.value.Plan;

/**
 * @author s-murakami
 */
public class PrefixcallPlanChangeInteractor implements IPrefixcallPlanChengeUseCase {

	private IPrefixcallRepository prefixcallRepository;

	public PrefixcallPlanChangeInteractor(IPrefixcallRepository prefixcallRepository) {
		this.prefixcallRepository = prefixcallRepository;
	}

	@Override
	public PrefixcallPlanChangeResponse planChenge(PrefixcallPlanChangeRequest request) {
		// 現在のデータを集約単位で取得
		Optional<Contructor> optContructor = prefixcallRepository.findByConstructorId(request.getContructorId());

		// 新規のプレフィックスコールを作成
		Contructor contructor = optContructor.get();
		contructor.reservePlanChange(Plan.BASIC, request.getPrefixcallId());

		prefixcallRepository.update(contructor);

		return new PrefixcallPlanChangeResponse(contructor.getContructorId());
	}
}
