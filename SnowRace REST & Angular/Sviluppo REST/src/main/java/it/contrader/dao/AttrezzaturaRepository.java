package it.contrader.dao;

import it.contrader.model.Attrezzatura;
import it.contrader.model.Impianto;
import it.contrader.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional


    public interface AttrezzaturaRepository extends JpaRepository<Attrezzatura, Long>{

    public List<Attrezzatura> findByidImpianto_idImpianto(Long id_impianto);

    }





