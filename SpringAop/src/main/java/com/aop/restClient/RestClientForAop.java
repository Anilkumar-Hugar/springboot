package com.aop.restClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.aop.entity.Person;

public class RestClientForAop {
	private static Logger logger = LoggerFactory.getLogger(RestClientForAop.class);
	public static final RestTemplate restTemplate = new RestTemplate();;
	public static final String URIFORPOST = "http://localhost:8080/api/create";
	public static final String URIFORGET = "http://localhost:8080/api/getById?id={id}";
	public static final String URIFORGETALL = "http://localhost:8080/api/getAll";
	public static final String URIFORDELETE="http://localhost:8080/api/delete?id={id}";

	public static void main(String[] args) {
//		Person person = new Person();
//		person.setName("Anil");
//		person.setCity("hyd");
//		Person object = restTemplate.postForObject(URIFORPOST, person, Person.class);
//		if(object!=null) {
//			System.out.println("data stored to the db");
//		}
		Map<String, Integer> map = new HashMap<>();
		map.put("id", 502);
		Person forObject = restTemplate.getForObject(URIFORGET, Person.class, map);
		logger.info("Response: {}", forObject);

		Person[] person = restTemplate.getForObject(URIFORGETALL, Person[].class);
		logger.info("Response: {}", Arrays.toString(person));
		restTemplate.delete(URIFORDELETE,map);
		logger.info("details delete with id: {}",forObject.getId());
	}
}
