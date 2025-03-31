package Backend.Tingeso.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Solicitud_Credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud_Credito_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_solicitud_credito;

    private int id_cliente;
    private int id_ejecutivo;

    //Para saber que tipo prestamo se va solicitar
    private int id_Tipo_Prestamo;
    private int monto_deseado;
    private int plazo_deseado;

    @Lob
    private byte[] comprobante_ingresos;
    @Lob
    private byte[] certificado_avaluo;
    @Lob
    private byte[] historial_crediticio;
    @Lob
    private byte[] escritura_primera_vivienda;
    @Lob
    private byte[] estado_financiero_negocio;
    @Lob
    private byte[] plan_negocios;
    @Lob
    private byte[] presupuesto_remodelacion;
    @Lob
    private byte[] certificado_avaluo_actualizado;

    private int id_evaluacion_credito;
    private int id_seguimiento_solicitud;

    private Integer cuota_mensual;


}
