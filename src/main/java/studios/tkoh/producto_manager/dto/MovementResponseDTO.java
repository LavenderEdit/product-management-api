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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementResponseDTO {

    private Long id;
    private String type;
    private Integer quantity;
    private String reason;
    private LocalDateTime date;
    private String receiverName;
    private String receiverRole;
}
