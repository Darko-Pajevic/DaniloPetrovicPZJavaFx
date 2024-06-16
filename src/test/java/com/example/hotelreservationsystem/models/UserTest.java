package com.example.hotelreservationsystem.models;

import com.example.hotelreservationsystem.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUser() {
        User user = new User(1, "Test User", "Test Password", "test@example.com");

        assertEquals(1, user.getId());
        assertEquals("Test User", user.getUsername());
        assertEquals("Test Password", user.getPassword());
        assertEquals("test@example.com", user.getEmail());

        user.setId(2);
        user.setUsername("New Test User");
        user.setPassword("New Test Password");
        user.setEmail("newtest@example.com");

        assertEquals(2, user.getId());
        assertEquals("New Test User", user.getUsername());
        assertEquals("New Test Password", user.getPassword());
        assertEquals("newtest@example.com", user.getEmail());

        String expectedToString = "User{id=2, username='New Test User', email='newtest@example.com'}";
        assertEquals(expectedToString, user.toString());
    }
}