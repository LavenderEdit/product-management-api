package studios.tkoh.producto_manager.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import studios.tkoh.producto_manager.model.Product;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

    boolean existsByCode(String code);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategory(String category);

    List<Product> findByStock(Integer stock);

    List<Product> findByStockLessThanEqual(Integer amount);

    @Query("SELECT p FROM Product p WHERE p.stock <= p.minStock")
    List<Product> findProductsWithLowStock();
}
