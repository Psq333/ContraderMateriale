package it.contrader.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //dichiara questa classe come entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
        @JoinColumn (name = " idImpianto") //foreign key
        private Impianto idImpianto;

}
