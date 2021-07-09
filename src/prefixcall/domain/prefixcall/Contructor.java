package prefixcall.domain.prefixcall;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author s-murakami
 */
public class Contructor {

	// ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
	private final ContructorId contructorId;
	private final Map<PrefixcallId, Prefixcall>  prefixcalls; // finalつけた

	// ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
	public Contructor(ContructorId contructorId) {
		this.contructorId = contructorId;
		this.prefixcalls = new HashMap<PrefixcallId, Prefixcall>();
	}

	// ===================================================================================
    //                                                                        　　 Behavior
    //                                                                            ========
	/**
	 * プレフィックスコールを申し込む
	 * @param prefixcall プレフィックスコール
	 */
	public void apply(Prefixcall prefixcall) {
		validatePrefixcall(prefixcall); 
		prefixcalls.put(prefixcall.getPrefixcallId(), prefixcall);
	}

	/**
	 * プラン変更を予約する
	 * @param plan 変更予定のプラン
	 */
	public void reservePlanChange(Plan plan, PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.reservePlanChange(plan);

		prefixcalls.replace(prefixcallId, prefixcall);
	}
	
	/**
	 * プラン変更を適用する
	 */
	public void applyPlanChange(PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.applyPlanChange();
	
		prefixcalls.replace(prefixcallId, prefixcall);
	}
	
	/**
	 * プレフィックスコールを解約する
	 * @param prefixcall プレフィックスコール
	 */
	public void terminate(PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.terminate();
		
		prefixcalls.replace(prefixcallId, prefixcall);
	}
	
	/**
	 * プレフィックスコールをキャンセルする
	 * @param prefixcall プレフィックスコール
	 */
	public void cancel(PrefixcallId prefixcallId) {
		Prefixcall prefixcall = getPrefixcall(prefixcallId);
		prefixcall.cancel();
		
		prefixcalls.replace(prefixcallId, prefixcall);
	}
	
	// ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
	private void validatePrefixcall(Prefixcall prefixcall) {
		if (activePrefixcallExists(prefixcall.getMsisdn())) {
			throw new IllegalStateException("1電話番号につき有効なプレフィックスコールは1つまで。prefixCall: " + prefixcall);
		}
	}

	private boolean activePrefixcallExists(Msisdn msisdn) {
		return prefixcalls.values()
				.stream()
				.filter(prefix -> prefix.getMsisdn().equals(msisdn))
				.filter(prefix -> isActive(prefix))
				.findAny()
				.isPresent();
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
    //                                                                      Basic Override
    //                                                                      ==============
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
    //                                                                            Accessor
    //                                                                            ========
	public ContructorId getContructorId() {
		return contructorId;
	}
}