package de.nonymus.acdan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GatewayServerApplication.class)
@WebAppConfiguration
public class GatewayServerApplicationTests {

  @Test
  public void contextLoads() {
  }

}
