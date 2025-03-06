package edu.unimagdelan.aeropuerto.Repository;

import edu.unimagdelan.aeropuerto.Entiti.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservaRepository {
    // 1. Buscar una reserva por su código único
    Reserva findByCodigoReserva(UUID codigoReserva);

    // 2. Buscar todas las reservas de un pasajero específico
    List<Reserva> findByPasajeroId(Long pasajeroId);

    // 3. Buscar todas las reservas asociadas a un vuelo específico
    List<Reserva> findByVueloId(Long vueloId);

    // 4. Contar cuántas reservas tiene un pasajero específico
    long countByPasajeroId(Long pasajeroId);

    // 5. Verificar si un pasajero tiene una reserva para un vuelo específico
    boolean existsByPasajeroIdAndVueloId(Long pasajeroId, Long vueloId);
    // 1. Buscar una reserva por su código único
    @Query("SELECT r FROM Reserva r WHERE r.codigoReserva = :codigoReserva")
    Reserva buscarPorCodigoReserva(@Param("codigoReserva") UUID codigoReserva);

    // 2. Buscar todas las reservas de un pasajero específico
    @Query("SELECT r FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    List<Reserva> buscarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId);

    // 3. Buscar todas las reservas asociadas a un vuelo específico
    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = :vueloId")
    List<Reserva> buscarReservasPorVuelo(@Param("vueloId") Long vueloId);

    // 4. Contar cuántas reservas tiene un pasajero específico
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    long contarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId);

    // 5. Verificar si un pasajero tiene una reserva para un vuelo específico
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Reserva r WHERE r.pasajero.id = :pasajeroId AND r.vuelo.id = :vueloId")
    boolean existeReservaPorPasajeroYVuelo(@Param("pasajeroId") Long pasajeroId, @Param("vueloId") Long vueloId);


}
