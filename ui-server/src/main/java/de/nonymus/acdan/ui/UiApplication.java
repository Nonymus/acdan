package de.nonymus.acdan.ui;

import de.nonymus.acdan.ui.filters.CsrfHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class UiApplication extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /* @formatter:off */
    http.
      httpBasic()
        .disable()
      .authorizeRequests()
        .anyRequest().permitAll()
        .and()
      .csrf()
        .csrfTokenRepository(repository())
        .and()
      .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    /* @formatter:on */
  }

  private CsrfTokenRepository repository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("X-XSRF-TOKEN");
    return repository;
  }

  public static void main(String[] args) {
    SpringApplication.run(UiApplication.class, args);
  }
}
