package presentation.interactor.prefixcall;

import java.time.LocalDateTime;
import java.util.Optional;

import application.usecase.apply.IPrefixcallApplyUseCase;
import application.usecase.apply.PrefixcallApplyRequest;
import application.usecase.apply.PrefixcallApplyResponse;
import domain.model.prefixcall.Contructor;
import domain.model.prefixcall.Prefixcall;
import domain.repository.IPrefixcallRepository;
import domain.value.PrefixcallStatus;

/**
 * @author s-murakami
 */
public class PrefixcallApplyInteractor implements IPrefixcallApplyUseCase {

	private IPrefixcallRepository prefixcallRepository;

	public PrefixcallApplyInteractor(IPrefixcallRepository prefixcallRepository) {
		this.prefixcallRepository = prefixcallRepository;
	}

	@Override
	public PrefixcallApplyResponse applyPrefixcall(PrefixcallApplyRequest request) {
		// 現在のデータを取得
		Optional<Contructor> optContructor = prefixcallRepository.findByConstructorId(request.getContructorId());

		// 新規のプレフィックスコールを作成
		if (optContructor.isPresent()) {
			// すでに契約者が存在する場合はプレフィックスコールを追加
			Prefixcall prefixcall = new Prefixcall(null, request.getContructorId(), request.getMsisdn(),
					request.getPlan(), LocalDateTime.now(), PrefixcallStatus.APPLYING, null, null);

			Contructor contructor = optContructor.get();
			contructor.apply(prefixcall);

			prefixcallRepository.update(contructor);
			return new PrefixcallApplyResponse(contructor.getContructorId());
		} else {
			// 契約者が存在しない場合は契約者もプレフィックスコールも新規作成
			Prefixcall prefixcall = new Prefixcall(null, null, request.getMsisdn(), request.getPlan(),
					LocalDateTime.now(), PrefixcallStatus.APPLYING, null, null);

			Contructor contructor = new Contructor(null);
			contructor.apply(prefixcall);

			prefixcallRepository.insert(contructor);
			return new PrefixcallApplyResponse(contructor.getContructorId());
		}
	}
}
