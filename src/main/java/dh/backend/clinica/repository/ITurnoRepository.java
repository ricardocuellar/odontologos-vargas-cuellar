package dh.backend.clinica.repository;

import dh.backend.clinica.entity.Turno;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {

    @Query("Select t from Turno t join t.paciente p with p.apellido = :pacienteApellido")
    Optional<Turno> buscarPorApellidoPaciente(String pacienteApellido);
}
