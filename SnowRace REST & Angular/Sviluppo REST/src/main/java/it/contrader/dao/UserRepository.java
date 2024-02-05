package it.contrader.dao;

import it.contrader.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


/**
 * Estende CrudRepository ed eredita tutti i metodi di CRUD. 
 * Definisce il metodo di login.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 * @see CrudRepository
 *
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	Page<User> findAll(Pageable pageable);

	List<User> findByUsertype(User.Usertype usertype);


}
