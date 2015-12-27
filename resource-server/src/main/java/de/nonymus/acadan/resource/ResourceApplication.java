package de.nonymus.acadan.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableResourceServer
public class ResourceApplication extends WebSecurityConfigurerAdapter{

    @RequestMapping("/")
    public Map<String, Object> home() {
        Map<String,Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "What's up?");
        return model;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
