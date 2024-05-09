package org.example.javaspringbootbasic2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA 엔티티 객체 생성후 메인 메소드에 추가한 어노테이션

public class JavaSpringBootBasic2Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootBasic2Application.class, args);
    }

}
