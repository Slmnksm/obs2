/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.services;

import tr.kasim.system.db.dao.TeacherDao;
import tr.kasim.system.model.Teacher;

/**
 *
 * @author S.KasımYurtaslan
 */
public class TeacherService extends CommonService<Teacher> {

    TeacherDao teacherDao;

    public TeacherService() {
        super(new TeacherDao());
        teacherDao = (TeacherDao) getDao();
    }

}
