package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
public class HashSets {

    /**
     * The User class has an overridden hashCode() method that computes the hash code based on the username field.
     * The equals() method checks for equality based on the username.
     * The HashSet is used to store User objects.
     * When checking for the existence of a user using contains, the HashSet uses the overridden hashCode()
     * and equals() methods to determine whether an equivalent user exists in the set.
     */
    @Test
    void contextLoads() {
        Set<User> userSet = new HashSet<>();

        // Adding users
        User johnDoe = new User("john_doe");
        User janeDoe = new User("jane_doe");

        userSet.add(johnDoe);
        userSet.add(janeDoe);

        // Checking existence
        User queriedUser = new User("john_doe");
        boolean exists = userSet.contains(queriedUser);

        System.err.println("User 'john_doe' exists: " + exists);
    }

    class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        @Override
        public int hashCode() {
            return Objects.hash(username);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            User otherUser = (User) obj;
            return Objects.equals(username, otherUser.username);
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
