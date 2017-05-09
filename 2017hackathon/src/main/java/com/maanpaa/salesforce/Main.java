package com.maanpaa.salesforce;


import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {

    static final String USERNAME     = "im@capgroup.com.dev";
    static final String PASSWORD     = "March@20177txoL2intApwxkhFI7PX3qmPO";
    static final String LOGINURL     = "https://test.salesforce.com";
    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    static final String CLIENTID     = "3MVG9sLbBxQYwWqui2n5ePG2HPxaKhHnHgyxPc6PuRQQ6bXbwhCCmKI06PMNMPk2kET7.2QaFZA8Px1NwFgBp";
    static final String CLIENTSECRET = "7189007724647142878";

    public static void main(String[] args) {
    	
    	
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	HttpHost proxy = new HttpHost("irvcache.capgroup.com",8080);
    	RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
    	
        // Assemble the login request URL
        String loginURL = LOGINURL + 
                          GRANTSERVICE + 
                          "&client_id=" + CLIENTID + 
                          "&client_secret=" + CLIENTSECRET +
                          "&username=" + USERNAME +
                          "&password=" + PASSWORD;
        
        
        //loginURL =  URLEncoder.encode(loginURL);
        
        // Login requests must be POSTs
        HttpPost httpPost = new HttpPost(loginURL);
        httpPost.setConfig(config);
        HttpResponse response = null;
        
        System.out.println("loginURL: " + loginURL);
        
        try {
            // Execute the login POST request
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException cpException) {
            // Handle protocol exception
        	System.out.println("ClientProtocolException: "+cpException.getMessage());
        } catch (IOException ioException) {
            // Handle system IO exception
        	System.out.println("IOException: "+ioException.getMessage());
        }

        // verify response is HTTP OK
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("Error authenticating to Force.com: "+statusCode);
            // Error is in EntityUtils.toString(response.getEntity()) 
            //return;
        }

        String getResult = null;
        try {
            getResult = EntityUtils.toString(response.getEntity());
        } catch (IOException ioException) {
            // Handle system IO exception
        }
        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;
        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        } catch (JSONException jsonException) {
            // Handle JSON exception
        }
        System.out.println(response.getStatusLine());
        System.out.println("Successful login");
        System.out.println("  instance URL: "+loginInstanceUrl);
        System.out.println("  access token/session ID: "+loginAccessToken);
        
        JSONObject task = new JSONObject();
        try {
        	task.put("contactPhoneNumber", "8186189493");
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
        HttpPost httpost = new HttpPost(loginInstanceUrl+ "/services/apexrest/HackathonTaskService/createTask");
        httpost.setConfig(config);
        httpost.addHeader("Authorization", "OAuth " + loginAccessToken);
        
        StringEntity messageEntity = new StringEntity( task.toString(),
				ContentType.create("application/json"));

		httpost.setEntity(messageEntity);
		
		try {
            // Execute the login POST request
			CloseableHttpResponse closeableresponse = httpclient.execute(httpost); 
			System.out.println("Response Status line :" + closeableresponse.getStatusLine());
        } catch (ClientProtocolException cpException) {
            // Handle protocol exception
        	System.out.println("ClientProtocolException: "+cpException.getMessage());
        } catch (IOException ioException) {
            // Handle system IO exception
        	System.out.println("IOException: "+ioException.getMessage());
        }
		  
		 
		  
        // release connection
        httpPost.releaseConnection();
    }
    
    

}
