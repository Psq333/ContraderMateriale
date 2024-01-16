package it.contrader.model;

import com.fasterxml.jackson.annotation.*;
import it.contrader.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "impianti")
public class Impianto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idImpianto;

    private String nome;

    private String descrizione;

    private String luogo;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_amministratore") //E' la chiave dell'entità che sta in questa tabella, che è FOREIGN KEY
    private User amministratore;

    @OneToMany(mappedBy = "impianto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pista> piste = new ArrayList<Pista>();

}
