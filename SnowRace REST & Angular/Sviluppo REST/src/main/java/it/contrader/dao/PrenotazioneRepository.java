package it.contrader.dao;
import it.contrader.model.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> { //qui specifico l'entità a cui deve essere mappata nel db e la chiave primaria dell'id di tipo Long

    public Page<Prenotazione> findByDataInizioIsGreaterThanEqual(Pageable pageable, Date dataInizio);

    public Page<Prenotazione> findByPista_IdPista(Pageable pageable, Long idPista);

    public List<Prenotazione> findByUser_id(Long id);
    public List<Prenotazione> findByPista_idPista(Long idPista);
 }
