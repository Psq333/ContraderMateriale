package it.contrader.converter;

import it.contrader.dao.AttrezzaturaRepository;
import it.contrader.dao.UserRepository;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Noleggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoleggioConverter extends AbstractConverter<Noleggio, NoleggioDTO> {

    @Autowired
    private AttrezzaturaRepository attrezzaturaRepository;
    @Autowired
    private AttrezzaturaConverter attrezzaturaConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public Noleggio toEntity(NoleggioDTO noleggioDTO) {
        Noleggio noleggio = null;
        if (noleggioDTO != null) {
            noleggio = new Noleggio(noleggioDTO.getIdnoleggio(),
                    userRepository.findByUsername(noleggioDTO.getUsername()),
                    attrezzaturaConverter.toEntity(noleggioDTO.getIdattrezzatura()),
                    noleggioDTO.getStartDate(),
                    noleggioDTO.getEndDate());
        }
        return noleggio;
    }

    @Override
    public NoleggioDTO toDTO(Noleggio noleggio) {
        NoleggioDTO noleggioDTO = null;
        if (noleggio != null) {
            String username = null;
            if(noleggio.getUsername() != null)
                 username = noleggio.getUsername().getUsername();
            noleggioDTO = new NoleggioDTO(
                    noleggio.getIdnoleggio(),
                    username,
                    attrezzaturaConverter.toDTO(noleggio.getIdattrezzatura()),
                    noleggio.getStartDate(),
                    noleggio.getEndDate());
        }
        return noleggioDTO;
    }

    @Override
    protected Class<NoleggioDTO> getDTOClass() {
        return NoleggioDTO.class;
    }
}