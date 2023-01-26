package refactoring.data;

import java.util.Objects;

public class Product {

    private final String name;
    private final long price;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String toHtml() {
        return name + "\t" + price + "</br>";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Product otherProduct = (Product) other;
        return price == otherProduct.price &&
                Objects.equals(name, otherProduct.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product {" +
                "  name: " + name +
                " price: " + price +
                "}";
    }
}
