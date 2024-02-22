package it.contrader.noleggioservice.repository;
import it.contrader.noleggioservice.model.Noleggio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

    Page<Noleggio> findByUsername(Pageable pageable, String username);


}
