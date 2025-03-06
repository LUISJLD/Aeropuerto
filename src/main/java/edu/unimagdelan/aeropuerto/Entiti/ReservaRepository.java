package edu.unimagdelan.aeropuerto.Entiti;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservaRepository {

    Reserva findByCodigoReserva(UUID codigoReserva);


    List<Reserva> findByPasajeroId(Long pasajeroId);


    List<Reserva> findByVueloId(Long vueloId);


    long countByPasajeroId(Long pasajeroId);


    boolean existsByPasajeroIdAndVueloId(Long pasajeroId, Long vueloId);

    @Query("SELECT r FROM Reserva r WHERE r.codigoReserva = :codigoReserva")
    Reserva buscarPorCodigoReserva(@Param("codigoReserva") UUID codigoReserva);


    @Query("SELECT r FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    List<Reserva> buscarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId);


    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = :vueloId")
    List<Reserva> buscarReservasPorVuelo(@Param("vueloId") Long vueloId);


    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    long contarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId);


    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Reserva r WHERE r.pasajero.id = :pasajeroId AND r.vuelo.id = :vueloId")
    boolean existeReservaPorPasajeroYVuelo(@Param("pasajeroId") Long pasajeroId, @Param("vueloId") Long vueloId);


}