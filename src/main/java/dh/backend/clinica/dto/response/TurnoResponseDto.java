package dh.backend.clinica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDto {
    private Integer id;
    //Datos del paciente
    private PacienteResponseDto pacienteResponseDto;

    //Datos odontologo
    private OdontologoResponseDto odontologoResponseDto;
    private String fecha;
}
