package it.contrader.noleggioservice.feignClient;

import it.contrader.noleggioservice.dto.AttrezzaturaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "attreservice", configuration = FeignClientConfig.class)
public interface AttrezzaturaFeignClient {
    @GetMapping("/attrezzatura/read")
    AttrezzaturaDTO read(@RequestParam("id") long idAttrezzatura);

}


@Slf4j
@Component
class AttrezzaturaFallBack implements FallbackFactory<AttrezzaturaFeignClient> {


    @Override
    public AttrezzaturaFeignClient create(Throwable cause) {
        return new AttrezzaturaFeignClient() {
            @Override
            public AttrezzaturaDTO read(long id) {
                throw new RuntimeException("Errore durante la lettura dell'amministratore" + cause);
            }

        };
    }
}
