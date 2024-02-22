package it.contrader.prenotazioneservice.feignClient;

import it.contrader.prenotazioneservice.dto.PistaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pistservice", configuration = FeignClientConfig.class)
public interface PistaFeignClient {
    @GetMapping("/pista/read")
    PistaDTO read(@RequestParam("id") long id);

}


@Slf4j
@Component
class PistaFallBack implements FallbackFactory<PistaFeignClient> {


    @Override
    public PistaFeignClient create(Throwable cause) {
        return new PistaFeignClient() {
            @Override
            public PistaDTO read(long id) {
                throw new RuntimeException("Errore durante la lettura dell'amministratore" + cause);
            }

        };
    }
}
