package com.nagarro.bookstore.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.bookstore.services.BookServiceImpl;

@Service
public class RestClientUtil {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * reads property media.coverage.uri from application.properties evaluates
	 * to https://jsonplaceholder.typicode.com/posts
	 */
	@Value(value = "${media.coverage.uri}")
	private String mediaCoverageURI;

	/**
	 * Gets the list of all media coverage.
	 *
	 * @return the list containing media coverage records
	 */
	public List<Map<String, Object>> getAllMediaCoverage() {
		LOGGER.debug("resttemplate get call to uri :  {}",mediaCoverageURI);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> reponses = (List<Map<String, Object>>) restTemplate.getForObject(mediaCoverageURI,
				List.class);

		if (null == reponses) {
			return new ArrayList<>(0);
		}
		return reponses;
	}

}
