package studios.tkoh.producto_manager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studios.tkoh.producto_manager.dto.MovementExcelDTO;
import studios.tkoh.producto_manager.dto.MovementRequest;
import studios.tkoh.producto_manager.dto.MovementResponseDTO;
import studios.tkoh.producto_manager.dto.PersonDTO;
import studios.tkoh.producto_manager.dto.ProductExcelDTO;
import studios.tkoh.producto_manager.dto.ProductRequest;
import studios.tkoh.producto_manager.dto.ProductResponseDTO;
import studios.tkoh.producto_manager.service.ProductService;

/**
 *
 * @author Studios TKOH!
 */
@RestController
@RequestMapping("/api/kardex")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/products/batch")
    public ResponseEntity<List<ProductResponseDTO>> createBatch(@RequestBody List<ProductRequest> batchRequest) {
        return ResponseEntity.ok(productService.createProductsBatch(batchRequest));
    }

    @PostMapping("/products/{id}/movement")
    public ResponseEntity<ProductResponseDTO> moveStock(
            @PathVariable Long id,
            @RequestBody MovementRequest request
    ) {
        return ResponseEntity.ok(productService.adjustStock(id, request));
    }

    @GetMapping("/products/{id}/movements")
    public ResponseEntity<List<MovementResponseDTO>> getMovements(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductMovements(id));
    }

    @GetMapping("/personnel/search")
    public ResponseEntity<List<PersonDTO>> searchPersonnel(@RequestParam String query) {
        return ResponseEntity.ok(productService.searchPersonnel(query));
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<ProductResponseDTO>> getAlerts() {
        return ResponseEntity.ok(productService.getLowStockAlerts());
    }

    @GetMapping("/reports/excel-data")
    public ResponseEntity<List<ProductExcelDTO>> getExcelData() {
        return ResponseEntity.ok(productService.getExcelReportData());
    }

    @GetMapping("/reports/movements-data")
    public ResponseEntity<List<MovementExcelDTO>> getMovementsExcelData() {
        return ResponseEntity.ok(productService.getMovementReportData());
    }
}
