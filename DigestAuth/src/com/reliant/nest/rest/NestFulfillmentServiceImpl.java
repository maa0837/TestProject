package com.reliant.nest.rest;

import org.springframework.stereotype.Service;

//@PropertySources({ @PropertySource("classpath:properties/environment.properties") })
@Service
public class NestFulfillmentServiceImpl {
	/*@Value("${ws.endPointForPlaceOrder}")
	private String defaultUriForPlaceOrder;

	@Value("${ws.getDetailOrderList}")
	private String getDetailOrderList;

	@Autowired
	RestTemplate restTemplate;

	// private static final Logger logger =
	// LogManager.getLogger(NestFulfillmentServiceImpl.class);
	public String placeNestOrders(String jsonOrderReq, String batch_id) {
		String restResponse = "";
		try {

			UriComponentsBuilder builder = UriComponentsBuilder	.fromUriString(defaultUriForPlaceOrder + "/orders/" + batch_id);
			final URI uri = builder.build().toUri();
			// logger.info("NEST API URL::::"+uri);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(jsonOrderReq, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";
		} catch (HttpStatusCodeException ex) {
			String err = ex.getResponseBodyAsString();
			// logger.info("ERROR OCCURED CALLING THE REST CALL FOR THE URL:::::::"+ex);
			// logger.info(err);
		} catch (RestClientException ex) {
			// logger.info("ERROR OCCURED CALLING THE REST CALL FOR THE URL::::::"+ex);
		}
		return restResponse;
	}

	public String getDetailOrderListImpl(String batch_id) {

		// Gson gson = new Gson();
		String restResponse = null;
		String url = "defaultUriForGetOrderStatus";

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getDetailOrderList + "/orders/?" + batch_id);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
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
		return restResponse;
	}*/

}
