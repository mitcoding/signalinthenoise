package com.maanpaa.services;

import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maanpaa.domain.salesforce.VisitResponse;

@RestController
public class UIRestService {

	@Autowired
	SalesForceService salesForceService;
	
	@Autowired
	SMSSender sMSSender;
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/ui")
    public String contact(@RequestParam(value="number", defaultValue="3103090654") String name) throws URISyntaxException, JsonProcessingException {
    	salesForceService.postMarketingFlowStartToSalesForce("3103090654");
    	sMSSender.sendMarketingFlowStart();
    	return "{\"message\":\"received your UI call\"}";
        //return new String(counter.incrementAndGet(),
        //                    String.format(template, name));
    }
    @RequestMapping("/api/salesforce")
    public String salesforce(@RequestParam(value="number", defaultValue="3103090654") String name) throws URISyntaxException {
    	VisitResponse visitResponse = salesForceService.callSalesForce();
    	sMSSender.sendSalesMeetingStart(visitResponse.getVisitDetail() + " Please REPLY rate scale 1-5?", visitResponse.getVisitWLSRPhone());
    	return "Received Meeting Ended Notice From SalesForce";
        //return new String(counter.incrementAndGet(),
        //                    String.format(template, name));
    }
}