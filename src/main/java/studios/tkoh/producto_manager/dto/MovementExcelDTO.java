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
public class MovementExcelDTO {

    private String fechaHora;
    private String tipoMovimiento; // "ENTRADA" o "SALIDA"
    private String codigoProducto;
    private String nombreProducto;
    private Integer cantidad;
    private String motivo;
    private String responsableNombre;
    private String responsableCargo;
}
