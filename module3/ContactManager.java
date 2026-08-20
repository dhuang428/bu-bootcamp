import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager {
    static HashMap<String, Contact> contacts = new HashMap<>(); // need to make this global so can access it in removeContact method

    public static void main(String[] args) { 
        // Step 4: add contacts here
        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 111 1111"));
        contacts.put("Alan Turing", new Contact("Alan Turing", "+1 617 222 2222"));
        contacts.put("Charles Babbage", new Contact("Charles Babbage", "+1 617 333 3333"));
        contacts.put("Tim Beners-Lee", new Contact("Tim Beners-Lee", "+1 617 444 4444"));
        contacts.put("John Backus", new Contact("John Backus", "+1 617 555 5555"));                                

        // Step 5: look up a contact
        if(contacts.get("A Lovelace") != null) {
            System.out.println(contacts.get("Ada Lovelace"));
        }
        else {
            System.out.println("Contact not found");
        }
 
        // Step 6: print sorted list
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
    
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");
        for(Contact cont : sorted) {
            System.out.println(cont.getName() + " | " + cont.getPhone());
        }

        // Optional Next Step: call method to remove contact from the HashMap using name
        removeContact("John Backus");
        //contacts.remove("John Backus");

        ArrayList<Contact> sortedAgain = new ArrayList<>(contacts.values());
    
        sortedAgain.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts After Removal of One ===");
        for(Contact contAgain : sortedAgain) {
            System.out.println(contAgain.getName() + " | " + contAgain.getPhone());
        }
    }

    public static void removeContact(String name) {
        if(contacts.remove(name) == null){
            System.out.println("Name to remove does not exist in HashMap");
        }
        else {
            contacts.remove(name);
        }
    }
}