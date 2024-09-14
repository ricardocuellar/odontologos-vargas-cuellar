package dh.backend.clinica.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> recursoNoEncontrado(ResourceNotFoundException e, HttpServletRequest request){
        ApiError apiError = new ApiError(
            request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error del servidor por GlobalExeptionHandler");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> manejoGeneral(ResourceNotFoundException e, HttpServletRequest request){
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                "Error del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }


    //BadRequestException
    //Cuando paciente u odontologo no existan en la BD
    //Usar @ExceptionHandler para procesar la excepción desde el controller turnos y retornar un ResponseEntity con el código status400BAD_REQUEST
}
