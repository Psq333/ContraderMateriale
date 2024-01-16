package it.contrader.dao;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.model.Anagrafica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AnagraficaRepository extends CrudRepository<Anagrafica, Long> {

    public Anagrafica findByUserId(Long userId);
}