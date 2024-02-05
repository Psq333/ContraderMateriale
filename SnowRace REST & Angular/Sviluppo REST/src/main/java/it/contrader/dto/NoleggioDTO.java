package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoleggioDTO {

    private Long idnoleggio;

    private String username;

    private AttrezzaturaDTO idattrezzatura;  // Chiave esterna

    private Date startDate;

    private Date endDate;

}
