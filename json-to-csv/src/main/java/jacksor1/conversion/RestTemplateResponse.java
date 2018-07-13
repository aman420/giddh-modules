/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jacksor1.conversion;
import java.util.Collections;
import org.springframework.http.*;
import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;



/**
 *
 * @author aman
 */
public class RestTemplateResponse {
           public static String RestTemplateConversion()
           {
               
		RestTemplate restTemplate = new RestTemplate();
                //MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
                //converter.setObjectMapper(new ObjectMapper());
                String url="http://apitest.giddh.com/company/newsncindore15000172022770ygv88/trial-balance?refresh=true";
                HttpHeaders headers=new HttpHeaders();
                headers.set("Auth-key","WBYPV0XQMaYnUc3RldcJnkDhJhE42JHB7q-bQ942xlMpt0qc72ClEI7q6oxr0rzNMLUIarqftZ55TlKZkpp2yEE7sMAmgM5s3R8h4bYUKbM=");
                headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
                headers.setContentType((MediaType.APPLICATION_JSON));
                //headers.setContentType(Collections.singleTonList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity=new HttpEntity<String>(headers);
                ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                return response.getBody();
                        
           }
}

    
                            
                

