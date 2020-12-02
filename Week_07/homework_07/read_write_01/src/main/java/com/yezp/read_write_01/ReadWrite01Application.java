package com.yezp.read_write_01;

import com.yezp.read_write_01.domain.Person;
import com.yezp.read_write_01.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadWrite01Application  implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(ReadWrite01Application.class, args);
    }

    @Override
    public void run(String... args) {
        Person person = new Person(1, "robot-01");
        personService.insertPerson(person);
        System.out.println(personService.getPersonList());
    }
}
