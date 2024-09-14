package dh.backend.clinica.controller;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    /*
    @GetMapping("/odontologos")
    public String buscarOdontologo(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("matricula", odontologo.getNumeroMatricula() );
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "odontologos";
    }*/

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado.isPresent()){
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El Odontologo fue modificado\"}";
            return  ResponseEntity.ok(jsonResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Delete
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return  ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");

    }


    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscarApellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarParteApellido(@PathVariable String apellido){
        return ResponseEntity.ok(odontologoService.buscarPorUnaParteApellido(apellido));
    }

    @GetMapping("/buscarMatricula/{matricula}")
    public ResponseEntity<List<Odontologo>> buscarParteMatricula(@PathVariable String matricula){
        return ResponseEntity.ok(odontologoService.buscarPorUnaParteMatricula(matricula));
    }


}
