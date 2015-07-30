package com.zs.springmongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *
 * https://spring.io/guides/gs/accessing-data-mongodb/
 * 
 * result:
 * create collection customer in default db
 * insert 
 * {"_id": ObjectID("55..."), "_class" : "com.zs.springmongo.Customer", "firstName" : "Alice", "lastName":"Smith"}
 * {"_id": ObjectID("55..."), "_class" : "com.zs.springmongo.Customer", "firstName" : "Bob", "lastName":"Smith"}
 * find()
 * find({"firstName":"Alice"})
 * find({"lastName":"Smith"})
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private CustomerRepository repository;

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
                System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
                System.out.println(customer);
        }

    }
}
