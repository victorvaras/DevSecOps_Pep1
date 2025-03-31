package Backend.Tingeso.Backend.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seguimiento_Solicitud")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seguimiento_Solicitud_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_seguimiento_solicitud;
    private String nombre_seguimiento_solicitud;
    private String descripcion_seguimiento_solicitud;
}

/*
INSERT INTO seguimiento_solicitud (id_seguimiento_solicitud, nombre_seguimiento_solicitud, descripcion_seguimiento_solicitud) VALUES
(1, 'En Revisión Inicial', 'Solicitud recibida y en proceso de verificación preliminar.'),
(2, 'Pendiente de Documentación', 'Solicitud en espera por falta de documentos o información adicional.'),
(3, 'En Evaluación', 'Solicitud en evaluación por un ejecutivo para revisión detallada.'),
(4, 'Pre-Aprobada', 'Solicitud cumple criterios básicos y ha sido pre-aprobada.'),
(5, 'En Aprobación Final', 'Solicitud en revisión final y preparación de contratos.'),
(6, 'Aprobada', 'Solicitud aprobada y lista para el desembolso.'),
(7, 'Rechazada', 'Solicitud no cumple con los criterios del banco y ha sido rechazada.'),
(8, 'Cancelada por el Cliente', 'Cliente canceló la solicitud en alguna etapa del proceso.'),
(9, 'En Desembolso', 'Solicitud aprobada y se está ejecutando el desembolso.');

 */