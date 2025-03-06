package edu.unimagdelan.aeropuerto.Repository;

import edu.unimagdelan.aeropuerto.Entiti.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    Pasajero findByNID(String NID);
    List<Pasajero> findByNombreContainingIgnoreCase(String nombre);
    long countByPasaporteIsNotNull();
    List<Pasajero> findAllByOrderByNombreAsc();
    boolean existsByNID(String NID);


    @Query("SELECT p FROM Pasajero p WHERE p.NID = :NID")
    Pasajero buscarPorNID(@Param("NID") String NID);


    @Query("SELECT p FROM Pasajero p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Pasajero> buscarPorNombreConteniendo(@Param("nombre") String nombre);


    @Query("SELECT COUNT(p) FROM Pasajero p WHERE p.pasaporte IS NOT NULL")
    long contarPasajerosConPasaporte();


    @Query("SELECT p FROM Pasajero p ORDER BY p.nombre ASC")
    List<Pasajero> obtenerTodosOrdenadosPorNombre();


    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Pasajero p WHERE p.NID = :NID")
    boolean existePorNID(@Param("NID") String NID);
}