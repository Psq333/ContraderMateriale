package it.contrader.attrezzaturaService.feignClient;

import it.contrader.attrezzaturaService.dto.ImpiantoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "impiantoService", configuration = FeignClientConfig.class)
public interface ImpiantoFeignClient {

    @GetMapping("/impianto/read")
    ImpiantoDTO read(@RequestParam("id") Long id);


}

@Slf4j
@Component
class ImpiantoFallback implements FallbackFactory<ImpiantoFeignClient> {

    @Override
    public ImpiantoFeignClient create(Throwable cause) {
        return new ImpiantoFeignClient() {
            @Override
            public ImpiantoDTO read(Long id) {
                throw new RuntimeException("Errore durante la creazione dell'anagrafica - ERROR: " + cause);
            }


        };
    }
}

