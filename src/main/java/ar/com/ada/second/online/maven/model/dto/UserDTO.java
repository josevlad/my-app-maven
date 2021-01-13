package ar.com.ada.second.online.maven.model.dto;

// DTO: DATA TRANSFER OBJECT

import lombok.*;

// todas estas anotacion, aplican  codigo en tiempo de compilacion.
@NoArgsConstructor // genera el constructor sin arg
@AllArgsConstructor // genera el constructor con todos los arg
@Getter // genera los getter's de los attr
@Setter // genera los setter's de los attr
@ToString // genera el metodo toString
public class UserDTO {

    private Integer id;
    private String nickname;
    private String email;

    public UserDTO(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}
