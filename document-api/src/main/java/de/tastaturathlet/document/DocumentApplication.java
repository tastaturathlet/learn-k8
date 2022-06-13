package de.tastaturathlet.document;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DocumentApplication {

  public static void main(String[] args) {
    SpringApplication.run(DocumentApplication.class, args);
  }

}
