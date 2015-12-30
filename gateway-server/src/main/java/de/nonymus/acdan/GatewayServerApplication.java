package de.nonymus.acdan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
@EnableZuulProxy
public class GatewayServerApplication extends WebSecurityConfigurerAdapter {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                // Whitelist static UI
                .antMatchers("/", "/ui/**/").permitAll()
                // Require authentication for anything else
                .anyRequest().authenticated();
    }

    public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
