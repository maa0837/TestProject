package com.reliant.nest.client;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import java.net.URI;
import java.util.Random;

public class HttpComponentsClientDigestAuth extends HttpComponentsClientHttpRequestFactory
{
	
	HttpHost host;

	public HttpComponentsClientDigestAuth(final HttpHost host, final HttpClient httpClient) {
		super(httpClient);
		this.host = host;
	}

	@Override
	protected HttpContext createHttpContext(final HttpMethod httpMethod, final URI uri) {
		return createHttpContext();
	}

	private HttpContext createHttpContext() {
		// Create AuthCache instance
		final AuthCache authCache = new BasicAuthCache();
		// Generate DIGEST scheme object, initialize it and add it to the local auth
		// cache
		final DigestScheme digestAuth = new DigestScheme();

		digestAuth.overrideParamter("realm", "Nest Store Digest Credentials");
		digestAuth.overrideParamter("nonce", Long.toString(new Random().nextLong(), 36));

		authCache.put(host, digestAuth);

		// Add AuthCache to the execution context
		final BasicHttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
		return httpContext;
	}
}
