package it.contrader.dao;
import it.contrader.model.Attrezzatura;
import it.contrader.model.Impianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
@Transactional

public interface AttrezzaturaRepository extends JpaRepository<Attrezzatura, Long>{


}




