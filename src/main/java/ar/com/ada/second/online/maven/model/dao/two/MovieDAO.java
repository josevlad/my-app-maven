package ar.com.ada.second.online.maven.model.dao.two;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Movie")
public class MovieDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "Actor_has_Movie",
            joinColumns = { @JoinColumn(
                    name = "Movie_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_Actor_has_Movie_Movie"))
            },
            inverseJoinColumns = { @JoinColumn(
                    name = "Actor_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_Actor_has_Movie_Actor"))
            }
    )
    private Set<ActorDAO> actors;
}
