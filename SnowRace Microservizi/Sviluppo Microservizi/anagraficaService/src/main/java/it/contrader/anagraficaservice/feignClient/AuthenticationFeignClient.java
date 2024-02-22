package it.contrader.anagraficaservice.feignClient;

import it.contrader.anagraficaservice.dto.UserDTO;
import it.contrader.anagraficaservice.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "authservice", configuration = FeignClientConfig.class)
public interface AuthenticationFeignClient {
    @GetMapping("/auth/read")
    UserDTO read(@RequestParam("id") Long id);

    @GetMapping("/auth/getAll")
    List<UserDTO> getAll();
}

@Slf4j
@Component
class AuthenticationFallback implements FallbackFactory<AuthenticationFeignClient> {

    @Override
    public AuthenticationFeignClient create(Throwable cause) {
        return new AuthenticationFeignClient() {
            @Override
            public UserDTO read(Long id) {
                throw new RuntimeException("Errore durante la lettura dello user - ERROR: " + cause);
            }

            @Override
            public List<UserDTO> getAll() {
                throw new RuntimeException("Errore durante la lettura degli user - ERROR: " + cause);
            }
        };

    }
}

