package testEx.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Socks")
public class Sock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    @NotEmpty(message = "Color should not be empty!")
    private String color;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity should be greater than 0")
    private int quantity;

    @Column(name = "cottonpart")
    @Range(min = 1, max = 100)
    private int cottonPart;

    public Sock(String color, int quantity, int cottonPart) {
        this.color = color;
        this.quantity = quantity;
        this.cottonPart = cottonPart;
    }

    public Sock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }
}
