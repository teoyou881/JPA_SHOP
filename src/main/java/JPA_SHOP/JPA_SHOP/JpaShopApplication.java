package JPA_SHOP.JPA_SHOP;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaShopApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpaShopApplication.class, args);

  }

  @Bean
  Hibernate5JakartaModule hibernate5Module() {
    return new Hibernate5JakartaModule();
  }
}
