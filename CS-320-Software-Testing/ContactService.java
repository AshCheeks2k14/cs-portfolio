package edu.snhu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("Contact cannot be null");

        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID already exists: " + id);
        }
        contacts.put(id, contact);
    }

    public void deleteContact(String contactId) {
        requireId(contactId);
        if (contacts.remove(contactId) == null) {
            throw new IllegalArgumentException("No contact found with ID: " + contactId);
        }
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact c = getRequired(contactId);
        c.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact c = getRequired(contactId);
        c.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact c = getRequired(contactId);
        c.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact c = getRequired(contactId);
        c.setAddress(address);
    }

    // Helpful for unit tests / verification
    public Contact getContact(String contactId) {
        requireId(contactId);
        return contacts.get(contactId);
    }

    // Optional: read-only view for debugging/tests
    public Map<String, Contact> getAllContacts() {
        return Collections.unmodifiableMap(contacts);
    }

    private Contact getRequired(String contactId) {
        requireId(contactId);
        Contact c = contacts.get(contactId);
        if (c == null) throw new IllegalArgumentException("No contact found with ID: " + contactId);
        return c;
    }

    private static void requireId(String contactId) {
        if (contactId == null || contactId.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact ID cannot be null/empty");
        }
    }
}
