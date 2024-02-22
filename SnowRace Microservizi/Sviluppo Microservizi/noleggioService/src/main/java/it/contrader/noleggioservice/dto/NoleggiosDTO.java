package it.contrader.noleggioservice.dto;

import it.contrader.noleggioservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoleggiosDTO {

    private Long idnoleggio;

    private Long idattrezzatura;

    private String username;

    private LocalDate startDate;

    private LocalDate endDate;
}
