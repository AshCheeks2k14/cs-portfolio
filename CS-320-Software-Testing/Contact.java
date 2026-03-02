package edu.snhu;

import java.util.Objects;

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateContactId(contactId);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    private static void validateContactId(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("Contact ID cannot be null/empty");
        if (id.length() > 10) throw new IllegalArgumentException("Contact ID must be 10 characters or less");
    }

    private static void validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) throw new IllegalArgumentException("First name cannot be null/empty");
        if (firstName.length() > 10) throw new IllegalArgumentException("First name must be 10 characters or less");
    }

    private static void validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) throw new IllegalArgumentException("Last name cannot be null/empty");
        if (lastName.length() > 10) throw new IllegalArgumentException("Last name must be 10 characters or less");
    }

    private static void validatePhone(String phone) {
        if (phone == null) throw new IllegalArgumentException("Phone cannot be null");
        if (!phone.matches("\\d{10}")) throw new IllegalArgumentException("Phone must be exactly 10 digits");
    }

    private static void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) throw new IllegalArgumentException("Address cannot be null/empty");
        if (address.length() > 30) throw new IllegalArgumentException("Address must be 30 characters or less");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
}
