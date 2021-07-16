package domain.model.prefixcall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import domain.value.ContructorId;
import domain.value.Msisdn;
import domain.value.Plan;
import domain.value.PrefixcallId;
import domain.value.PrefixcallStatus;

/**
 * @author s-murakami
 */
public class Contructor {

	// ===================================================================================
	// Attribute
	// =========
	private final ContructorId contructorId;
	private final Map<PrefixcallId, Prefixcall> prefixcalls; // finalつけた

	// ===================================================================================
	// Constructor
	// ===========
	public Contructor(ContructorId contructorId) {
		this.contructorId = contructorId;
		this.prefixcalls = new HashMap<PrefixcallId, Prefixcall>();
	}

	/**
	 * リポジトリからよぶ再構成用のコンストラクタ publicだが、リポジトリからしか呼ばないようにする。
	 */
	public Contructor(ContructorId contructorId, List<Prefixcall> prefixcallList) {
		this.contructorId = contructorId;
		this.prefixcalls = new HashMap<PrefixcallId, Prefixcall>();
		prefixcallList.forEach(prefixcall -> {
			prefixcalls.put(prefixcall.getPrefixcallId(), prefixcall);
		});
	}

	// ===================================================================================
	// Behavior
	// ========
	/**
	 * プレフィックスコールを申し込む
	 * 
	 * @param prefixcallId プレフィックスコールId
	 */
	public void apply(Prefixcall prefixcall) {
		validatePrefixcall(prefixcall);
		prefixcalls.put(prefixcall.getPrefixcallId(), prefixcall);
	}

	/**
	 * プラン変更を予約する
	 * 
	 * @param plan         変更予定のプラン
	 * @param prefixcallId プレフィックスコールId
	 */
	public void reservePlanChange(Plan plan, PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.reservePlanChange(plan);
	}

	/**
	 * プラン変更を適用する
	 * 
	 * @param prefixcallId プレフィックスコールId
	 */
	public void applyPlanChange(PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.applyPlanChange();
	}

	/**
	 * プレフィックスコールを解約する
	 * 
	 * @param prefixcallId プレフィックスコールId
	 */
	public void terminate(PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.terminate();
	}

	/**
	 * プレフィックスコールをキャンセルする
	 * 
	 * @param prefixcallId プレフィックスコールId
	 */
	public void cancel(PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.cancel();
	}

	// ===================================================================================
	// Assist Logic
	// ============
	private void validatePrefixcall(Prefixcall prefixcall) {
		if (activePrefixcallExists(prefixcall.getMsisdn())) {
			throw new IllegalStateException("1電話番号につき有効なプレフィックスコールは1つまで。prefixCall: " + prefixcall);
		}
	}

	private boolean activePrefixcallExists(Msisdn msisdn) {
		return prefixcalls.values().stream().filter(prefix -> prefix.getMsisdn().equals(msisdn))
				.filter(prefix -> isActive(prefix)).findAny().isPresent(); // anyMatchでもいける
	}

	private boolean isActive(Prefixcall prefixcall) {
		return prefixcall.getStatus() == PrefixcallStatus.APPLYING
				|| prefixcall.getStatus() == PrefixcallStatus.RUNNING;
	}

	private Prefixcall getPrefixcall(PrefixcallId prefixcallId) {
		if (!prefixcalls.containsKey(prefixcallId)) {
			throw new NoSuchElementException("プレフィックスコールが存在しません。prefixCallId: " + prefixcallId.getId());
		}
		return prefixcalls.get(prefixcallId);
	}

	// ===================================================================================
	// Basic Override
	// ==============
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contructorId == null) ? 0 : contructorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contructor other = (Contructor) obj;
		if (contructorId == null) {
			if (other.contructorId != null)
				return false;
		} else if (!contructorId.equals(other.contructorId))
			return false;
		return true;
	}

	// ===================================================================================
	// Accessor
	// ========
	public ContructorId getContructorId() {
		return contructorId;
	}

	public List<Prefixcall> getPrefixcalls() {
		return (List<Prefixcall>) prefixcalls.values();
	}
}