package com.ecnu.g03.pethospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Shen Lei
 * @date Created in 2021/3
 */
@SpringBootApplication
@EnableAsync
public class PetHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetHospitalApplication.class, args);
    }

}
