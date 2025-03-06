package edu.unimagdelan.aeropuerto.Entiti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {


    Pasaporte findByNumero(String numero);


    boolean existsByNumero(String numero);


    long countByPasajeroIsNotNull();


    List<Pasaporte> findAllByOrderByNumeroAsc();


    @Query("SELECT p FROM Pasaporte p JOIN FETCH p.pasajero WHERE p.numero = :numero")
    Pasaporte findByNumeroWithPasajero(@Param("numero") String numero);

    @Query("SELECT p FROM Pasaporte p WHERE p.numero = :numero")
    Pasaporte buscarPorNumero(@Param("numero") String numero);


    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Pasaporte p WHERE p.numero = :numero")
    boolean existePorNumero(@Param("numero") String numero);


    @Query("SELECT COUNT(p) FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    long contarPasaportesAsignados();


    @Query("SELECT p FROM Pasaporte p ORDER BY p.numero ASC")
    List<Pasaporte> obtenerTodosOrdenadosPorNumero();


    @Query("SELECT p FROM Pasaporte p JOIN FETCH p.pasajero WHERE p.numero = :numero")
    Pasaporte buscarPorNumeroConPasajero(@Param("numero") String numero);
}
