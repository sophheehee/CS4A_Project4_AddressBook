import java.io.File;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {

            // create file if it does not exist
            File file = new File("contacts.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            // load contacts from file
            ContactManager manager = new ContactManager("contacts.txt");

            boolean done = false;

            // main menu loop
            while (!done) {

                System.out.println("\n===== ADDRESS BOOK MENU =====");
                System.out.println("1. Add Contact");
                System.out.println("2. Edit Contact");
                System.out.println("3. Delete Contact");
                System.out.println("4. View All Contacts");
                System.out.println("5. Search Contacts");
                System.out.println("6. Filter Contacts");
                System.out.println("7. Reports");
                System.out.println("8. Save Contacts");
                System.out.println("9. Exit");

                System.out.print("Choose option: ");
                int choice = Integer.parseInt(input.nextLine());

                // menu options
                if (choice == 1) {
                    addContact(manager, input);
                }

                else if (choice == 2) {
                    editContact(manager, input);
                }

                else if (choice == 3) {
                    manager.deleteContact();
                }

                else if (choice == 4) {
                    System.out.println(manager);
                }

                else if (choice == 5) {
                    manager.searchContacts();
                }

                else if (choice == 6) {
                    manager.filterContacts();
                }

                else if (choice == 7) {
                    showReports(manager);
                }

                else if (choice == 8) {
                    manager.saveToFile();
                    System.out.println("Contacts saved.");
                }

                else if (choice == 9) {
                    manager.saveToFile();
                    done = true;
                    System.out.println("Goodbye.");
                }

                else {
                    System.out.println("Invalid option.");
                }
            }
        }

        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        input.close();
    }

    // add new contact
    public static void addContact(ContactManager manager, Scanner input) {

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Phone Number: ");
        String phone = input.nextLine();

        System.out.print("Contact Type (Person, Business, Vendor, Emergency): ");
        String type = input.nextLine();

        System.out.print("City: ");
        String city = input.nextLine();

        System.out.print("Tag/Group: ");
        String tag = input.nextLine();

        Contact c = new Contact(name, email, phone, type, city, tag);

        manager.contacts.add(c);

        System.out.println("Contact added.");
    }

    // edit contact
    public static void editContact(ContactManager manager, Scanner input) {

        System.out.print("Enter contact name to edit: ");
        String name = input.nextLine();

        int index = manager.getContactIndex(name);

        if (index != -1) {

            Contact c = manager.contacts.get(index);

            System.out.print("New email: ");
            c.setEmail(input.nextLine());

            System.out.print("New phone number: ");
            c.setPhoneNumber(input.nextLine());

            System.out.print("New contact type: ");
            c.setContactType(input.nextLine());

            System.out.print("New city: ");
            c.setCity(input.nextLine());

            System.out.print("New tag/group: ");
            c.setTag(input.nextLine());

            System.out.println("Contact updated.");
        }

        else {
            System.out.println("Contact not found.");
        }
    }

    // reports
    public static void showReports(ContactManager manager) {

        System.out.println("\\n===== REPORTS =====");

        System.out.println("\\nContacts by type:");

        String[] types = {"Person", "Business", "Vendor", "Emergency"};

        for (int i = 0; i < types.length; i++) {

            int count = 0;

            for (int j = 0; j < manager.contacts.size(); j++) {

                Contact c = manager.contacts.get(j);

                if (c.getContactType().equalsIgnoreCase(types[i])) {
                    count++;
                }
            }

            System.out.println(types[i] + ": " + count);
        }
    }
}
