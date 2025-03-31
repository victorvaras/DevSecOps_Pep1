package Backend.Tingeso.Backend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;
    private int rut;
    private String nombre;
    private String apellido;
    private String contrasenia;

    //Documentacion
    @Lob
    private byte[] identificacion;
    @Lob
    private byte[] comprobante_ingresos;


}
