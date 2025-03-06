package edu.unimagdelan.aeropuerto.Repository;


import edu.unimagdelan.aeropuerto.Entiti.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {


    List<Aerolinea> findByNombreContaining(String nombre);
    List<Aerolinea> findByVuelosDestino(String destino);
    List<Aerolinea> findByVuelosOrigen(String origen);
    List<Aerolinea> findByVuelosNumeroVuelo(String numeroVuelo);
    boolean existsByNombre(String nombre);


    @Query("SELECT a FROM Aerolinea a WHERE a.nombre LIKE %:nombre%")
    List<Aerolinea> obtenerAerolineasPorNombre(@Param("nombre") String nombre);

    @Query("SELECT DISTINCT a FROM Aerolinea a JOIN a.vuelos v WHERE v.destino = :destino")
    List<Aerolinea> obtenerAerolineasConVuelosADestino(@Param("destino") String destino);

    @Query("SELECT DISTINCT a FROM Aerolinea a JOIN a.vuelos v WHERE v.origen = :origen")
    List<Aerolinea> obtenerAerolineasConVuelosDesdeOrigen(@Param("origen") String origen);

    @Query("SELECT DISTINCT a FROM Aerolinea a JOIN a.vuelos v WHERE v.numeroVuelo = :numeroVuelo")
    List<Aerolinea> obtenerAerolineasPorNumeroVuelo(@Param("numeroVuelo") String numeroVuelo);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Aerolinea a WHERE a.nombre = :nombre")
    boolean existeAerolineaPorNombre(@Param("nombre") String nombre);


}