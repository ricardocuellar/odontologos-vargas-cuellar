package dh.backend.clinica.repository;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
    @Query("Select o from Odontologo o where LOWER(o.apellido) LIKE LOWER(CONCAT('%',:parteApellido,'%' ))")
    List<Odontologo> buscarPorParteApellido(String parteApellido);

    @Query("Select m from Odontologo m where LOWER(m.numeroMatricula) LIKE LOWER(CONCAT('%',:parteMatricula, '%' ))")
    List<Odontologo> buscarPorParteMatricula(String parteMatricula);
}
