package studios.tkoh.producto_manager.dto;

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
public class MovementRequest {

    private String type;
    private Integer quantity;
    private String reason;

    private String receiverName;
    private String receiverRole;
}
