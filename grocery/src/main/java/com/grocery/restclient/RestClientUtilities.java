package com.grocery.restclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestClientUtilities {
	
	
		public JsonNode retrievePostResponseObject(JsonNode node, String urlEndpoint) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.postForEntity(urlEndpoint, request, Object.class);
			Object objects = responseEntity.getBody();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode responseObject = mapper.convertValue(objects, JsonNode.class);

			return responseObject;
		}

		
		public JsonNode retrieveGetResponseObject(String urlEndpoint) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity(urlEndpoint, Object.class);

			Object objects = responseEntity.getBody();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode responseObject = mapper.convertValue(objects, JsonNode.class);

			return responseObject;
		}

}
