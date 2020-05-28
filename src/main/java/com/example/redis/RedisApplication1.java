package com.example.redis;

import com.example.redis.entity.Person;
import com.example.redis.repository.EmployeeDAO;
import com.example.redis.repository.FamilyDAO;
import com.example.redis.repository.FriendDAO;
import com.example.redis.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisApplication1 {
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private FamilyDAO familyDAO;
    @Autowired
    private EmployeeDAO empDAO;
    @Autowired
    private UserDAO userDAO;


    public static void main(String[] args) {
        SpringApplication.run(RedisApplication1.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            System.out.println("--Example of ListOperations--");
            friendDAO.removeKey();
            System.out.println("Total Friends: " + friendDAO.getNumberOfFriends());
            Person p1 = new Person(2, "Ram", 20);
            Person p2 = new Person(1, "Shyam", 30);
            System.out.println("Adding friends");
            friendDAO.addFriend(p1);
            friendDAO.addFriend(p2);
            System.out.println("Total Friends: " + friendDAO.listAllFriends());

            System.out.println();
            System.out.println("--Example of SetOperations--");
            familyDAO.removeKey();
            Person p11 = new Person(101, "Ram", 30);
            Person p12 = new Person(102, "Lakshman", 25);
            Person p13 = new Person(102, "Lakshman", 25);
            familyDAO.addFamilyMembers(p11, p12, p13);
            System.out.println("Number of Family members: " + familyDAO.getNumberOfFamilyMembers());
            System.out.println(familyDAO.getFamilyMembers());
        };
    }

}
