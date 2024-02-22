package it.contrader.prenotazioneservice.feignClient;

import it.contrader.prenotazioneservice.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authservice", configuration = FeignClientConfig.class)
public interface UserFeignClient {
    @GetMapping("/auth/read")
    UserDTO read(@RequestParam("id") Long id);

    @GetMapping("/auth/findByUsername")
    UserDTO findByUsername(@RequestParam("username") String username);
}


@Slf4j
@Component
class UserFallBack implements FallbackFactory<UserFeignClient> {


    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public UserDTO read(Long id) {
                throw new RuntimeException("Errore durante la lettura dell'amministratore" + cause);
            }

            @Override
            public UserDTO findByUsername(String username) {
                throw new RuntimeException("Errore durante la lettura dell'amministratore By Username" + cause);
            }

        };
    }
}
