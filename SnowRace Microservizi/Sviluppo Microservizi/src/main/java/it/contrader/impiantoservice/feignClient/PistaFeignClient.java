package it.contrader.impiantoservice.feignClient;

import it.contrader.impiantoservice.dto.PistaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pistaservice", configuration = FeignClientConfig.class)
public interface PistaFeignClient {
    @GetMapping("/pista/getAllImpiantiAdmin")
    ResponseEntity<List<PistaDTO>> getallimpiantiadmin(@RequestParam("id") String idImpiato);
}


@Slf4j
@Component
class PistaFallBack implements FallbackFactory<PistaFeignClient> {


    @Override
    public PistaFeignClient create(Throwable cause) {
        return new PistaFeignClient() {
            @Override
            public ResponseEntity<List<PistaDTO>> getallimpiantiadmin(String idImpiato) {
                throw new RuntimeException("Errore durante la lettura dell'amministratore" + cause);
            }
        };
    }
}
