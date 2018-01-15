/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.system.db.Session;
import tr.kasim.system.model.Note;

/**
 *
 * @author S.KasımYurtaslan
 */
public class NoteDao extends CommonDao<Note> {

    public Note getNoteByStudentAndLectureId(Session session, String studentNumber, String lectureCode) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from Note a where a.student.studentNumber=:studentId and a.lecture.code=:code");
        query.setParameter("studentId", studentNumber);
        query.setParameter("code", lectureCode);
        return (Note) getSingleResult(query.getResultList());

    }
    public Note getAllNotesByStudentNumber(Session session, String studentNumber){
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from Note a where a.student.studentNumber=:studentNumber");
        query.setParameter("studentNumber", studentNumber);
        return (Note) getSingleResult(query.getResultList());
    }
}
