package it.contrader.anagraficaservice.repository;

import it.contrader.anagraficaservice.model.Anagrafica;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long> {

    public Optional<Anagrafica> findByUserId(Long userId);


}
