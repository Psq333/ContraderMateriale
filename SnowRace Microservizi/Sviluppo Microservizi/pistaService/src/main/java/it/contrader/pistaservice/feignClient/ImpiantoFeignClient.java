package it.contrader.pistaservice.feignClient;

import it.contrader.pistaservice.dto.ImpiantoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "impiantoService", configuration = FeigClientConfig.class)
public interface ImpiantoFeignClient {

    @GetMapping("/impianto/listaimpiantiadmin")
    List<ImpiantoDTO> listaimpiantiadmin(@RequestParam("amministratore") Long id);
}

@Slf4j
@Component
class ImpiantoFallback implements FallbackFactory<ImpiantoFeignClient> {

    @Override
    public ImpiantoFeignClient create(Throwable cause) {
        return new ImpiantoFeignClient() {
            @Override
            public List<ImpiantoDTO> listaimpiantiadmin(Long id) {
                throw new RuntimeException("Errore durante la creazione dell'anagrafica - ERROR: " + cause);
            }


        };
    }
}

