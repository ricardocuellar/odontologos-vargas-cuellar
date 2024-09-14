package dh.backend.clinica.controller;

import dh.backend.clinica.dto.request.TurnoModifyDto;
import dh.backend.clinica.dto.request.TurnoRequestDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.entity.Turno;
import dh.backend.clinica.service.ITurnoService;
import dh.backend.clinica.service.impl.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TurnoResponseDto> guardarTurno(@RequestBody TurnoRequestDto turnoRequestDto){
        return ResponseEntity.ok(turnoService.guardarTurno(turnoRequestDto));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(id);
        if (turnoEncontrado.isPresent()){
            return ResponseEntity.ok(turnoEncontrado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<TurnoResponseDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@RequestBody TurnoModifyDto turnoModifyDto){
        turnoService.modificarTurno(turnoModifyDto);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue modificado\"");
    }

    @GetMapping("/buscarTurnosApellido/{apellido}")
    public ResponseEntity<Turno> buscarTurnoPorApellido(@PathVariable String apellido){
        Optional<Turno> turno = turnoService.buscarPorApellidoPaciente(apellido);
        return ResponseEntity.ok(turno.get());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue eliminado\"");
    }
}
