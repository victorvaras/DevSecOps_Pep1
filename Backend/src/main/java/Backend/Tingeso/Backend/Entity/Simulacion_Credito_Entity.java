package Backend.Tingeso.Backend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Simulacion_Credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simulacion_Credito_Entity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id_simulacion_credito;

    private int monto_deseado;
    private int plazo_deseado;

    //Para saber que tipo prestamo se va solicitar
    private int id_Tipo_Prestamo;

    //Valor cuota
    private int valor_cuota;
}
