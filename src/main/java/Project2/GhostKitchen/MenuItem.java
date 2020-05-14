package Project2.GhostKitchen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long restaurantID;
    private String Name;
    private String Description;
    private double price;

    protected MenuItem() {}

    public MenuItem(Long restaurantID, String name, String description, Double price) {
        this.restaurantID = restaurantID;
        this.Name = name;
        this.Description = description;
        this.price = price;
    }

    public CartItem menuToCart(CartRepository c){
        int quantity=1;
        if(c.findById(id).isPresent()){
            quantity += c.findById(id).get().getQuantity();
        }
        CartItem newCartItem= new CartItem(getId(), quantity,getName(),getDescription(),getPrice());
        return newCartItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantID() { return restaurantID; }

    public void setRestaurantID(Long restaurantID) { this.restaurantID = restaurantID; }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}