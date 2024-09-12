package dh.backend.clinica.dao.impl;
import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoEnMemoria implements IDao<Turno>{
    private static Logger logger = LoggerFactory.getLogger(TurnoEnMemoria.class);
    private List<Turno> turnos = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turno.setId(turnos.size()+1);
        turnos.add(turno);
        logger.info("El turno fue agregado : "+ turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoEncontrado = null;
        for (Turno t: turnos){
            if(t.getId().equals(id)){
                turnoEncontrado = t;
                logger.info("Turno encontrado: "+turnoEncontrado);
            }
        }
        return turnoEncontrado;
    }

    @Override
    public List<Turno> listaTodos() {
        return turnos;
    }

    @Override
    public void modifitar(Turno turno) {
        for (Turno t: turnos){
            if(t.getId().equals(turno.getId())){
                t.setOdontologo(turno.getOdontologo());
                t.setPaciente(turno.getPaciente());
                t.setFecha(turno.getFecha());
                logger.info("Turno encontrado: "+t);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoAEliminar = null;
        for (Turno t: turnos){
            if(t.getId().equals(id)){
                //turnoAEliminar = t;
                turnos.remove(t);
                break;
            }
        }
        logger.info("Turno eliminado");
    }
}
