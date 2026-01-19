package studios.tkoh.producto_manager.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Studios TKOH!
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getApiStatus() {
        Map<String, Object> status = new LinkedHashMap<>();
        status.put("app", "Producto Manager API");
        status.put("version", "0.0.1-SNAPSHOT");
        status.put("status", "ONLINE");
        status.put("serverTime", LocalDateTime.now());
        status.put("description", "Small API for managing products from ONPE");
        status.put("author", "Studios TKOH!");

        return ResponseEntity.ok(status);
    }
}
