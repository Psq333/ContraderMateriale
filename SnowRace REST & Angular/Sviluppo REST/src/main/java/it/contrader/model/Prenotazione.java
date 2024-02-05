package it.contrader.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idPrenotazione;


    private Date dataInizio;

    private Date dataFine;

    @ManyToOne
    @JoinColumn(name = "idPista", nullable = true) //"idPista" sarà il nome della colonna nel Db che viene generato qui come chiave esterna
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Pista pista; // attributo di tipo Pista il nome 'idPista' qui andrà a matcharsi con quello sotto @Id in Pista

    @ManyToOne
    @JoinColumn (name= "idUser")
    private User user;

    @PreRemove
    private void primaDiRimuovere() {
        // Imposta manualmente il campo idPista a NULL prima della rimozione della Pista
        this.pista = null;
    }
}
