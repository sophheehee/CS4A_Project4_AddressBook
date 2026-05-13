import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

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
    }
}
}
