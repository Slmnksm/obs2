/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.kasim.system.app.Application;
import tr.kasim.system.model.Lecture;
import tr.kasim.system.model.Student;
import tr.kasim.system.right.AdminAccess;
import tr.kasim.system.right.MenuItem;
import tr.kasim.system.right.StudentAccess;
import tr.kasim.system.right.TeacherAccess;
import tr.kasim.system.util.GeneralUtil;

/**
 *
 * @author S.KasımYurtaslan
 */
@Panel(name = "lecture", label = "Lecture Menu")
public class LecturePanel extends CommonPanel<Lecture> implements MainPanel {

    @Override
    public void display() {

        try {
            showMenu("Lecture Menu", this, getMenuItems());

        } catch (Exception e) {
            System.out.println("Hata olustu : msg:" + e.getMessage());
            e.printStackTrace();
        }
        Application.getApp().getGuiService().display();

    }

    @MenuItem(label = "Yeni Ders Ekle")
    @AdminAccess()
    private void addLecture() {
        try {
            System.out.println("Yeni Ders Ekle");

            System.out.print("Ders Adi Giriniz: ");
            String lectureName = GeneralUtil.readConsole();

            System.out.print("Ders Kodu Giriniz : ");
            String lectureCode = GeneralUtil.readConsole();

            System.out.print("Ogrenci Kapasitesini Giriniz: ");
            String lectureCapacity = GeneralUtil.readConsole();

            Lecture lecture = new Lecture();
            lecture.setName(lectureName);
            lecture.setCode(lectureCode);
            lecture.setCapacity(lectureCapacity);

            Lecture savedLecture = Application.getApp().getMainService().addLecture(lecture);

            System.out.println(" " + lectureName + " dersi basariyla eklendi..");
            GeneralUtil.readConsole();

        } catch (Exception ex) {
            Logger.getLogger(LecturePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @MenuItem(label = "Tum Dersleri Göster")
    @AdminAccess
    @TeacherAccess
    @StudentAccess
    public void displayLectures() throws Exception {
        displayLectures(Application.getApp().getMainService().getLectures());
    }

    public void displayLectures(List<Lecture> lectures) {
        showTables(new String[][]{
            {"id", "id", "1"},
            {"Kodu", "code", "2"},
            {"Adi", "name", "2"},
            {"Kapasite", "capacity", "1"},}, lectures);
    }

    @MenuItem(label = "Derse Ogrenci Ekle")
    @AdminAccess
    @TeacherAccess
    private void addLectureToStudent() throws Exception {

        Lecture lecture = readLectureByCode();

        if (lecture == null) {
            return;
        }

        StudentPanel studentPanel = ((StudentPanel) Application.getApp().getGuiService().getPanelByName("student"));

        Student student = studentPanel.readStudentByStudentNumber();

        if (student == null) {
            return;
        }

        if (student.getLectures() == null) {
            student.setLectures(new ArrayList());
        }
        System.out.println(student.getName() + " " + student.getSurname() + " " + lecture.getName() + " dersine basariyla eklendi..");

        student.getLectures().add(lecture);

        GeneralUtil.readConsole();

        Application.getApp().getMainService().saveStudent(student);
    }

    @MenuItem(label = "Dersi Alan Ogrencileri Goster")
    @AdminAccess
    @TeacherAccess
    public void showStudentByLecture() throws Exception {
        Lecture lecture = readLectureByCode();
        if (lecture == null) {
            return;
        }
        System.out.println(lecture.getName() + " Dersini Alan Ogrenciler: ");
        ((StudentPanel) Application.getApp().getGuiService().getPanelByName("student")).displayStudents(lecture.getStudents());
    }

    public Lecture readLectureByCode() throws Exception {
        return getObject("Ders kodu:", "Verdiginiz koda gore ders bulunamadi.");
    }

}
