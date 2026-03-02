package edu.snhu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    @Test
    void addContact_uniqueId_success() {
        ContactService service = new ContactService();
        Contact c = new Contact("1", "Ash", "A", "8065551234", "123 Main St");

        service.addContact(c);

        assertNotNull(service.getContact("1"));
        assertEquals("Ash", service.getContact("1").getFirstName());
    }

    @Test
    void addContact_duplicateId_throws() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        assertThrows(IllegalArgumentException.class,
                () -> service.addContact(new Contact("1", "John", "Doe", "8065559999", "456 Another St")));
    }

    @Test
    void addContact_nullContact_throws() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void deleteContact_existingId_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        service.deleteContact("1");

        assertNull(service.getContact("1"));
    }

    @Test
    void deleteContact_missingId_throws() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("nope"));
    }

    @Test
    void updateFirstName_existingId_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        service.updateFirstName("1", "Ashley");

        assertEquals("Ashley", service.getContact("1").getFirstName());
        assertEquals("1", service.getContact("1").getContactId()); // ID not updatable
    }

    @Test
    void updateLastName_existingId_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        service.updateLastName("1", "Almanza");

        assertEquals("Almanza", service.getContact("1").getLastName());
    }

    @Test
    void updatePhone_existingId_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        service.updatePhone("1", "8065550000");

        assertEquals("8065550000", service.getContact("1").getPhone());
    }

    @Test
    void updateAddress_existingId_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        service.updateAddress("1", "456 Another St");

        assertEquals("456 Another St", service.getContact("1").getAddress());
    }

    @Test
    void updateOnMissingContactId_throws() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("404", "Ashley"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("404", "Almanza"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("404", "8065550000"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("404", "456 Another St"));
    }

    @Test
    void updateWithInvalidValues_throws() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Ash", "A", "8065551234", "123 Main St"));

        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", "ABCDEFGHIJK"));

        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", "ABCDEFGHIJK"));

        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "80655A1234"));

        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1",
                "1234567890123456789012345678901"));
    }
}
