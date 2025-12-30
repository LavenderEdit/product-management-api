package studios.tkoh.producto_manager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Studios TKOH!
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, length = 500)
    private String name;

    private String unit;

    private String category;

    @Builder.Default
    private Integer stock = 5;

    @Column(name = "min_stock")
    private Integer minStock;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public Product(String code, String name, String unit, String category, Integer stock) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.category = category;
        this.stock = stock;
        this.minStock = 5;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        this.lastUpdated = LocalDateTime.now();
    }

    public void addStock(int quantity) {
        this.stock += quantity;
        this.lastUpdated = LocalDateTime.now();
    }

    public void removeStock(int quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
            this.lastUpdated = LocalDateTime.now();
        } else {
            throw new RuntimeException("Stock insuficiente para el producto: " + this.name);
        }
    }
}
