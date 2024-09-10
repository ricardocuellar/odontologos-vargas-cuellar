package dh.backend.clinica.dao;

import java.util.List;

public interface IDao <T>{
    T guardar(T t);
    T buscarPorId(Integer id);
    List<T> listaTodos();
    void modifitar(T t);
    void eliminar(Integer id);

}
