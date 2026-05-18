import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
//import contact.java

public class ContactManager {
    ArrayList<Contact> contacts = new ArrayList<>();
    File contactsFile;

    public ContactManager(String contactsFileName) throws FileNotFoundException{
        contactsFile = new File(contactsFileName);
        if(!contactsFile.exists()){
            throw new FileNotFoundException("Could not find the file " + contactsFileName);
        }

        Scanner reader = new Scanner(contactsFile);
        while(reader.hasNextLine()){
            String data = reader.nextLine();
            String[] parts = data.split("=");
            Contact c = new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            contacts.add(c);
        }        

        reader.close();
    }

    //this doenst have to be a toString it just prints out
    //all the contacts but it seems to make homie happy so idk
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<contacts.size(); i++){
            sb.append((contacts.get(i)).toString()).append("\n");
        }
        return sb.toString();
    }

    public void saveToFile() {
    //delete file then write to it
    try (PrintWriter writer = new PrintWriter(contactsFile)) {
        
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            
            String line = c.getName() + "=" +
                          c.getEmail() + "=" +
                          c.getPhoneNumber() + "=" +
                          c.getContactType() + "=" +
                          c.getCity() + "=" +
                          c.getTag();
            
            // Write the line to the file
            writer.println(line);
        }
        
    } catch (IOException e) {
        System.err.println("Error saving to file: " + e.getMessage());
    }}

    // lil searchy helper - sophie 
    public int getContactIndex(String name){
            for(int i = 0; i < contacts.size(); i++){
                if (contacts.get(i).getName().equalsIgnoreCase(name)){
                    return i; 
                }
            }
            return -1; 
    }

     // search for a contact - sophie
    public void searchContacts() {
        //make scanner 
        Scanner input = new Scanner(System.in);

        // print choicessss
        System.out.println("Search by:");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Phone Number");
        System.out.print("Enter choice: ");

        int choice = input.nextInt();
        input.nextLine(); // clear enter 
        System.out.print("Enter search term: ");
        String searchTerm = input.nextLine();

        boolean found = false;

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            String valueToCheck = "";

            if (choice == 1) { //by name
                valueToCheck = c.getName();
            }
            else if (choice == 2) { //by email
                valueToCheck = c.getEmail();
            }
            else if (choice == 3) { //by phoen number
                valueToCheck = c.getPhoneNumber();
            }
            else {
                System.out.println("Invalid choice.");
                return;
            }

            if (valueToCheck.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        }
        input.close();
}

    // delete a contact - sophie
    public void deleteContact(){
        Scanner input = new Scanner(System.in); 

        System.out.println("Enter contact name to delete:");
        String name = input.nextLine(); 

        int index = getContactIndex(name); 
        if (index != -1){
            System.out.println("Deleting: "); 
            System.out.println(contacts.get(index)); 

            contacts.remove(index);
            saveToFile();
            System.out.println("Contact Deleted!"); 

        }else{
            System.out.println("Contact not found"); 
        }
        input.close();

    }

    // filter contacts - sophie 
    public void filterContacts() {
        // crate scanner 
        Scanner input = new Scanner(System.in);

        // print thy choices 
        System.out.println("Filter by:");
        System.out.println("1. Contact Type");
        System.out.println("2. City");
        System.out.println("3. Tag");
        System.out.print("Enter choice: ");
        
        //get input 
        int choice = input.nextInt();
        input.nextLine(); // clear enter
        System.out.print("Enter filter term: ");
        String filterTerm = input.nextLine();

        boolean found = false;

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            String valueToCheck = "";

            if (choice == 1) { //by type
                valueToCheck = c.getContactType();
            }
            else if (choice == 2) { //by city 
                valueToCheck = c.getCity();
            }
            else if (choice == 3) { //by tag 
                valueToCheck = c.getTag();
            }
            else {
                System.out.println("Invalid choice.");
                return;
            }

            if (valueToCheck.equalsIgnoreCase(filterTerm)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        }
        input.close();
}
}


