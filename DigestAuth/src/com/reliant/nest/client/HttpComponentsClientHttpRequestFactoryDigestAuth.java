package com.reliant.nest.client;

import java.net.URI;
import java.util.Random;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class HttpComponentsClientHttpRequestFactoryDigestAuth extends HttpComponentsClientHttpRequestFactory {

	HttpHost host;

	public HttpComponentsClientHttpRequestFactoryDigestAuth(HttpHost host, HttpClient httpClient) {
		super(httpClient);
		this.host = host;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		return createHttpContext();
	}

	private HttpContext createHttpContext() {
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate DIGEST scheme object, initialize it and add it to the local auth
		// cache
		DigestScheme digestAuth = new DigestScheme();
		// If we already know the realm name


		digestAuth.overrideParamter("realm", "Nest Store Digest Credentials");
		digestAuth.overrideParamter("nonce", Long.toString(new Random().nextLong(), 36));
		
		authCache.put(host, digestAuth);

		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
		return localcontext;
	}
	
	@Test
	public void whenSecuredRestApiIsConsumed_then200OK() {
		System.out.println("HttpComponentsClientHttpRequestFactoryDigestAuth.whenSecuredRestApiIsConsumed_then200OK()");
		RestTemplate restTemplate = new RestTemplate();
	    String uri = "https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406";
	    ResponseEntity<String> entity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
	    System.out.println("HttpComponentsClientHttpRequestFactoryDigestAuth.whenSecuredRestApiIsConsumed_then200OK() : " + entity.getStatusCode());	    
	}
}
