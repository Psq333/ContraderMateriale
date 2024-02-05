package it.contrader.converter;
import it.contrader.dto.PrenotazioneDTO;
import it.contrader.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
    @Component
    public class PrenotazioneConverter extends AbstractConverter <Prenotazione, PrenotazioneDTO>{
        @Autowired
        private UserConverter userConverter;

        @Autowired
        private PistaConverter pistaConverter;

        @Override
        public Prenotazione toEntity(PrenotazioneDTO prenotazioneDTO) {
            Prenotazione prenotazione = null;
            if (prenotazioneDTO != null) {
                prenotazione = new Prenotazione(prenotazioneDTO.getIdPrenotazione(), prenotazioneDTO.getDataInizio(), prenotazioneDTO.getDataFine(),pistaConverter.toEntity(prenotazioneDTO.getPista()), userConverter.toEntity(prenotazioneDTO.getUser()));
            }                                                                                                                                           //inietto il converter di pista, e trasformo la pista di prenDTO in un'entitò
            return prenotazione;
        }

        @Override
        public PrenotazioneDTO toDTO(Prenotazione prenotazione) {
            PrenotazioneDTO prenotazioneDTO = null;
            if (prenotazione !=null){
                prenotazioneDTO = new PrenotazioneDTO(prenotazione.getIdPrenotazione(), userConverter.toDTO(prenotazione.getUser()),prenotazione.getDataInizio(),prenotazione.getDataFine(),pistaConverter.toDTO(prenotazione.getPista()));

            }
            return prenotazioneDTO;
        }

        @Override
        protected Class<PrenotazioneDTO> getDTOClass() {
            return PrenotazioneDTO.class;
        }
    }

