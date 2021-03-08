package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class DemoApplication {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${site.title}")
    private String siteTitle;

    @Value("${site.description}")
    private String siteDescription;

    private String getTimeNow() {
       return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/site")
    public Site getSite() {
        return new Site(siteTitle, siteDescription);
    }

    @GetMapping("/health")
    Health getHealth() { return new Health("OK", getTimeNow()); }

    @GetMapping("/hello")
    public Greeting sayHello(@RequestParam(value="myName", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/time")
    public TimeNow getTime() {
        return new TimeNow(String.format("Time is now: %s", getTimeNow()));
    }



}
