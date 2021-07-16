package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * 契約者
 * @author s-murakami
 */
@Entity
@Table(name = "contructor")
public class ContructorEntity extends PanacheEntityBase {

	/**
	 * 契約者ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contructor_id")
	public Long contructorId;
	
	/**
	 * プレフィックスコールリスト
	 */
	@OneToMany(mappedBy = "prefixcall")
    public List<PrefixcallEntity> prefixcalls;
}
