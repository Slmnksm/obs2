package tr.kasim.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tr.kasim.system.model.Note;
import tr.kasim.system.model.Student;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-11T16:54:24")
@StaticMetamodel(Lecture.class)
public class Lecture_ { 

    public static volatile SingularAttribute<Lecture, String> code;
    public static volatile ListAttribute<Lecture, Note> notes;
    public static volatile SingularAttribute<Lecture, String> name;
    public static volatile ListAttribute<Lecture, Student> students;
    public static volatile SingularAttribute<Lecture, Integer> id;
    public static volatile SingularAttribute<Lecture, String> capacity;

}