package it.contrader.dao;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Anagrafica;
import it.contrader.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long> {

    public Anagrafica findByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT 1 FROM Anagrafica a WHERE a.user.id = u.id)")
    List<User> findUsersWithoutAnagrafica();


}