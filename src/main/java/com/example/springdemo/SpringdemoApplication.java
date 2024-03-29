package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringdemoApplication {

        @GetMapping("/message")
        public String welcome(){
                return "Awesome!! your application is successfully deployed to Kubernetes!!";
        }

        public static void main(String[] args) {
                SpringApplication.run(SpringdemoApplication.class, args);
        }

}
