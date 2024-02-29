import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

public class ContactManagerTask3 {
    private static final String CONTACT_FILE = "contacts.ser";
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        loadContacts(); // Load contacts from file (if any)

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nContact Manager");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    editContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContacts(); // Save contacts to file before exiting
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Contact contact = new Contact(name, phoneNumber, email);
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Contact List:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private static void editContact(Scanner scanner) {
        viewContacts();
        if (contacts.isEmpty()) {
            return;
        }

        System.out.print("Enter the name of the contact you want to edit: ");
        String name = scanner.nextLine();

        Contact foundContact = null;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                foundContact = contact;
                break;
            }
        }

        if (foundContact == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Enter new phone number (leave empty to keep current): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.isEmpty()) {
                foundContact.setPhoneNumber(phoneNumber);
            }

            System.out.println("Enter new email (leave empty to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                foundContact.setEmail(email);
            }

            System.out.println("Contact updated successfully.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        viewContacts();
        if (contacts.isEmpty()) {
            return;
        }

        System.out.print("Enter the name of the contact you want to delete: ");
        String name = scanner.nextLine();

        Contact foundContact = null;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                foundContact = contact;
                break;
            }
        }

        if (foundContact == null) {
            System.out.println("Contact not found.");
        } else {
            contacts.remove(foundContact);
            System.out.println("Contact deleted successfully.");
        }
    }

    private static void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONTACT_FILE))) {
            contacts = (ArrayList<Contact>) ois.readObject();
            System.out.println("Contacts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No contacts file found. Starting with an empty contact list.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    private static void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTACT_FILE))) {
            oos.writeObject(contacts);
            System.out.println("Contacts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }
}
