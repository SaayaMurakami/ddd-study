package domain.repository;

import java.util.Optional;

import domain.model.prefixcall.Contructor;
import domain.value.ContructorId;

/**
 * @author s-murakami
 */
public interface IPrefixcallRepository {

	public Optional<Contructor> findByConstructorId(ContructorId contructorId);

	public void insert(Contructor contructor);

	public void update(Contructor contructor);

}
