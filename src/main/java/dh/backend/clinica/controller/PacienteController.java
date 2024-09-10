package dh.backend.clinica.controller;

import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    //POST
    @PostMapping("/guardar")
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    //PUT
    @PutMapping("/modificar")
    public String modificarPaciente(@RequestBody Paciente paciente){
        pacienteService.modificarPaciente(paciente);
        return "El paciente " + paciente.getId() + " fue modificado";
    }

    //Delete
    @DeleteMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return "El paciente "+ id + " fue eliminado";
    }


    //Get ID
    @GetMapping("/buscar/{id}")
    public Paciente buscarPorId(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }

    //Get all
    @GetMapping("/buscarTodos")
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }
}
