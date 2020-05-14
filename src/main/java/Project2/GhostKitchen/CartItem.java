package Project2.GhostKitchen;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CartItem {
    @Id
    private Long id;
    private int Quantity;
    private String Name;
    private String Description;
    private double price;

    protected CartItem() {}

    public CartItem(Long id, int Quantity, String name, String description, Double price) {
        this.id =id;
        this.Quantity = Quantity;
        this.Name = name;
        this.Description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() { return Quantity; }

    public void setQuantity(int quantity) { Quantity = quantity; }

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