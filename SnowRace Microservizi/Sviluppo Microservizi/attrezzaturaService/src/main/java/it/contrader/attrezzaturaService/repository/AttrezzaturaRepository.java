package it.contrader.attrezzaturaService.repository;


import it.contrader.attrezzaturaService.model.Attrezzatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AttrezzaturaRepository extends JpaRepository<Attrezzatura, Long> {

}
