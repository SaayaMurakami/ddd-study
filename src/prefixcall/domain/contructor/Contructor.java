package prefixcall.domain.contructor;

import java.util.ArrayList;
import java.util.List;

import prefixcall.domain.prefixcall.Prefixcall;
import prefixcall.domain.prefixcall.PrefixcallStatus;

public class Contructor {

	// ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
	private final ContructorId contructorId;
	private List<Prefixcall>  prefixcalls;

	// ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
	public Contructor(ContructorId contructorId) {
		this.contructorId = issueId();
		this.prefixcalls = new ArrayList<Prefixcall>();
	}

	// ===================================================================================
    //                                                                        Domain Event
    //                                                                        ============
	/**
	 * プレフィックスコールを申し込む
	 * @param prefixcall プレフィックスコール
	 */
	public void apply(Prefixcall prefixcall) {
		if (0 == prefixcalls.stream() //
				.filter(prefix -> prefix.getMsisdn().equals(prefixcall.getMsisdn())) //
				.filter(prefix -> isActive(prefix)) //
				.count()) {
			prefixcalls.add(prefixcall);
		} else {
			throw new IllegalStateException("1電話番号につき有効なプレフィックスコールは1つまで。prefixCall: " + prefixcall);
		}
	}
	
	/**
	 * プレフィックスコールを解約する
	 * @param prefixcall プレフィックスコール
	 */
	public void terminate(Prefixcall prefixcall) {
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