package it.contrader.dao;

import it.contrader.model.Attrezzatura;
import it.contrader.model.Noleggio;
import it.contrader.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public interface NoleggioRepository extends CrudRepository<Noleggio, Long> {

    Noleggio findByUserAndStartDateBetweenAndIdattrezzatura(User user, Date startDate, Date endDate, Attrezzatura idattrezzatura);


}