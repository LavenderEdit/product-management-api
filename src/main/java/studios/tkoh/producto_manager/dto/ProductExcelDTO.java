package studios.tkoh.producto_manager.dto;

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
public class ProductExcelDTO {

    private String codigoBarras;
    private String producto;
    private String categoria;
    private String unidad;
    private Integer cantidadActual;
    private String estadoInventario;
    private String fechaUltimoMovimiento;
}
