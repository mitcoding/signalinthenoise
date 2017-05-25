package com.maanpaa.services;

import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maanpaa.domain.salesforce.LoginResponse;
import com.maanpaa.domain.salesforce.NotesFromVisitRequest;
import com.maanpaa.domain.salesforce.VisitResponse;
import com.maanpaa.domain.salesforce.WelcomeRequest;
import com.twilio.twiml.Method;

@Service
public class SalesForceService {

    /*static final String USERNAME     = "im@capgroup.com.dev";
    static final String PASSWORD     = "March@20177txoL2intApwxkhFI7PX3qmPO";
    static final String LOGINURL     = "https://test.salesforce.com";
    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    static final String CLIENTID     = "3MVG9sLbBxQYwWqui2n5ePG2HPxaKhHnHgyxPc6PuRQQ6bXbwhCCmKI06PMNMPk2kET7.2QaFZA8Px1NwFgBp";
    static final String CLIENTSECRET = "7189007724647142878";*/
	
	static final String USERNAME     = "";
    static final String PASSWORD     = ";
    static final String LOGINURL     = "https://test.salesforce.com";
    static final String GRANTSERVICE = "";
    static final String CLIENTID     = "";
    static final String CLIENTSECRET = "";


	private RestTemplate restTemplate;
	
	private RestTemplateBuilder restTemplateBuilder;
	
	public SalesForceService(RestTemplateBuilder restTemplateBuilder){
		this.restTemplateBuilder=restTemplateBuilder;
		restTemplate = restTemplateBuilder.build();
		
	}
    public VisitResponse callSalesForce() {

    	String loginURL = LOGINURL + 
                 GRANTSERVICE + 
                 "&client_id=" + CLIENTID + 
                 "&client_secret=" + CLIENTSECRET +
                 "&username=" + USERNAME +
                 "&password=" + PASSWORD;
    	 
    	 
    	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
         
         //ClassLoader classLoader = getClass().getClassLoader();
         //File file = new File(classLoader.getResource(name).getFile());
                
          //       map.add("bundlefile", new HttpEntity<Resource>(new FileSystemResource(file.getAbsolutePath())));
          //       map.add("_noredir_", "_noredir_");
          //       map.add("bundlestartlevel", "20");
         //map.add("action", "install"); 
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    	 
    	 
    	HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
          
    	 //String response = restTemplate.postForObject(url, requestEntity, String.class);
    	
    	 LoginResponse loginResponse = restTemplate.postForObject(loginURL, requestEntity, LoginResponse.class);
    	 
    	 System.out.println(loginResponse.getAccessToken());

    	 
    	 headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
    	   	 
         headers.add("Authorization", "OAuth " + loginResponse.getAccessToken());         
  
         HttpEntity<MultiValueMap<String,Object>> requestEntity2 = new HttpEntity<MultiValueMap<String, Object>>(null, headers);
    	 
    	 
    	 String visitDetailURL = loginResponse.getInstanceUrl() + "/services/apexrest/HackathonVisitService/getVisitDetail?userEmail=aras@capgroup.com";
    	 
    	 //String visitResponse = restTemplate.getForObject(visitDetailURL, String.class);
    	 
    	 //ResponseEntity visitResponse = restTemplate.exchange(visitDetailURL, HttpMethod.GET, requestEntity2, String.class);
 
    	 
    	 HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
         ResponseEntity<VisitResponse> response = restTemplate.exchange(visitDetailURL, HttpMethod.GET, entity, VisitResponse.class);
         System.out.println(response.getBody().getVisitId());
         System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
         return response.getBody();
     	 
    }
	public void postMarketingFlowStartToSalesForce(String phoneNumber) throws JsonProcessingException {

    	String loginURL = LOGINURL + 
                 GRANTSERVICE + 
                 "&client_id=" + CLIENTID + 
                 "&client_secret=" + CLIENTSECRET +
                 "&username=" + USERNAME +
                 "&password=" + PASSWORD;
    	 
    	 
    	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.MULTIPART_FORM_DATA);
         HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
    	 LoginResponse loginResponse = restTemplate.postForObject(loginURL, requestEntity, LoginResponse.class);
    	 
    	 headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);   	   	 
         headers.add("Authorization", "OAuth " + loginResponse.getAccessToken());         

         WelcomeRequest welcomeRequest = new WelcomeRequest();
         welcomeRequest.setContactPhoneNumber(phoneNumber);
         welcomeRequest.setTxtMessage("We received txt from advisor request a call back");
         welcomeRequest.setTaskType("Txt Apt");
         
         
    	 String welcomeURL = loginResponse.getInstanceUrl() + "/services/apexrest/HackathonTaskService/createTask";

    	 ObjectMapper mapper = new ObjectMapper();
    	 String welcomeRequestAsString = mapper.writeValueAsString(welcomeRequest);
    	 
    	 HttpEntity<String> entity = new HttpEntity<String>(welcomeRequestAsString ,headers);   	 
    	 ResponseEntity<String> response = restTemplate.postForEntity(welcomeURL, entity, String.class, "");
    	 System.out.println(response);

    	
    	 
    	 
		
	}
	public void postSalesFlowRatingToSalesForce(String visitId, String notes) throws JsonProcessingException {

    	String loginURL = LOGINURL + 
                 GRANTSERVICE + 
                 "&client_id=" + CLIENTID + 
                 "&client_secret=" + CLIENTSECRET +
                 "&username=" + USERNAME +
                 "&password=" + PASSWORD;
    	 
    	 
    	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.MULTIPART_FORM_DATA);
         HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
    	 LoginResponse loginResponse = restTemplate.postForObject(loginURL, requestEntity, LoginResponse.class);
    	 
    	 headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);   	   	 
         headers.add("Authorization", "OAuth " + loginResponse.getAccessToken());         

         NotesFromVisitRequest notesFromVisitRequest = new NotesFromVisitRequest();
         notesFromVisitRequest.setVisitId("a0Hc0000008XLtzEAG"); // TODO change to visitID from previous call
         notesFromVisitRequest.setVisitNotes("Visit was rated as 5."); //change to real rating
         
         
    	 String welcomeURL = loginResponse.getInstanceUrl() + "/services/apexrest/HackathonVisitService/updateVisit";

    	 ObjectMapper mapper = new ObjectMapper();
    	 String welcomeRequestAsString = mapper.writeValueAsString(notesFromVisitRequest);
    	 
    	 HttpEntity<String> entity = new HttpEntity<String>(welcomeRequestAsString ,headers);   	 
    	 ResponseEntity<String> response = restTemplate.postForEntity(welcomeURL, entity, String.class, "");
    	 System.out.println(response);

    	
    	 
    	 
		
	}

    }
    
    