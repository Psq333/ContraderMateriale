package it.contrader.pistaservice.repository;

import it.contrader.pistaservice.model.Pista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PistaRepository extends JpaRepository<Pista, Long> {

    public List<Pista> findByImpianto(Long impianto);
    public  List<Pista> findAllByImpianto(Pageable pageable,Long impianto);
}
