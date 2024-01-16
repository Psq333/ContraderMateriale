package it.contrader.converter;

import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Noleggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NoleggioConverter extends AbstractConverter<Noleggio, NoleggioDTO> {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private  AttrezzaturaConverter attrezzaturaConverter;

    @Override
    public Noleggio toEntity(NoleggioDTO noleggioDTO) {
        Noleggio noleggio = null;
        if (noleggioDTO != null) {
            noleggio = new Noleggio(noleggioDTO.getIdnoleggio(), userConverter.toEntity(noleggioDTO.getUser()), attrezzaturaConverter.toEntity(noleggioDTO.getIdattrezzatura()), noleggioDTO.getStartDate(), noleggioDTO.getEndDate());
        }
        return noleggio;
    }

    @Override
    public NoleggioDTO toDTO(Noleggio noleggio) {
        NoleggioDTO noleggioDTO = null;
        if (noleggio != null) {
            noleggioDTO = new NoleggioDTO(noleggio.getIdnoleggio(), userConverter.toDTO(noleggio.getUser()) ,attrezzaturaConverter.toDTO(noleggio.getIdattrezzatura())  , noleggio.getStartDate(), noleggio.getEndDate());

        }
        return noleggioDTO;
    }
}
