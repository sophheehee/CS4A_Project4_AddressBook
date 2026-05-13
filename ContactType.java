
// Enum: ContactType

public enum ContactType {
    PERSON("Person"),
    BUSINESS("Business"),
    VENDOR("Vendor"),
    EMERGENCY("Emergency");

    private final String displayName;

    ContactType(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }


    public static ContactType fromString(String token) {
        if (token == null) return PERSON;
        switch (token.trim()) {
            case "Business":  return BUSINESS;
            case "Vendor":    return VENDOR;
            case "Emergency": return EMERGENCY;
            default:          return PERSON;
        }
    }

    @Override
    public String toString() {
        return displayName;
    }
}
