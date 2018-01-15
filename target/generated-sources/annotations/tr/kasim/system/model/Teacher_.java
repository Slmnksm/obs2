package tr.kasim.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tr.kasim.system.model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-11T16:54:24")
@StaticMetamodel(Teacher.class)
public class Teacher_ { 

    public static volatile SingularAttribute<Teacher, String> surname;
    public static volatile SingularAttribute<Teacher, Integer> name;
    public static volatile SingularAttribute<Teacher, Integer> id;
    public static volatile SingularAttribute<Teacher, User> user;

}