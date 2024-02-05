package it.contrader.service;

import it.contrader.converter.UserConverter;
import it.contrader.dao.UserRepository;
import it.contrader.dto.*;
import it.contrader.exceptions.InvalidCredentialsException;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("User")
public class UserService extends AbstractService<User,UserDTO> {

	@Autowired
	AnagraficaService anagraficaService;
	@Autowired
	ImpiantoService impiantoService;
	@Autowired
	PrenotazioneService prenotazioneService;
	@Autowired
	NoleggioService noleggioService;

	public UserDTO findByUsernameAndPassword(String username, String password) {
		return converter.toDTO(((UserRepository)repository).findByUsernameAndPassword(username, password)
				.orElseThrow(() -> new InvalidCredentialsException("Credenziali Errate")));
	}

	public Page<UserDTO> getAllPaginata(Pageable pageable) {
		Page<User> page = ((UserRepository) repository).findAll(pageable);
		return ((UserConverter)converter).convertToDTOPage(page);
	}

	@Override
	public Page<UserDTO> getAllPaginataCustom(Pageable pageable, String id) {
		return null;
	}

	@Override
	public Page<UserDTO> getAllPaginataAdmin(Pageable pageable, String id) {
		return null;
	}

	public List<UserDTO> getAll(User.Usertype userType) {
		List<User> list = ((UserRepository)repository).findByUsertype(userType);
		return converter.toDTOList(list);
	}

	public UserDTO readusername(String username) {
		return converter.toDTO(((UserRepository)repository).findByUsername(username));
	}
	public void deleteUser(long id){
		AnagraficaDTO anagrafica = anagraficaService.findByUserId(id);
		if (anagrafica != null) {
			anagrafica.setUser(null);
			anagraficaService.update(anagrafica);
		}
		List<ImpiantoDTO> impianti = impiantoService.findByAmministratore_Id(id);
		if (!impianti.isEmpty()){
			for (ImpiantoDTO imp: impianti ) {
				imp.setAmministratore(null);
				impiantoService.update(imp);
			}
		}
		List<PrenotazioneDTO> prenotazioni = prenotazioneService.findByUser_Id(id);
		if (!prenotazioni.isEmpty()){
			for (PrenotazioneDTO pren: prenotazioni ) {
				pren.setUser(null);
				prenotazioneService.update(pren);
			}
		}
		List<NoleggioDTO> noleggi = noleggioService.findByUser_Id(id);
		if (!noleggi.isEmpty()){
			for (NoleggioDTO nol: noleggi ) {
				nol.setUsername(null);
				noleggioService.update(nol);
			}
		}
		this.delete(id);
	}



}
