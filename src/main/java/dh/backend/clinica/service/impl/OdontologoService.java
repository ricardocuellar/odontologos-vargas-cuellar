package dh.backend.clinica.service.impl;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.IOdontologoRepository;
import dh.backend.clinica.service.IOdontologoService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    public static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("odontólogo guardado: " + odontologo);
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        logger.info("odontólogo modificado: " + odontologo);
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        Optional<Odontologo> odontologoEncontrado = buscarPorId(id);
        if(odontologoEncontrado.isPresent()){
            logger.info("id del odontólogo eliminado: " + id);
            odontologoRepository.deleteById(id);
        }else{
            logger.error("Odontologo " + id + " no encontrado");
            throw new ResourceNotFoundException("Odontólogo no encontrado");
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        logger.info("lista odontólogos: "+ listaOdontologos);
        return listaOdontologos;
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        logger.info("odontólogo encontrado: " + id);
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarPorUnaParteApellido(String parte){
        List<Odontologo> apellidoOdontologo = odontologoRepository.buscarPorParteApellido(parte);
        logger.info("búsqueda por apellido odontologos: "+ apellidoOdontologo);
        return apellidoOdontologo;
    }

    @Override
    public List<Odontologo> buscarPorUnaParteMatricula(String matricula) {
        List<Odontologo> matriculaOdontologo = odontologoRepository.buscarPorParteMatricula(matricula);
        logger.info("búsqueda por matricula odontologos: "+ matriculaOdontologo);
        return matriculaOdontologo;
    }
}
