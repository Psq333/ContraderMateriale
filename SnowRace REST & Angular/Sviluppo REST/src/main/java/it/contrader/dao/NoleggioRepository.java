package it.contrader.dao;

import it.contrader.model.Noleggio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

    Noleggio findByUsernameAndStartDateBetweenAndIdattrezzatura(String username, Date startDate, Date endDate, Long idattrezzatura);

    Page<Noleggio> findByUsername_username(Pageable pageable, String username);

    public List<Noleggio> findByUsername_Id(Long id);
    public List<Noleggio> findByIdattrezzatura_idAttrezzatura(Long idAttrezzatura);

}