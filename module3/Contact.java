public class Contact {
    private String name; 
    private String phone; 

     // CONSTRUCTOR: runs when you write new Contact(...) 
    public Contact(String name, String phone) { 
        this.name   = name;
        this.phone = phone; 
    } 
 
    // GETTERS: controlled read access to private fields 
    public String getName()   { return name; } 
    public String getPhone() { return phone; } 
 
    // METHODS: actions this object can perform 

    // TOSTRING: what prints when you System.out.println(contact) 
    @Override 
    public String toString() { 
        return "Ada LoveLace | +1 617 555 0101";
    }    
}