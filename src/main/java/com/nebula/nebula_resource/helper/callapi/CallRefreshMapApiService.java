package com.nebula.nebula_resource.helper.callapi;

import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CallRefreshMapApiService {
    public void sendRefreshMapRequest(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://ai-alb-1793409698.ap-northeast-2.elb.amazonaws.com";
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        ResponseEntity<?> result = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }
}
