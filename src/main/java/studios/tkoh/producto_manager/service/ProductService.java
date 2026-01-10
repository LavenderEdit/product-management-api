package studios.tkoh.producto_manager.service;

import studios.tkoh.producto_manager.dto.*;
import java.util.List;

/**
 *
 * @author Studios TKOH!
 */
public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO createProduct(ProductRequest request);

    List<ProductResponseDTO> createProductsBatch(List<ProductRequest> requests);

    ProductResponseDTO adjustStock(Long productId, MovementRequest request);

    List<ProductResponseDTO> getLowStockAlerts();

    List<ProductExcelDTO> getExcelReportData();

    List<MovementExcelDTO> getMovementReportData();

    List<PersonDTO> searchPersonnel(String query);

    List<MovementResponseDTO> getProductMovements(Long productId);
}
