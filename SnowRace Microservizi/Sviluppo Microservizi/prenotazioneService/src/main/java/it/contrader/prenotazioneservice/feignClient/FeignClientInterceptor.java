package it.contrader.prenotazioneservice.feignClient;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignClientInterceptor implements RequestInterceptor{

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKER_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null){
            HttpServletRequest request = attributes.getRequest();
            String jwt = (String) request.getSession().getAttribute(AUTHORIZATION_HEADER);
            System.out.println(jwt);
            if(jwt != null){
                template.header(AUTHORIZATION_HEADER ,jwt);
            }
        }
    }
}
