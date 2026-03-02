package edu.snhu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    // ---------- Happy path ----------
    @Test
    void createContact_validData_success() {
        Contact c = new Contact("12345", "Ash", "A", "8065551234", "123 Main St");

        assertEquals("12345", c.getContactId());
        assertEquals("Ash", c.getFirstName());
        assertEquals("A", c.getLastName());
        assertEquals("8065551234", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void createContact_boundaryLengths_success() {
        // ID 10 chars, first 10, last 10, address 30, phone 10 digits
        Contact c = new Contact(
                "1234567890",
                "ABCDEFGHIJ",
                "KLMNOPQRST",
                "0123456789",
                "123456789012345678901234567890"
        );

        assertEquals("1234567890", c.getContactId());
        assertEquals("ABCDEFGHIJ", c.getFirstName());
        assertEquals("KLMNOPQRST", c.getLastName());
        assertEquals("0123456789", c.getPhone());
        assertEquals("123456789012345678901234567890", c.getAddress());
    }

    // ---------- ID validation ----------
    @Test
    void createContact_nullId_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Ash", "A", "8065551234", "123 Main St"));
    }

    @Test
    void createContact_idTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Ash", "A", "8065551234", "123 Main St"));
    }

    // ---------- First name validation ----------
    @Test
    void createContact_nullFirstName_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "A", "8065551234", "123 Main St"));
    }

    @Test
    void createContact_firstNameTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "ABCDEFGHIJK", "A", "8065551234", "123 Main St"));
    }

    // ---------- Last name validation ----------
    @Test
    void createContact_nullLastName_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", null, "8065551234", "123 Main St"));
    }

    @Test
    void createContact_lastNameTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "ABCDEFGHIJK", "8065551234", "123 Main St"));
    }

    // ---------- Phone validation ----------
    @Test
    void createContact_nullPhone_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "A", null, "123 Main St"));
    }

    @Test
    void createContact_phoneNot10Digits_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "A", "123456789", "123 Main St")); // 9 digits
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "A", "12345678901", "123 Main St")); // 11 digits
    }

    @Test
    void createContact_phoneHasNonDigits_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "A", "80655A1234", "123 Main St"));
    }

    // ---------- Address validation ----------
    @Test
    void createContact_nullAddress_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "A", "8065551234", null));
    }

    @Test
    void createContact_addressTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Ash", "A", "8065551234",
                        "1234567890123456789012345678901")); // 31 chars
    }

    // ---------- Update validation ----------
    @Test
    void updateFields_validChanges_success() {
        Contact c = new Contact("1", "Ash", "A", "8065551234", "123 Main St");

        c.setFirstName("Ashley");
        c.setLastName("Almanza");
        c.setPhone("8065550000");
        c.setAddress("456 Another St");

        assertEquals("1", c.getContactId()); // ID stays the same
        assertEquals("Ashley", c.getFirstName());
        assertEquals("Almanza", c.getLastName());
        assertEquals("8065550000", c.getPhone());
        assertEquals("456 Another St", c.getAddress());
    }

    @Test
    void updateFirstName_invalid_throws() {
        Contact c = new Contact("1", "Ash", "A", "8065551234", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("ABCDEFGHIJK"));
    }

    @Test
    void updateLastName_invalid_throws() {
        Contact c = new Contact("1", "Ash", "A", "8065551234", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("ABCDEFGHIJK"));
    }

    @Test
    void updatePhone_invalid_throws() {
        Contact c = new Contact("1", "Ash", "A", "8065551234", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("80655A1234"));
    }

    @Test
    void updateAddress_invalid_throws() {
        Contact c = new Contact("1", "Ash", "A", "8065551234", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("1234567890123456789012345678901"));
    }
}
