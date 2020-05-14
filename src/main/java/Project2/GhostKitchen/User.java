package Project2.GhostKitchen;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String Username;
    private String Password;
    private String fName;
    private String lName;
    private String email;
    private String Street;
    private String City;
    private String State;
    private String Zip;

    protected User() {}

    public User(String username, String password, String fName, String lName,
                String email, String street, String city, String state, String zip) {
        this.Username = username;
        this.Password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.Street = street;
        this.City = city;
        this.State = state;
        this.Zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getfName() { return fName; }

    public void setfName(String fName) { this.fName = fName; }

    public String getlName() { return lName; }

    public void setlName(String lName) { this.lName = lName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getStreet() { return Street; }

    public void setStreet(String street) { Street = street; }

    public String getCity() { return City; }

    public void setCity(String city) { City = city; }

    public String getState() { return State; }

    public void setState(String state) { State = state; }

    public String getZip() { return Zip; }

    public void setZip(String zip) { Zip = zip; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", Street='" + Street + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip='" + Zip + '\'' +
                '}';
    }
}