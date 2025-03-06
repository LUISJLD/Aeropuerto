package edu.unimagdelan.aeropuerto.Repository;

import edu.unimagdelan.aeropuerto.Entiti.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {

    // 1. Buscar pasajero por número de identificación (NID)
    Pasajero findByNID(String NID);

    // 2. Buscar pasajeros cuyo nombre contenga una cadena específica (ignorando mayúsculas/minúsculas)
    List<Pasajero> findByNombreContainingIgnoreCase(String nombre);

    // 3. Contar cuántos pasajeros tienen un pasaporte registrado
    long countByPasaporteIsNotNull();

    // 4. Obtener todos los pasajeros ordenados por nombre ascendente
    List<Pasajero> findAllByOrderByNombreAsc();

    // 5. Verificar si existe un pasajero con un NID específico
    boolean existsByNID(String NID);

    // 1. Buscar pasajero por NID
    @Query("SELECT p FROM Pasajero p WHERE p.NID = :NID")
    Pasajero buscarPorNID(@Param("NID") String NID);

    // 2. Buscar pasajeros cuyo nombre contenga una cadena específica
    @Query("SELECT p FROM Pasajero p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Pasajero> buscarPorNombreConteniendo(@Param("nombre") String nombre);

    // 3. Contar cuántos pasajeros tienen un pasaporte registrado
    @Query("SELECT COUNT(p) FROM Pasajero p WHERE p.pasaporte IS NOT NULL")
    long contarPasajerosConPasaporte();

    // 4. Obtener todos los pasajeros ordenados por nombre ascendente
    @Query("SELECT p FROM Pasajero p ORDER BY p.nombre ASC")
    List<Pasajero> obtenerTodosOrdenadosPorNombre();

    // 5. Verificar si existe un pasajero con un NID específico
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Pasajero p WHERE p.NID = :NID")
    boolean existePorNID(@Param("NID") String NID);
}