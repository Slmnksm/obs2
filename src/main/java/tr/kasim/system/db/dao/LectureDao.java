/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.system.db.Session;
import tr.kasim.system.model.Lecture;

/**
 *
 * @author S.KasımYurtaslan
 */
public class LectureDao extends CommonDao<Lecture> {

    public Lecture getByCode(Session session, String lectureCode) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from Lecture a where a.code =:code ");
        query.setParameter("code", lectureCode);
        return (Lecture) getSingleResult(query.getResultList());
    }
    
    

}
