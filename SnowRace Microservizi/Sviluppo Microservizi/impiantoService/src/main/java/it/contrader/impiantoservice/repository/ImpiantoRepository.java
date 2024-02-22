package it.contrader.impiantoservice.repository;

import it.contrader.impiantoservice.model.Impianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpiantoRepository extends JpaRepository<Impianto, Long> {

    public List<Impianto> findAllByAmministratore(Long id);

}
