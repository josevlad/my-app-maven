package ar.com.ada.second.online.maven.model.dto;

// DTO: DATA TRANSFER OBJECT

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// todas estas anotacion, aplican  codigo en tiempo de compilacion.
@NoArgsConstructor // genera el constructor sin arg
@AllArgsConstructor // genera el constructor con todos los arg
@Getter // genera los getter's de los attr
@Setter // genera los setter's de los attr
public class UserDTO {

    private Integer id;
    private String nickname;
    private String email;

}
