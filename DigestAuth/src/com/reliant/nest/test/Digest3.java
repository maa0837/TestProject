package com.reliant.nest.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Digest3 {

	public static void main(String[] args) {
		String restResponse = null;
		String url = "https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406";
		System.out.println("Digest3.main() || url : " + url);
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);			
			System.out.println("NestFulfillmentServiceImpl.getDetailOrderListImpl() || restResponse : " + responseEntity.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
