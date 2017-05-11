/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maanpaa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private SalesForceService salesForceService;
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
//      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick, body FROM messagesReceived");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("At: " + rs.getTimestamp("tick") + " Response: " + rs.getString("body"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

 @RequestMapping(value="/sms", produces = "application/xml;charset=UTF-8")
 @ResponseBody
 String respondToSms( @RequestParam("Body") String smsInboundBody,  @RequestParam("From") String smsPhoneNumber) throws JsonProcessingException{
	 Message message;
	 writeSMSintoDB(smsInboundBody + " From Phonenumber: " + smsPhoneNumber);
	 System.out.println(smsInboundBody + " From Phonenumber: " + smsPhoneNumber);
	 if(Integer.parseInt(smsInboundBody)==1) { // for DEMO this is the marketing flow
	     message = new Message.Builder()
	             .body(new Body("Option: " + smsInboundBody + " Capital Group representative will contact you shortly. Thank you."))
	             .build();
	 }
	 else { // for DEMO we assume this is wholesaler flow
		 message = new Message.Builder()
				 .body(new Body("Thanks you. Please REPLY 1-5 How useful were the insights provided by the PIT crew?"))
	             .build();
		 salesForceService.postSalesFlowRatingToSalesForce("id", "5");
	 }

     MessagingResponse twiml = new MessagingResponse.Builder()
             .message(message)
             .build();

     //response.setContentType("application/xml");

     try {
         //response.getWriter().print(twiml.toXml());
    	 return(twiml.toXml());
     } catch (TwiMLException e) {
         e.printStackTrace();
     }
     return "";
 }
	 void writeSMSintoDB(String body){
		    try (Connection connection = dataSource.getConnection()) {
		        Statement stmt = connection.createStatement();
		        System.out.println("Database updates");
		        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS messagesReceived (tick timestamp, body text)");
		        System.out.println(stmt.executeUpdate("INSERT INTO messagesReceived VALUES (now(), '"+body+"')"));
		        //ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

//		        ArrayList<String> output = new ArrayList<String>();
//		        while (rs.next()) {
//		          output.add("Read from DB: " + rs.getTimestamp("tick"));
//		        }
//
//		        model.put("records", output);
//		        return "db";
		      } catch (Exception e) {
		    	  System.out.println(e.getMessage());

		    	  e.printStackTrace();
//		//        model.put("message", e.getMessage());
//		        return "error";
		      }		 
		 
		 
		 
		 
	 }
 }
  
