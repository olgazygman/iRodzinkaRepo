import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import controller.Lista;
import controller.Uzytkownik;


public class IntegrationTest {

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void getUzytkownik() {
	    String uzytkownikUrl = "http://localhost:8080/uzytkownik/1";

	    ParameterizedTypeReference<Resource<Uzytkownik>> responseType = new ParameterizedTypeReference<Resource<Uzytkownik>>() {};

	    ResponseEntity<Resource<Uzytkownik>> responseEntity = 
	    		restTemplate.exchange(uzytkownikUrl, HttpMethod.GET, null, responseType);

	    Uzytkownik uzytkownik = responseEntity.getBody().getContent();
	    assertEquals("Ernest", uzytkownik.getImie());
	}
	
	@Test
	public void getLista() {
	    String listaUrl = "http://localhost:8080/lista/1";

	    ParameterizedTypeReference<Resource<Lista>> responseType = new ParameterizedTypeReference<Resource<Lista>>() {};

	    ResponseEntity<Resource<Lista>> responseEntity = 
	    		restTemplate.exchange(listaUrl, HttpMethod.GET, null, responseType);

	    Lista lista = responseEntity.getBody().getContent();
	    assertEquals(25, lista.getIlosc());
	}
	
	
}
