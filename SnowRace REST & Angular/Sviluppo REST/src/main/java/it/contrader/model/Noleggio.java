package it.contrader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Noleggio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnoleggio;

    @ManyToOne
    @JoinColumn(name = "id")
    private User username;


    @ManyToOne
    @JoinColumn(name = "idattrezzatura")
    private Attrezzatura idattrezzatura;


    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

}
