package infrastructure.repository.prefixcall;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.model.prefixcall.Contructor;
import domain.model.prefixcall.Prefixcall;
import domain.repository.IPrefixcallRepository;
import domain.value.ContructorId;
import domain.value.Msisdn;
import domain.value.Plan;
import domain.value.PrefixcallId;
import domain.value.PrefixcallStatus;
import entity.ContructorEntity;
import entity.PrefixcallEntity;

/**
 * @author s-murakami
 */
public class PrefixcallRepository implements IPrefixcallRepository {

	@Override
	public Optional<Contructor> findByConstructorId(ContructorId contructorId) {
		Optional<ContructorEntity> optContructorEntity = ContructorEntity.find("contructor_id = ?1", contructorId)
				.singleResultOptional();

		if (optContructorEntity.isPresent()) {
			List<Prefixcall> PrefixcallList = optContructorEntity.get().prefixcalls //
					.stream() //
					.map(entity -> entity)
					.map(entity -> new Prefixcall(new PrefixcallId(entity.prefixcallId),
							new ContructorId(entity.contructor.contructorId), //
							new Msisdn(entity.msisdn), //
							Plan.valueOf(entity.PlanCode), //
							entity.applyDateTime, //
							PrefixcallStatus.valueOf(entity.statusCode), //
							Plan.valueOf(entity.nextPlanCode), //
							entity.planChangeDate))
					.collect(Collectors.toList());
			return Optional.of(new Contructor(contructorId, PrefixcallList));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void insert(Contructor contructor) {
		ContructorEntity contructorEntity = new ContructorEntity();
		contructorEntity.contructorId = contructor.getContructorId().getConstructorId();
		contructorEntity.persist();

		contructor.getPrefixcalls().forEach(prefixcall -> {
			PrefixcallEntity prefixcallEntity = new PrefixcallEntity();
			prefixcallEntity.prefixcallId = prefixcall.getPrefixcallId().getId();
			prefixcallEntity.contructor = contructorEntity;
			prefixcallEntity.msisdn = prefixcall.getMsisdn().getMsisdn();
			prefixcallEntity.PlanCode = prefixcall.getPlan().name();
			prefixcallEntity.applyDateTime = prefixcall.getApplyDateTime();
			prefixcallEntity.statusCode = prefixcall.getStatus().name();
			prefixcallEntity.nextPlanCode = prefixcall.getNextPlan().name();
			prefixcallEntity.planChangeDate = prefixcall.getPlanChangeDate();
			prefixcallEntity.persist();
		});
	}

	@Override
	public void update(Contructor contructor) {
		// 集約全部更新...すごいパフォーマンス悪そう。
		// 差分をどう判別したらいいんだ？？
		contructor.getPrefixcalls().forEach(prefixcall -> {
			PrefixcallEntity entity = new PrefixcallEntity();
			entity.prefixcallId = prefixcall.getPrefixcallId().getId();
			entity.msisdn = prefixcall.getMsisdn().getMsisdn();
			entity.PlanCode = prefixcall.getPlan().name();
			entity.applyDateTime = prefixcall.getApplyDateTime();
			entity.statusCode = prefixcall.getStatus().name();
			entity.nextPlanCode = prefixcall.getNextPlan().name();
			entity.planChangeDate = prefixcall.getPlanChangeDate();
			PrefixcallEntity.flush();
		});
	}
}
