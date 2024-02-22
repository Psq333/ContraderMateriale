package it.contrader.prenotazioneservice.repository;

import it.contrader.prenotazioneservice.dto.PrenotazioneDTO;
import it.contrader.prenotazioneservice.model.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    @Override
    Page<Prenotazione> findAll(Pageable pageable);

    public Page<Prenotazione> findByIdUserAndIdPista (Pageable pageable, Long userId, Long idPista);

    public Page<Prenotazione> findAllByIdUserAndDataInizioIsGreaterThanEqual (Pageable pageable, Long idUser, Date dataInizio);


}
