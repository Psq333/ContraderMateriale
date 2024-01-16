package it.contrader.dao;

import it.contrader.dto.PrenotazioneDTO;
import it.contrader.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    //metodo che fa la query e nel service richiami
    public List<Prenotazione> findByUser_id(Long idUser);

    //public List<Prenotazione> findByDataInizioEqualsAndUser_Id(Date data, Long idUser);
    //public List<Prenotazione> findByDataInizioGreaterThanAndUser_Id(Date data, Long idUser);

    public List<Prenotazione> findByDataInizioEqualsOrDataInizioGreaterThanAndUser_Id(Date dataE,Date dataGT, Long idUser);

    public List<Prenotazione> findByPista_IdPistaAndUser_Id(Long idPista, Long idUser);
}
