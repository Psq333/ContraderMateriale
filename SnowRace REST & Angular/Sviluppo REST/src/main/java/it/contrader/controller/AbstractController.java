package it.contrader.controller;

import it.contrader.service.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * In questa classe sono implementati tutti i metodi di CRUD dei Controller, paramentrizzati dal tipo
 * generico. Nella classe viene dichiarata l'interfaccia ServiceDTO<DTO>.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 *@param <DTO>
 *

 * 
 * @see ServiceDTO<DTO>
 */
public abstract class AbstractController <DTO>{

	@Autowired
	private ServiceDTO<DTO> service;
	
	@GetMapping("/getall")
	public Iterable<DTO> getAll(){
		return service.getAll();
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") long id) {
		service.delete(id);
	}
	
	@PutMapping("/update")
	public DTO update(@RequestBody DTO dto){
		return service.update(dto);

	}
	
	@PostMapping("/insert")
	public DTO insert (@RequestBody @Valid DTO dto) {
		System.out.println(dto.toString());
		return service.insert(dto);
	}
	
	@GetMapping("/read")
	public DTO read(long id) {
		return service.read(id);
	}

	@GetMapping("/getAllPaginata")
	public ResponseEntity<Page<DTO>> getall(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber ) {
		return new ResponseEntity<>(service.getAllPaginata(PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
	}

	@GetMapping("/getAllPaginataCustom")
	public ResponseEntity<Page<DTO>> getallcustom(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("idAmministratore") String id ) {
		return new ResponseEntity<>(service.getAllPaginataCustom(PageRequest.of(pageNumber, pageSize), id), HttpStatus.OK);
	}

	@GetMapping("/getAllPaginataAdmin")
	public ResponseEntity<Page<DTO>> getalladmin(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("amministratore") String id ) {
		return new ResponseEntity<>(service.getAllPaginataAdmin(PageRequest.of(pageNumber, pageSize), id), HttpStatus.OK);
	}



}
