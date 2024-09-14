package dh.backend.clinica.controller;

import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return  ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteEncontrado.isPresent()){
            pacienteService.modificarPaciente(paciente);
            String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"";
            return  ResponseEntity.ok(jsonResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Delete
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){
         pacienteService.eliminarPaciente(id);
         return  ResponseEntity.ok("{\"mensaje\": \"El paciente fue eliminado\"");

    }


    //Get ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id){
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(id);
        if (pacienteEncontrado.isPresent()){
            return ResponseEntity.ok(pacienteEncontrado.get());
        }
        return ResponseEntity.notFound().build();
    }

    //Get all
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/buscarApellido/{apellido}")
    public ResponseEntity<List<Paciente>> buscarParteApellido(@PathVariable String apellido){
        return ResponseEntity.ok(pacienteService.buscarPorUnaParteApellido(apellido));
    }
}
