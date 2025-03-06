package edu.unimagdelan.aeropuerto.Repository;

import edu.unimagdelan.aeropuerto.Entiti.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {

    // 1. Buscar un pasaporte por su número
    Pasaporte findByNumero(String numero);

    // 2. Verificar si existe un pasaporte con un número específico
    boolean existsByNumero(String numero);

    // 3. Contar cuántos pasaportes están asignados a un pasajero
    long countByPasajeroIsNotNull();

    // 4. Obtener todos los pasaportes ordenados por número ascendente
    List<Pasaporte> findAllByOrderByNumeroAsc();

    // 5. Buscar un pasaporte junto con su pasajero
    @Query("SELECT p FROM Pasaporte p JOIN FETCH p.pasajero WHERE p.numero = :numero")
    Pasaporte findByNumeroWithPasajero(@Param("numero") String numero);
    // 1. Buscar un pasaporte por su número
    @Query("SELECT p FROM Pasaporte p WHERE p.numero = :numero")
    Pasaporte buscarPorNumero(@Param("numero") String numero);

    // 2. Verificar si existe un pasaporte con un número específico
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Pasaporte p WHERE p.numero = :numero")
    boolean existePorNumero(@Param("numero") String numero);

    // 3. Contar cuántos pasaportes están asignados a un pasajero
    @Query("SELECT COUNT(p) FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    long contarPasaportesAsignados();

    // 4. Obtener todos los pasaportes ordenados por número ascendente
    @Query("SELECT p FROM Pasaporte p ORDER BY p.numero ASC")
    List<Pasaporte> obtenerTodosOrdenadosPorNumero();

    // 5. Buscar un pasaporte junto con su pasajero
    @Query("SELECT p FROM Pasaporte p JOIN FETCH p.pasajero WHERE p.numero = :numero")
    Pasaporte buscarPorNumeroConPasajero(@Param("numero") String numero);
}
