package it.contrader.impiantoservice.feignClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.FeignException;
import it.contrader.impiantoservice.dto.UserDTO;
import it.contrader.impiantoservice.exceptions.NotExistAdminException;
import it.contrader.impiantoservice.exceptions.NotFoundImpiantoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authservice", configuration = FeignClientConfig.class, fallbackFactory = UserFallBack.class)
public interface UserFeignClient {
    @GetMapping("/auth/read")
    UserDTO read(@RequestParam("id") Long id);

    @GetMapping("/auth/readusername")
    ResponseEntity<UserDTO> readusername(@RequestParam("username") String username);
}


@Slf4j
@Component
class UserFallBack implements FallbackFactory<UserFeignClient> {


    @Override
    public UserFeignClient create(Throwable cause) {

        return new UserFeignClient() {
            @Override
            public UserDTO read(@RequestParam("id") Long id) {
                if (cause instanceof FeignException.FeignClientException.NotFound) {
                    throw new NotExistAdminException("L'admin non esiste");
                }
                throw new NotExistAdminException("....");

            }


            @Override
            public ResponseEntity<UserDTO> readusername(String username) {
                if (cause instanceof FeignException.FeignClientException.NotFound) {
                    throw new NotExistAdminException("L'amministratore by readUsername non esiste");
                }
                throw new RuntimeException(cause);
            }

        };
    }
}
