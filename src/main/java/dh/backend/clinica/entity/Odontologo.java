package dh.backend.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "odontologo-turnos")
    //@JsonIgnore
    private Set<Turno> turnoSet;

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}