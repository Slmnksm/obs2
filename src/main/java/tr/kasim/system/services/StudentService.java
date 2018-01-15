/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.services;

import tr.kasim.system.db.Session;
import tr.kasim.system.db.dao.StudentDao;
import tr.kasim.system.model.Student;

/**
 *
 * @author S.KasımYurtaslan
 */
public class StudentService extends CommonService<Student> {

    StudentDao studentDao;

    public StudentService() {
        super(new StudentDao());
        studentDao = (StudentDao) getDao();
    }

     public Student getStudentNumber(Session session, String studentNumber) {
        return studentDao.getByNumber(session, studentNumber);
    }
}
