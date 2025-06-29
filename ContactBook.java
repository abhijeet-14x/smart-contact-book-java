import java.util.*;

class Contact {
    String name;
    Set<String> phoneNumbers;

    Contact(String name) {
        this.name = name;
        this.phoneNumbers = new HashSet<>();
    }

    void addPhoneNumber(String number) {
        phoneNumbers.add(number);
    }

    void removePhoneNumber(String number) {
        phoneNumbers.remove(number);
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Phone Numbers: " + phoneNumbers);
    }
}

public class ContactBook {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Contact> contacts = new TreeMap<>(); // Sorted by name

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Smart Contact Book ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact by Name");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: searchContact(); break;
                case 4: updateContact(); break;
                case 5: deleteContact(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void addContact() {
        System.out.print("Enter contact name: ");
        String name = sc.nextLine().trim();
        Contact contact = contacts.getOrDefault(name, new Contact(name));
        
        System.out.print("Enter number of phone numbers to add: ");
        int n = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter phone number: ");
            contact.addPhoneNumber(sc.nextLine().trim());
        }

        contacts.put(name, contact);
        System.out.println("Contact saved successfully!");
    }

    static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println("\n--- All Contacts ---");
        for (Contact c : contacts.values()) {
            c.display();
            System.out.println("--------------");
        }
    }

    static void searchContact() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine().trim();

        boolean found = false;
        for (String key : contacts.keySet()) {
            if (key.toLowerCase().startsWith(name.toLowerCase())) {
                contacts.get(key).display();
                System.out.println("--------------");
                found = true;
            }
        }

        if (!found) System.out.println("No matching contacts found.");
    }

    static void updateContact() {
        System.out.print("Enter name to update: ");
        String name = sc.nextLine().trim();
        if (!contacts.containsKey(name)) {
            System.out.println("Contact not found.");
            return;
        }
        Contact contact = contacts.get(name);
        System.out.println("1. Add new number\n2. Remove a number");
        int ch = sc.nextInt(); sc.nextLine();
        if (ch == 1) {
            System.out.print("Enter new number to add: ");
            contact.addPhoneNumber(sc.nextLine().trim());
        } else if (ch == 2) {
            System.out.print("Enter number to remove: ");
            contact.removePhoneNumber(sc.nextLine().trim());
        } else {
            System.out.println("Invalid option.");
            return;
        }
        System.out.println("Contact updated.");
    }

    static void deleteContact() {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine().trim();
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Contact deleted.");
        } else {
            System.out.println("Contact not found.");
        }
    }
}
