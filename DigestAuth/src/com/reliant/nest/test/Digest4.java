package com.reliant.nest.test;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class Digest4 {

	public static void main(String[] args) {

		try {
			String RELATIVE_IDENTITY_URL = "https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406";
			RestTemplate restTemplate;
			Credentials credentials;

			// 1. Set credentials
			credentials = new UsernamePasswordCredentials("reliant_test_user", "N3stP@ssw0rd");

			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, credentials);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// 2. Bind credentialsProvider to httpClient
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
			CloseableHttpClient httpClient = httpClientBuilder.build();

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

			// 3. create restTemplate
			restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(factory);

			// 4. restTemplate execute
			String url = RELATIVE_IDENTITY_URL;

			String response = restTemplate.getForObject(url, String.class);
			System.out.println("Digest4.main() || response : " + response);
			
			JSONObject jsonObject = new JSONObject(response);
			JSONArray jsonArray = jsonObject.getJSONArray("items");
			System.out.println("Digest4.main() || jsonArray : " + jsonArray);
			
			String status = jsonObject.getString("status");
			System.out.println("Digest4.main() || status : " + status);
			
			
			for(int i = 0, size = jsonArray.length(); i<size; i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				String [] elementNames = JSONObject.getNames(jsonObject2);
				System.out.println("Digest4.main() || Elements NAmes : " + elementNames.length);
				for (String elementName : elementNames) {
					Object value = jsonObject2.get(elementName);
					//System.out.println("Digest4.main() || value : " + value);
					System.out.printf("name=%s, value=%s\n", elementName, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
