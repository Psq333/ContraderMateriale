package it.contrader.dao;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.model.Impianto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ImpiantoRepository extends JpaRepository<Impianto, Long> {

    public  Page<Impianto> findByAmministratore_Username(Pageable pageable, String id_amministratore);

    public List<Impianto> findByAmministratore_id(Long id_amministratore);
    public List<Impianto> findByAmministratore_Username(String Username);
    public List<Impianto> findByAmministratore_Id(Long id);

}