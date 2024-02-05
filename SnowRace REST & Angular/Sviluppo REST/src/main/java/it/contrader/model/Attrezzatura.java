package it.contrader.model;
import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import it.contrader.dto.ImpiantoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "attrezzatura")
public class Attrezzatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idAttrezzatura;
    private String nome;
    private String descrizione;
    private Double prezzo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn (name = " idImpianto") //foreign key
    private Impianto idImpianto;


}
