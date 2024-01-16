package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoleggioDTO {

    private Long idnoleggio;

    private UserDTO user;

    private AttrezzaturaDTO idattrezzatura;  // Chiave esterna

    private Date startDate;

    private Date endDate;

}
