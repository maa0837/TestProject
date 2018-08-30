package com.reliant.nest.controller;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.reliant.nest.service.NestService;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

@Controller
public class NestController {
	
	@Autowired
	NestService nestservice;
	
	@RequestMapping("/")
	public ModelAndView defaultPage() {
		System.out.println("NestController.defaultPage()");
		try {
			String url = "https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406";
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(
					"https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406");
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
			System.out.println("NestController.defaultPage() : " + responseEntity.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("index");

	}

	@RequestMapping("/test1")
	public ModelAndView test1() {
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

			String xml = restTemplate.getForObject(url, String.class);
			System.out.println("Done : " + xml);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("index");
	}

	@RequestMapping("/test2")
	public ModelAndView test2() throws ClientProtocolException, IOException {
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			DefaultHttpClient httpclient2 = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(
					"https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406");
			System.out.println("Requesting : " + httpget.getURI());

			try {
				// Initial request without credentials returns "HTTP/1.1 401 Unauthorized"
				HttpResponse response = httpclient.execute(httpget);
				System.out.println(response.getStatusLine());

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {

					// Get current current "WWW-Authenticate" header from response
					// WWW-Authenticate:Digest realm="My Test Realm", qop="auth",
					// nonce="cdcf6cbe6ee17ae0790ed399935997e8",
					// opaque="ae40d7c8ca6a35af15460d352be5e71c"
					Header authHeader = response.getFirstHeader(AUTH.WWW_AUTH);
					System.out.println("authHeader = " + authHeader);

					DigestScheme digestScheme = new DigestScheme();

					// Parse realm, nonce sent by server.
					digestScheme.processChallenge(authHeader);

					UsernamePasswordCredentials creds = new UsernamePasswordCredentials("reliant_test_user",
							"N3stP@ssw0rd");
					httpget.addHeader(digestScheme.authenticate(creds, httpget));

					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					String responseBody = httpclient2.execute(httpget, responseHandler);
					System.out.println("responseBody : " + responseBody);
				}

			} catch (MalformedChallengeException e) {
				e.printStackTrace();
			} catch (AuthenticationException e) {
				e.printStackTrace();
			} finally {
				httpclient.getConnectionManager().shutdown();
				httpclient2.getConnectionManager().shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("index");
	}

	@RequestMapping("/test3")
	public ModelAndView test3() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String xml = restTemplate.getForObject("https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406",String.class);
			System.out.println("NestController.test3() : " + xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("index");
	}

	@RequestMapping("/batchid/{batch_id}")
	public String getNestBatchId(@PathVariable("batch_id") String batch_id) {
		System.out.println("NestController.getNestBatchId() || batch_id : " + batch_id);
		String response="";
		try {
			response = nestservice.getNestBatchId(batch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("NestController.getNestBatchId() || response : " + response);
		return "index";
	}
}
