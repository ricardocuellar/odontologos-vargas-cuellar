package dh.backend.clinica.service.impl;


import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.IPacienteRepository;
import dh.backend.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    public static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("paciente guardado: " + paciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        logger.info("paciente encontrado: " + id);
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        logger.info("lista pacientes: "+ listaPacientes);
        return listaPacientes;
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        logger.info("Paciente modificado: "+ paciente);
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = buscarPorId(id);
        if(pacienteEncontrado.isPresent()){
            logger.info("Paciente eliminado: "+ id);
            pacienteRepository.deleteById(id);
        }else{
            logger.error("Paciente "+ id + "no ha sido eliminado");
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<Paciente> buscarPorUnaParteApellido(String parte){
        List<Paciente> apellidoPaciente = pacienteRepository.buscarPorParteApellido(parte);
        logger.info("búsqueda por apellido pacientes: "+ apellidoPaciente);
        return apellidoPaciente;
    }

}
