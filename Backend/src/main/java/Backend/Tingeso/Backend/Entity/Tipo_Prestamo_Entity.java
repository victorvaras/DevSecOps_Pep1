package Backend.Tingeso.Backend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tipo_Prestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tipo_Prestamo_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Tipo_Prestamo;
    private String nombreTipo_Prestamo;
    private int plazo_maximo;
    private int tasa_anual;
    private int porcentaje_maximo_financiamiento;



}

/*
INSERT INTO public.tipo_prestamo(
    id_tipo_prestamo, plazo_maximo, porcentaje_maximo_financiamiento, tasa_anual, nombre_tipo_prestamo)
VALUES
    (1, 30, 80, 5, 'Primera Vivienda'),
    (2, 20, 70, 6, 'Segunda Vivienda'),
    (3, 25, 60, 7, 'Propiedades Comerciales'),
    (4, 15, 50, 6, 'Remodelación');

 */
