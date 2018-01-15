/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.services;

import tr.kasim.system.db.Session;
import tr.kasim.system.db.dao.NoteDao;
import tr.kasim.system.model.Note;

/**
 *
 * @author S.KasımYurtaslan
 */
public class NoteService extends CommonService<Note> {

    NoteDao noteDao;

    public NoteService() {
        super(new NoteDao());
        noteDao = (NoteDao) getDao();
    }

    public Note getNoteByStudentAndLectureId(Session session, String studentNumber, String lectureCode) {
        return noteDao.getNoteByStudentAndLectureId(session, studentNumber, lectureCode);
    }

     public Note getAllNotesByStudentNumber(Session session, String studentNumber) {
        return noteDao.getAllNotesByStudentNumber(session, studentNumber);
    }
}
