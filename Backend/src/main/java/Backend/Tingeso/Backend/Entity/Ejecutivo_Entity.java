package Backend.Tingeso.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ejecutivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejecutivo_Entity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id_ejecutivo;
    private int rut;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasenia;
}
