package com.maanpaa.services;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIRestService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public String contact(@RequestParam(value="number", defaultValue="3103090654") String name) {
    	
        //return new String(counter.incrementAndGet(),
        //                    String.format(template, name));
    }
}