package studios.tkoh.producto_manager.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studios.tkoh.producto_manager.dto.MovementRequest;
import studios.tkoh.producto_manager.dto.PersonDTO;
import studios.tkoh.producto_manager.dto.ProductExcelDTO;
import studios.tkoh.producto_manager.dto.ProductRequest;
import studios.tkoh.producto_manager.dto.ProductResponseDTO;
import studios.tkoh.producto_manager.model.Movement;
import studios.tkoh.producto_manager.model.Product;
import studios.tkoh.producto_manager.repository.MovementRepository;
import studios.tkoh.producto_manager.repository.ProductRepository;
import studios.tkoh.producto_manager.service.ProductService;

/**
 *
 * @author Studios TKOH!
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequest request) {
        if (productRepository.existsByCode(request.code())) {
            throw new RuntimeException("El código de producto ya existe: " + request.code());
        }

        Product product = new Product(
                request.code(),
                request.name(),
                request.unit(),
                request.category(),
                request.stock()
        );

        if (request.minStock() != null) {
            product.setMinStock(request.minStock());
        }

        Product saved = productRepository.save(product);
        return mapToResponseDTO(saved);
    }

    @Override
    @Transactional
    public List<ProductResponseDTO> createProductsBatch(List<ProductRequest> requests) {
        List<Product> productsToSave = new ArrayList<>();

        for (ProductRequest req : requests) {
            if (productRepository.existsByCode(req.code())) {
                continue;
            }

            Product p = new Product(
                    req.code(),
                    req.name(),
                    req.unit(),
                    req.category(),
                    req.stock()
            );
            if (req.minStock() != null) {
                p.setMinStock(req.minStock());
            }

            productsToSave.add(p);
        }

        List<Product> savedProducts = productRepository.saveAll(productsToSave);

        return savedProducts.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO adjustStock(Long productId, MovementRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if ("OUT".equalsIgnoreCase(request.getType())) {
            if (request.getReceiverRole() == null || request.getReceiverRole().trim().isEmpty()) {
                throw new IllegalArgumentException("El CARGO es obligatorio para retiros de producto.");
            }

            product.removeStock(request.getQuantity());
        } else if ("IN".equalsIgnoreCase(request.getType())) {
            product.addStock(request.getQuantity());
        } else {
            throw new IllegalArgumentException("Tipo de movimiento inválido. Use 'IN' o 'OUT'");
        }

        Product savedProduct = productRepository.save(product);

        Movement movement = Movement.builder()
                .product(savedProduct)
                .type(request.getType().toUpperCase())
                .quantity(request.getQuantity())
                .reason(request.getReason())
                .date(LocalDateTime.now())
                .receiverName(request.getReceiverName())
                .receiverRole(request.getReceiverRole())
                .build();

        movementRepository.save(movement);

        return mapToResponseDTO(savedProduct);
    }

    @Override
    public List<PersonDTO> searchPersonnel(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return movementRepository.searchPersonnel(query);
    }

    @Override
    public List<ProductResponseDTO> getLowStockAlerts() {
        return productRepository.findProductsWithLowStock().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductExcelDTO> getExcelReportData() {
        return productRepository.findAll().stream()
                .map(product -> {
                    ProductExcelDTO dto = new ProductExcelDTO();
                    dto.setCodigoBarras(product.getCode());
                    dto.setProducto(product.getName());
                    dto.setCategoria(product.getCategory());
                    dto.setUnidad(product.getUnit());
                    dto.setCantidadActual(product.getStock());

                    if (product.getStock() == 0) {
                        dto.setEstadoInventario("AGOTADO");
                    } else if (product.getStock() <= product.getMinStock()) {
                        dto.setEstadoInventario("BAJO STOCK");
                    } else {
                        dto.setEstadoInventario("NORMAL");
                    }

                    dto.setFechaUltimoMovimiento(
                            product.getLastUpdated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // --- MAPPER AUXILIAR ---
    private ProductResponseDTO mapToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setUnit(product.getUnit());
        dto.setCategory(product.getCategory());
        dto.setStock(product.getStock());
        dto.setMinStock(product.getMinStock());
        dto.setLastUpdated(product.getLastUpdated());

        if (product.getStock() <= product.getMinStock()) {
            dto.setStatus("LOW_STOCK");
        } else {
            dto.setStatus("OK");
        }

        return dto;
    }

}
