package it.contrader.dao;

import it.contrader.model.Impianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ImpiantoRepository extends JpaRepository<Impianto, Long> {

    public List<Impianto> findByAmministratore_id(Long id_amministratore);

}
