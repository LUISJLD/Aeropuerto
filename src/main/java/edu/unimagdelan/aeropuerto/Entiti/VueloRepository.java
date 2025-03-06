package edu.unimagdelan.aeropuerto.Entiti;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

        List<Vuelo> findByDestino(String destino);
        List<Vuelo> findByOrigen(String origen);
        List<Vuelo> findByAerolineasNombre(String nombreAerolinea);
        List<Vuelo> findByNumeroVuelo(UUID numeroVuelo);
        List<Vuelo> findByDestinoAndOrigen(String destino, String origen);

        @Query("SELECT v FROM Vuelo v WHERE v.destino = :destino")
        List<Vuelo> obtenerVuelosPorDestino(@Param("destino") String destino);

        @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen")
        List<Vuelo> obtenerVuelosPorOrigen(@Param("origen") String origen);

        @Query("SELECT v FROM Vuelo v JOIN v.aerolineas a WHERE a.nombre = :nombreAerolinea")
        List<Vuelo> obtenerVuelosPorAerolinea(@Param("nombreAerolinea") String nombreAerolinea);

        @Query("SELECT v FROM Vuelo v WHERE v.numeroVuelo = :numeroVuelo")
        Vuelo obtenerVueloPorNumero(@Param("numeroVuelo") UUID numeroVuelo);

        @Query("SELECT v FROM Vuelo v WHERE v.destino = :destino AND v.origen = :origen")
        List<Vuelo> obtenerVuelosPorDestinoYOrigen(@Param("destino") String destino, @Param("origen") String origen);


}
