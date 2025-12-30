package studios.tkoh.producto_manager.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Studios TKOH!
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String code;
    private String name;
    private String unit;
    private String category;
    private Integer stock;
    private Integer minStock;
    private String status;
    private LocalDateTime lastUpdated;

}
