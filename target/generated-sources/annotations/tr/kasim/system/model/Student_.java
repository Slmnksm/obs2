package tr.kasim.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tr.kasim.system.model.Lecture;
import tr.kasim.system.model.Note;
import tr.kasim.system.model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-11T16:54:24")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, String> address;
    public static volatile ListAttribute<Student, Note> notes;
    public static volatile SingularAttribute<Student, String> surname;
    public static volatile SingularAttribute<Student, String> studentNumber;
    public static volatile SingularAttribute<Student, String> name;
    public static volatile ListAttribute<Student, Lecture> lectures;
    public static volatile SingularAttribute<Student, String> tel;
    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, User> user;

}