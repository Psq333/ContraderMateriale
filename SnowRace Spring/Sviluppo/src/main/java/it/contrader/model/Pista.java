package it.contrader.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.contrader.dto.ImpiantoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Segnala che la classe è un’entità
 //Va a generare getter, setter, costruttore con tutti gli attributi, e metodi come hashCode e toString()
 //si usa solamente per indicare che il nome della classe è diverso dal nome della tabella.
@Builder
@Table(name = "piste")
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPista;
    private String nome;
    private String difficolta;
    private double prezzo;
    private int prenotazioniMax;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "impianto")
    private Impianto impianto;



}
