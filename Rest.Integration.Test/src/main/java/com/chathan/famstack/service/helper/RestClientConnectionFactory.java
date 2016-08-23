package com.chathan.famstack.service.helper;

import java.net.URISyntaxException;

import groovyx.net.http.RESTClient;
public class RestClientConnectionFactory {
	
    public synchronized static RESTClient createResource(String endpoint, String contentType) throws URISyntaxException {
    	RESTClient restClient = new RESTClient(endpoint);
    	restClient.setContentType(contentType);
        return restClient;
    }
    
    private RestClientConnectionFactory(){
    	
    }
}
