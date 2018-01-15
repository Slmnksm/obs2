/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.system.db.Session;
import tr.kasim.system.model.Student;

/**
 *
 * @author S.KasımYurtaslan
 */
public class StudentDao extends CommonDao<Student>{
    
    public Student getByNumber(Session session, String studentNumber) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select b from Student b where b.studentNumber =:studentNumber ");
        query.setParameter("studentNumber", studentNumber);
        return (Student) getSingleResult(query.getResultList());
    }

    
    

}
