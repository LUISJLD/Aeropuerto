package edu.unimagdelan.aeropuerto.Entiti;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pasaporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero; // Número del pasaporte

    @OneToOne(mappedBy = "pasaporte")
    private Pasajero pasajero;
}