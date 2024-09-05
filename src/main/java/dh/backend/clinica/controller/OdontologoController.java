package dh.backend.clinica.controller;

import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/odontologos")
    public String buscarOdontologo(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("matricula", odontologo.getNumeroMatricula() );
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "odontologos";
    }

    //@GetMapping("/odontologos")
    @GetMapping("/listaTodos")
    public List<Odontologo> listaTodos(){
        return odontologoService.listaTodos();
    }



}
