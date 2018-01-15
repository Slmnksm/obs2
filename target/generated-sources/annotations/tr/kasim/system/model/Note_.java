package tr.kasim.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tr.kasim.system.model.Lecture;
import tr.kasim.system.model.Student;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-11T16:54:24")
@StaticMetamodel(Note.class)
public class Note_ { 

    public static volatile SingularAttribute<Note, Student> student;
    public static volatile SingularAttribute<Note, Lecture> lecture;
    public static volatile SingularAttribute<Note, Integer> id;
    public static volatile SingularAttribute<Note, String> noteNumber;

}