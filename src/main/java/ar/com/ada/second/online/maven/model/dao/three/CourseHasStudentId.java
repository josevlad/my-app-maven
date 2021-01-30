package ar.com.ada.second.online.maven.model.dao.three;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CourseHasStudentId implements Serializable {

    @Column(name = "Course_id")
    private Long courseId;

    @Column(name = "Student_id")
    private Long studentId;

}
