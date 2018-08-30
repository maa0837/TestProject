package com.reliant.nest.serviceimpl;

import java.util.ArrayList;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.reliant.nest.dao.NestDao;
import com.reliant.nest.entity.NestEntity;
import com.reliant.nest.service.NestService;

@Service
@Transactional
public class NestServiceImpl implements NestService {

	@Autowired
	NestDao nestdao;
	
	NestEntity entity = new NestEntity();
	
	@Override
	public String getNestBatchId(String batch_id) {
		System.out.println("NestServiceImpl.getNestBatchId() || batch_id : " + batch_id);
		String response = "";
		try {
			String RELATIVE_IDENTITY_URL = "https://integration.store.ft.nest.com/v1/bulk/reliant_test/orders/?batch_id="
					+ batch_id;
			System.out.println("NestServiceImpl.getNestBatchId() || url : " + RELATIVE_IDENTITY_URL);

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

			response = restTemplate.getForObject(url, String.class);
			System.out.println("NestServiceImpl.getNestBatchId() || response : " + response);

			JSONObject jsonObject = new JSONObject(response);
			JSONArray jsonArray = jsonObject.getJSONArray("items");
			System.out.println("NestServiceImpl.getNestBatchId() || jsonArray : " + jsonArray);		
			
			System.out.println("========================= || DONE 1 || =========================");
			
			String status = jsonObject.getString("status");
			System.out.println("NestServiceImpl.getNestBatchId() || status : " + status);
			
			
			
			/*for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					String key = keys.next();
					System.out.println("Key :" + key + "  Value :" + json.get(key));
				}

			}*/

			ArrayList<NestEntity> al = new ArrayList<NestEntity>();			
			for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String ORDER_DATE = jsonObject1.optString("ORDER_DATE");
                Integer QUANTITY = (Integer) jsonObject1.get("QUANTITY");
                String PHONE = jsonObject1.optString("PHONE");
                String STATE = jsonObject1.optString("STATE");
                String COMMENTS = jsonObject1.optString("COMMENTS");
                String LAST_NAME = jsonObject1.optString("LAST_NAME");
                String EMAIL = jsonObject1.optString("EMAIL");
                String BATCH_ID = jsonObject1.optString("BATCH_ID");
                String FIRST_NAME = jsonObject1.optString("FIRST_NAME");
                Integer ORIGINAL_INDEX = (Integer)jsonObject1.get("ORIGINAL_INDEX");
                String STATUS = jsonObject1.optString("STATUS");
                String CITY = jsonObject1.optString("CITY");
                String COUNTRY = jsonObject1.optString("COUNTRY");
                String POSTAL_CODE = jsonObject1.optString("POSTAL_CODE");
                String ORDER_NUMBER = jsonObject1.optString("ORDER_NUMBER");
                String ADDRESS1 = jsonObject1.optString("ADDRESS1");
                String SKU = jsonObject1.optString("SKU");
                String ADDRESS2 = jsonObject1.optString("ADDRESS2");
                
                System.out.println("NestServiceImpl.getNestBatchId() || ORDER_DATE : " + ORDER_DATE);
                System.out.println("NestServiceImpl.getNestBatchId() || QUANTITY : " + QUANTITY);
                System.out.println("NestServiceImpl.getNestBatchId() || PHONE : " + PHONE);
                System.out.println("NestServiceImpl.getNestBatchId() || STATE : " + STATE);
                
                entity.setORDER_DATE(ORDER_DATE);
                entity.setQUANTITY(QUANTITY);
                entity.setPHONE(PHONE);
                entity.setSTATE(STATE);
                entity.setCOMMENTS(COMMENTS);
                entity.setLAST_NAME(LAST_NAME);
                entity.setEMAIL(EMAIL);
                entity.setBATCH_ID(BATCH_ID);
                entity.setFIRST_NAME(FIRST_NAME);
                entity.setORIGINAL_INDEX(ORIGINAL_INDEX);
                entity.setSTATUS(STATUS);
                entity.setCITY(CITY);
                entity.setCOUNTRY(COUNTRY);
                entity.setPOSTAL_CODE(POSTAL_CODE);
                entity.setORDER_NUMBER(ORDER_NUMBER);
                entity.setADDRESS1(ADDRESS1);
                entity.setSKU(SKU);
                entity.setADDRESS2(ADDRESS2);
                
                al.add(entity);
            }
			
			System.out.println("NestServiceImpl.getNestBatchId() || entity : " + entity.getFIRST_NAME());
			System.out.println("========================= || DONE 2 || =========================");
			
			nestdao.saveList(entity);
			
			/*List<NestEntity> li = new ArrayList<NestEntity>();
			for(int i = 0, size = jsonArray.length(); i<size; i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				System.out.println("NestServiceImpl.getNestBatchId() || jsonObject1 : " + jsonObject2);				
				
				jsonObject2.put("ORDER_DATE", jsonObject2.get("ORDER_DATE"));
				jsonObject2.put("QUANTITY", jsonObject2.get("QUANTITY"));
				jsonObject2.put("PHONE", jsonObject2.get("PHONE"));
				jsonObject2.put("STATE", jsonObject2.get("STATE"));
				
				String bill = JSONValue.toJSONString(jsonObject2);// JSON to String
				
				System.out.println("NestServiceImpl.getNestBatchId() || jsonObject2 : " + jsonObject2);
				System.out.println("NestServiceImpl.getNestBatchId() || bill : " + bill);
				
				System.out.println("NestServiceImpl.getNestBatchId() || ORDER_DATE : " + jsonObject2.get("ORDER_DATE"));
				System.out.println("NestServiceImpl.getNestBatchId() || QUANTITY : " + jsonObject2.get("QUANTITY"));
				System.out.println("NestServiceImpl.getNestBatchId() || PHONE : " + jsonObject2.get("PHONE"));
				System.out.println("NestServiceImpl.getNestBatchId() || STATE : " + jsonObject2.get("STATE"));
				System.out.println("NestServiceImpl.getNestBatchId() || COMMENTS : " + jsonObject2.get("COMMENTS"));
				System.out.println("NestServiceImpl.getNestBatchId() || LAST_NAME : " + jsonObject2.get("LAST_NAME"));
				System.out.println("NestServiceImpl.getNestBatchId() || EMAIL : " + jsonObject2.get("EMAIL"));
				System.out.println("NestServiceImpl.getNestBatchId() || BATCH_ID : " + jsonObject2.get("BATCH_ID"));
				System.out.println("NestServiceImpl.getNestBatchId() || FIRST_NAME : " + jsonObject2.get("FIRST_NAME"));
				System.out.println("NestServiceImpl.getNestBatchId() || ORIGINAL_INDEX : " + jsonObject2.get("ORIGINAL_INDEX"));
				System.out.println("NestServiceImpl.getNestBatchId() || STATUS : " + jsonObject2.get("STATUS"));
				System.out.println("NestServiceImpl.getNestBatchId() || CITY : " + jsonObject2.get("CITY"));
				System.out.println("NestServiceImpl.getNestBatchId() || COUNTRY : " + jsonObject2.get("COUNTRY"));
				System.out.println("NestServiceImpl.getNestBatchId() || POSTAL_CODE : " + jsonObject2.get("POSTAL_CODE"));
				System.out.println("NestServiceImpl.getNestBatchId() || ORDER_NUMBER : " + jsonObject2.get("ORDER_NUMBER"));
				System.out.println("NestServiceImpl.getNestBatchId() || ADDRESS1 : " + jsonObject2.get("ADDRESS1"));
				System.out.println("NestServiceImpl.getNestBatchId() || SKU : " + jsonObject2.get("SKU"));
				System.out.println("NestServiceImpl.getNestBatchId() || ADDRESS2 : " + jsonObject2.get("ADDRESS2"));
				
				//li.add(nestentity.setORDER_DATE(jsonObject2.get("ORDER_DATE")));
								
				String [] elementNames = JSONObject.getNames(jsonObject2);
				System.out.println("NestServiceImpl.getNestBatchId() || elementNames 1 : " + elementNames);				
				System.out.println("Digest4.main() || Elements NAmes : " + elementNames.length);
				for (String elementName : elementNames) {
					Object value = jsonObject2.get(elementName);
					//System.out.println("Digest4.main() || value : " + value);
					System.out.println("NestServiceImpl.getNestBatchId()");
					System.out.printf("name=%s, value=%s\n", elementName, value);
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
