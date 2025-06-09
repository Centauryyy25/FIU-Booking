package fiu.bookingapp.models;

public class User {
    private int id;
    private String name;
    private String password;
    private String role;

    public User(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // Getter
    public int getId() { return id; }
    public String getUsername() { return name; }
    public String getRole() { return role; }

    // Optional: Setter jika perlu
}
