package ar.com.ada.second.online.maven.model.dao.three;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Course_has_Student")
public class CourseHasStudent implements Serializable {

    @EmbeddedId
    private CourseHasStudentId id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "Course_id", foreignKey = @ForeignKey(name = "fk_Course_has_Student_Course"))
    private CourseDAO course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "Student_id", foreignKey = @ForeignKey(name = "fk_Course_has_Student_Student"))
    private StudentDAO student;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;
}
