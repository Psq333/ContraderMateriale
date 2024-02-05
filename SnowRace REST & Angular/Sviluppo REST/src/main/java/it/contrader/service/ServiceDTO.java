package it.contrader.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceDTO<DTO> {
	
	public Iterable<DTO> getAll();

	public DTO read(long id);
	
	public DTO insert (DTO dto);
	
	public DTO update (DTO dto);
	
	public void delete (long id);

	public Page<DTO> getAllPaginata(Pageable pageable);
	public Page<DTO> getAllPaginataCustom(Pageable pageable, String id);
	public Page<DTO> getAllPaginataAdmin(Pageable pageable, String id);
}
