package org.loose.fis.authentication.model;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @org.junit.jupiter.api.Test
    void getUsername() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.getUsername();
    }

    @org.junit.jupiter.api.Test
    void setUsername() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.setUsername("test2");
    }

    @org.junit.jupiter.api.Test
    void getPassword() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.getPassword();
    }

    @org.junit.jupiter.api.Test
    void setPassword() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.setPassword("12345");
    }

    @org.junit.jupiter.api.Test
    void getLastName() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.getLastName();
    }

    @org.junit.jupiter.api.Test
    void setLastName() {
    }

    @org.junit.jupiter.api.Test
    void getFirstName() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.getFirstName();
    }

    @org.junit.jupiter.api.Test
    void setFirstName() {
    }

    @org.junit.jupiter.api.Test
    void getGender() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.getGender();

    }

    @org.junit.jupiter.api.Test
    void setGender() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        User tester1= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.equals(tester1);
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.hashCode();
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.toString();
    }

    @org.junit.jupiter.api.Test
    void getDate() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.getDate();
    }

    @org.junit.jupiter.api.Test
    void setDate() {
        User tester= new User("test","12345678","12345","12345678","Male","12.10.2022");
        tester.setDate("12.12.2022");
    }
}