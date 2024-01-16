package it.contrader.dao;


import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.contrader.model.Pista;

import java.util.List;

@Repository
@Transactional
public interface PistaRepository extends JpaRepository<Pista, Long> {

    List<Pista> findByImpianto_Amministratore_Id(Long amministratoreId);
    Pista findFirstByNome(String nome);
}
