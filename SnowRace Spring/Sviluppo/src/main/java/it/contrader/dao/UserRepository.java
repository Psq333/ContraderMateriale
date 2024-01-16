package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.User;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	List<User> findByUsertype(User.Usertype usertype);


}
