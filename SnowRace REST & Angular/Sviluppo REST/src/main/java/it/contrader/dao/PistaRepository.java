package it.contrader.dao;

import it.contrader.model.Pista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PistaRepository extends JpaRepository<Pista, Long> {

    public Page<Pista> findByImpianto_Amministratore_Username(Pageable pageable, String amministratoreId);
    public Pista findFirstByNome(String nome);
    public Page<Pista> findByImpianto_IdImpianto(Pageable pageable, Long impianto);
    public List<Pista> findByImpianto_IdImpianto(Long impianto);

}
