package studios.tkoh.producto_manager.dto;


/**
 *
 * @author Studios TKOH!
 */
public record ProductRequest(
        String code,
        String name,
        String unit,
        String category,
        Integer stock,
        Integer minStock) {

}
