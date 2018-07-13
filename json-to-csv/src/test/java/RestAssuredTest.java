
import io.restassured.RestAssured;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aman
 */
public class RestAssuredTest {
        @Test
        public void testGet() throws URISyntaxException
        {
            URI uri=new URI("http://apitest.giddh.com/company/newsncindore15000172022770ygv88/trial-balance?refresh=true");
            RestAssured.when().get(uri);
        }

   
    
}
