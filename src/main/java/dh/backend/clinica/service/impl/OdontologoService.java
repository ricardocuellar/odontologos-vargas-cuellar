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
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        logger.info("id del odontólogo encontrado: " + id);
        return odontologoRepository.findById(id);
    }
}
