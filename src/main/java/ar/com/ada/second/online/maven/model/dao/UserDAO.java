package ar.com.ada.second.online.maven.model.dao;

// DAO: DATA ACCESS OBJECT

import ar.com.ada.second.online.maven.model.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "User")
public class UserDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nickname", length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<PostDAO> posts;

    @OneToMany(mappedBy = "user")
    private List<CommentDAO> comments;

    public UserDAO(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public static UserDAO toDAO(UserDTO dto) {
        UserDAO userDAO = new UserDAO(dto.getNickname(), dto.getEmail());
        if (dto.getId() != null)
            userDAO.setId(dto.getId());
        return userDAO;
    }

    public static UserDTO toDTO(UserDAO dao) {
        UserDTO dto = new UserDTO(dao.getNickname(), dao.getEmail());
        if (dao.getId() != null)
            dto.setId(dao.getId());
        return dto;
    }
}


/**
 * List<RecordSet> list = rs.query("SELECT * FROM User");
 * for (list) {
 * <p>
 * int id = list.get(i).id();
 * String nickname = list.get(i).nickname();
 * String email = list.get(i).email();
 * <p>
 * UserDAO userDAO = new UserDAO(id, nickname, email);
 * }
 */
