package it.contrader.pistaservice.feignClient;

import it.contrader.pistaservice.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authservice", configuration = FeigClientConfig.class)
public interface UserFeignClient {

    @GetMapping("/auth/readusername")
    UserDTO readusername(@RequestParam("username") String username);
}

@Slf4j
@Component
class UserFallback implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public UserDTO readusername(String username) {
                throw new RuntimeException("Errore durante la creazione dell'anagrafica - ERROR: " + cause);
            }
        };
    }
}

