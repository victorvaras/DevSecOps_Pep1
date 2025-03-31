package Backend.Tingeso.Backend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Evaluacion_Credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion_Credito_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_evaluacion_credito;

    private boolean credito_aceptado;

    //Relacion cuota ingreso
    private boolean R1;
    //Historial crediticio del cliente
    private boolean R2;
    //Antiguedad laboral
    private boolean R3;
    //Relacion deuda/ingreso
    private boolean R4;
    //Monto maximo financiamiento
    private boolean R5;
    //Edad del solicitante
    private boolean R6;
    //Capacidad de ahorro
    private String R7;

    //Saldo minimo requerido
    private boolean R71;
    //Historial de ahorro consistente
    private boolean R72;
    //Deposito periodico
    private boolean R73;
    //Relacion saldo/años antiguedad
    private boolean R74;
    //Retiron recientes
    private boolean R75;

}
