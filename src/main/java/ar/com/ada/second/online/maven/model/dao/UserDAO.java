package ar.com.ada.second.online.maven.model.dao;

// DAO: DATA ACCESS OBJECT

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {

    private Integer id;
    private String nickname;
    private String email;

}
