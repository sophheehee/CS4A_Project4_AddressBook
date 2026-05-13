public class Contact {
    private String name;
    private String email;
    private String phoneNumber;
    private String contactType;
    private String city;
    private String tag;

    // Default Constructor
    public Contact() {
        this.name = "(No Entry)";
        this.email = "(No Entry)";
        this.phoneNumber = "(No Entry)";
        this.contactType = "(No Entry)";
        this.city = "(No Entry)";
        this.tag = "(No Entry)";
    }

    // Full Constructor
    public Contact(String name, String email, String phoneNumber, String contactType, String city, String tag) {
        this.name = validateEntry(name);
        this.email = validateEntry(email);
        this.phoneNumber = validateEntry(phoneNumber);
        this.contactType = validateEntry(contactType);
        this.city = validateEntry(city);
        this.tag = validateEntry(tag);
    }

    // Helper function to replace empty/null strings
    private String validateEntry(String value) {
        value = value.replace("=", "");
        //maybe add an exception to check if this was done
        //and warn the user
        if (value == null || value.trim().isEmpty()) {
            return "(No Entry)";
        }
        return value;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateEntry(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validateEntry(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = validateEntry(phoneNumber);
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = validateEntry(contactType);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = validateEntry(city);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = validateEntry(tag);
    }

    @Override
    public String toString() {
        return String.format("%s\n\tPhone: %s\n\tEmail: %s\n\tType: %s\n\tCity: %s\n\tTag: %s", 
            name, phoneNumber, email, contactType, city, tag);
    }
}