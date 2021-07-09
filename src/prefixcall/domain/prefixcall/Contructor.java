package prefixcall.domain.prefixcall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author s-murakami
 */
public class Contructor {

	// ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
	private final ContructorId contructorId;
	private final List<Prefixcall>  prefixcalls; // finalつけた

	// ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
	public Contructor() {
		this.contructorId = issueId();
		this.prefixcalls = new ArrayList<Prefixcall>();
	}

	// ===================================================================================
    //                                                                        　　 Behavior
    //                                                                            ========
	/**
	 * プレフィックスコールを申し込む
	 * @param prefixcall プレフィックスコール
	 */
	public void apply(Prefixcall prefixcall) {
		if (同じ電話番号で有効なプレフィックスコールが存在するか(prefixcall)) {
			throw new IllegalStateException("1電話番号につき有効なプレフィックスコールは1つまで。prefixCall: " + prefixcall);
		} 
		prefixcalls.add(prefixcall);
	}

	private boolean 同じ電話番号で有効なプレフィックスコールが存在するか(Prefixcall prefixcall) {
		return prefixcalls.stream() //
				.filter(prefix -> prefix.getMsisdn().equals(prefixcall.getMsisdn())) //
				.filter(prefix -> isActive(prefix)) //
				.findAny()
				.isPresent();
	}
	
	/**
	 * プレフィックスコールを解約する
	 * @param prefixcall プレフィックスコール
	 */
	public void terminate(Prefixcall prefixcall) {
		// TODO 契約者に紐付いたプレフィックスコールかどうかチェックする
		// TODO リストで持っているプレフィックスコールが上書きされていない！
		prefixcall.terminate();
	}
	
	/**
	 * プレフィックスコールをキャンセルする
	 * @param prefixcall プレフィックスコール
	 */
	public void cancel(Prefixcall prefixcall) {
		prefixcall.cancel();
	}
	
	// ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
	private ContructorId issueId() {
		return new ContructorId(999L); // TODO 実装する
	}
	
	private boolean isActive(Prefixcall prefixcall) {
		return prefixcall.getStatus() == PrefixcallStatus.APPLYING // 
				|| prefixcall.getStatus() == PrefixcallStatus.RUNNING;
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