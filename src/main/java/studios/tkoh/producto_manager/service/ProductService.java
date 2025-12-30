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

    ProductResponseDTO adjustStock(Long productId, String type, Integer quantity, String reason);

    List<ProductResponseDTO> getLowStockAlerts();

    List<ProductExcelDTO> getExcelReportData();
}
