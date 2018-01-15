/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.services;

import tr.kasim.system.db.Session;
import tr.kasim.system.db.dao.LectureDao;
import tr.kasim.system.model.Lecture;

/**
 *
 * @author S.KasımYurtaslan
 */
public class LectureService extends CommonService<Lecture> {

    LectureDao lectureDao;

    public LectureService() {
        super(new LectureDao());
        lectureDao = (LectureDao) getDao();
    }

    public Lecture getByCode(Session session, String code) {
        return lectureDao.getByCode(session, code);
    }
    
    
    
}
