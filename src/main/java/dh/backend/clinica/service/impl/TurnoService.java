package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.TurnoModifyDto;
import dh.backend.clinica.dto.request.TurnoRequestDto;
import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.entity.Turno;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.ITurnoRepository;
import dh.backend.clinica.service.IOdontologoService;
import dh.backend.clinica.service.IPacienteService;
import dh.backend.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    public static final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private ITurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;
    @Autowired
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turno = new Turno();
        Turno turnoDesdeBD = null;
        TurnoResponseDto turnoResponseDto = null;
        if(paciente.isPresent() && odontologo.isPresent()){
            //Build turno from turno request DTO
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            //Get persist turno
            turnoDesdeBD = turnoRepository.save(turno);
            logger.info("turno guardado: "+ turnoDesdeBD.getId());


            //Build of turno response dto from turno got of DB
            //Armado a mano
            //turnoResponseDto = obtenerTurnoResponse(turnoDesdeBD);
            //Armado con Model mapper
            turnoResponseDto = convertirTUrnoEnResponse(turnoDesdeBD);
        }else{
            logger.error("No se pudo guardar el turno");
        }

        return turnoResponseDto;
    }

    @Override
    public Optional<Turno> buscarPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        logger.info("turno encontrado: "+ id);
        return turno;
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnoDesdeBD = turnoRepository.findAll();
        List<TurnoResponseDto> turnoRespuesta = new ArrayList<>();
        for(Turno t: turnoDesdeBD){
            //Manual
            //turnoRespuesta.add(obtenerTurnoResponse(t));
            //Con model mapper
            turnoRespuesta.add(convertirTUrnoEnResponse(t));
        }
        logger.info("Turnos encontrados: "+ turnoRespuesta);
        return turnoRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModifyDto turnoModifyDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoModifyDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoModifyDto.getOdontologo_id());

        if(paciente.isPresent() && odontologo.isPresent()){
            Turno turno = new Turno(
                    turnoModifyDto.getId(),
                    paciente.get(),
                    odontologo.get(),
                    LocalDate.parse(turnoModifyDto.getFecha())
                    );
            logger.info("Turno modificado: "+ turno.getId());
            turnoRepository.save(turno);
        }else{
            logger.error("El turno no se pudo modificar");

        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        Optional<Turno> turnoEncontrado = turnoRepository.findById(id);
        if (turnoEncontrado.isPresent()){
            logger.info("Turno eliminado: "+ id);
            turnoRepository.deleteById(id);
        }else{
            logger.error("Turno no encontrado");
            throw new ResourceNotFoundException("Turno no encontrado");
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public Optional<Turno> buscarPorApellidoPaciente(String pacienteApellido) {
        Optional<Turno> apellidoPaciente = turnoRepository.buscarPorApellidoPaciente(pacienteApellido);
        logger.info("Paciente encontrado por apellido: "+ apellidoPaciente);
        return apellidoPaciente;
    }

    private TurnoResponseDto obtenerTurnoResponse(Turno turnoDesdeBD){
        OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto(
                turnoDesdeBD.getOdontologo().getId(), turnoDesdeBD.getOdontologo().getNumeroMatricula(), turnoDesdeBD.getOdontologo().getApellido(), turnoDesdeBD.getOdontologo().getNombre()
        );

        PacienteResponseDto pacienteResponseDto = new PacienteResponseDto(
                turnoDesdeBD.getPaciente().getId(), turnoDesdeBD.getPaciente().getApellido(),
                turnoDesdeBD.getPaciente().getNombre(), turnoDesdeBD.getPaciente().getDni()
        );

        TurnoResponseDto turnoResponseDto = new TurnoResponseDto(
                turnoDesdeBD.getId(),
                pacienteResponseDto, odontologoResponseDto,
                turnoDesdeBD.getFecha().toString()
        );

        return turnoResponseDto;
    }

    private TurnoResponseDto convertirTUrnoEnResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        return turnoResponseDto;
    }
}
