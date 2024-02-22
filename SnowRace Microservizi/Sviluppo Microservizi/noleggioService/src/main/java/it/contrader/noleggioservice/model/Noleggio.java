package it.contrader.noleggioservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Noleggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnoleggio;

    private Long idattrezzatura;

    private String username;

    private LocalDate startDate;

    private LocalDate endDate;
}
