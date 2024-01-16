package it.contrader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "anagrafica")
public class Anagrafica {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    private String indirizzo;

    private Date dataNascita;

    private String luogoNascita;

    @OneToOne( cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user")
    private User user;
}
