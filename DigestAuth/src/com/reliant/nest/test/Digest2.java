package com.reliant.nest.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Digest2 {

	public static void main(String[] args) {
		String restResponse = null;
		String url = "https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406";

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
			System.out.println("Digest2.main() || builder : " + builder);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			System.out.println("Digest2.main()");
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
			System.out.println("Digest2.main() || responseEntity : " + responseEntity);
			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";
			System.out.println("NestFulfillmentServiceImpl.getDetailOrderListImpl() || restResponse : " + restResponse);
		} catch (HttpStatusCodeException ex) {
			String err = ex.getResponseBodyAsString();
			// logger.info("ERROR OCCURED CALLING THE REST CALL FOR THE
			// URL:::::"+url+"::"+ex);
			// logger.info(err);
		} catch (RestClientException ex) {
			// logger.info("ERROR OCCURED CALLING THE REST CALL FOR THE
			// URL:::::"+url+"::"+ex);
		}

	}

}
