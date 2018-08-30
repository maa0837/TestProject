package com.reliant.nest.test;

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

public class DigestAuthenticationTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		DefaultHttpClient httpclient2 = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id=RELIANT_909406");
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

				UsernamePasswordCredentials creds = new UsernamePasswordCredentials("reliant_test_user", "N3stP@ssw0rd");
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
	}
}
