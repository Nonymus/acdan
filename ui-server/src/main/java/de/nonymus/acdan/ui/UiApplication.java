package de.nonymus.acdan.ui;

import de.nonymus.acdan.ui.filters.CsrfHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class UiApplication extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .logout()
                .and()
            .antMatcher("/**").authorizeRequests()
                // Allow anonymous access to static content
                .antMatchers("/index.html", "/views/**", "/scripts/**", "/styles/**", "/bower_components/**", "/", "/login").permitAll()
            .anyRequest().authenticated()
                .and()
            .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and()
            .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
        // @formatter:on
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


    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }
}
