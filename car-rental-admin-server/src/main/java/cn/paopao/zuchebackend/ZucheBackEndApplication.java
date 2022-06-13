package cn.paopao.zuchebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZucheBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZucheBackEndApplication.class, args);
    }

}
