package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String color;
    private String describe;
    private int idCategory;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity, String color, String describe, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.describe = describe;
        this.idCategory = idCategory;
    }

    public Product(int id, String name, double price, int quantity, String color, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.idCategory = idCategory;
    }


    public Product(String name, double price, int quantity, String color, String describe, int idCategory) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.describe = describe;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", color='" + color + '\'' +
                ", describe='" + describe + '\'' +
                ", idCategory=" + idCategory +
                '}';
    }
}
