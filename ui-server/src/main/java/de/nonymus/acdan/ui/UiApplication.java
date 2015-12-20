package de.nonymus.acdan.ui;

import de.nonymus.acdan.ui.filters.CsrfHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class UiApplication {

    @RequestMapping("/resource")
    private Map<String, Object> home() {
        Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello again!");
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    /**
     * Customize the security configuration for our custom login mechanism
     */
    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic().and()
                    .authorizeRequests()
                    // Allow anonymous access to static content
                    .antMatchers("/index.html", "/views/**", "/scripts/**", "/styles/**", "/bower_components/**", "/")
                    .permitAll().anyRequest()
                    .authenticated().and()
                    // Process csrf information
                    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                    .csrf().csrfTokenRepository(csrfTokenRepository());
        }

        /**
         * Custom repository using the header name expected by angular (which is not the spring default)
         *
         * @return {@link CsrfTokenRepository} using "X-XSRF-TOKEN" header name
         */
        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }
}
