package it.contrader.authenticationservice.feignClient;

import it.contrader.authenticationservice.dto.AnagraficaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "anagservice", configuration = FeignClientConfig.class)
public interface AnagraficaFeignClient {

    @PostMapping("/anag/registerAnagrafica")
    AnagraficaDTO register(@RequestBody AnagraficaDTO anagraficaDTO);
}

@Slf4j
@Component
class AnagraficaFallback implements FallbackFactory<AnagraficaFeignClient> {

    @Override
    public AnagraficaFeignClient create(Throwable cause) {
        return new AnagraficaFeignClient() {
            @Override
            public AnagraficaDTO register(AnagraficaDTO anagraficaDTO) {
                throw new RuntimeException("Errore durante la creazione dell'anagrafica - ERROR: " + cause);
            }
        };

    }
}

