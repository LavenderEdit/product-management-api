package studios.tkoh.producto_manager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@CrossOrigin(origins = "*")
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

    @PutMapping("/products/{id}/movement")
    public ResponseEntity<ProductResponseDTO> moveStock(
            @PathVariable Long id,
            @RequestParam String type,
            @RequestParam Integer qty,
            @RequestParam String reason
    ) {
        return ResponseEntity.ok(productService.adjustStock(id, type, qty, reason));
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<ProductResponseDTO>> getAlerts() {
        return ResponseEntity.ok(productService.getLowStockAlerts());
    }

    @GetMapping("/reports/excel-data")
    public ResponseEntity<List<ProductExcelDTO>> getExcelData() {
        return ResponseEntity.ok(productService.getExcelReportData());
    }
}
