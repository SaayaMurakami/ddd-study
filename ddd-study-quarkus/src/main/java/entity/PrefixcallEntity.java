package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * プレフィックスコール
 * @author s-murakami
 */
@Entity
@Table(name = "prefixcall") 
public class PrefixcallEntity extends PanacheEntityBase {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
	public Long prefixcallId;
	
	@ManyToOne
    @JoinColumn(name = "contructor_id", referencedColumnName = "contructor_id")
	public ContructorEntity contructor;
	
    @Column(name = "msisdn")
	public String msisdn;
	
    @Column(name = "plan_code")
	public String PlanCode;
	
    @Column(name = "apply_date_time")
	public LocalDateTime applyDateTime;
	
    @Column(name = "status_code")
	public String statusCode;
	
    @Column(name = "next_plan_code")
	public String nextPlanCode;
	
    @Column(name = "plan_change_date")
	public LocalDate planChangeDate;
}
