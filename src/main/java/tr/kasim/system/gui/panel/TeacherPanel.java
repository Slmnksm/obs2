/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui.panel;

import java.util.List;
import tr.kasim.system.app.Application;
import tr.kasim.system.model.Teacher;
import tr.kasim.system.model.User;
import tr.kasim.system.right.AdminAccess;
import tr.kasim.system.right.MenuItem;
import tr.kasim.system.right.StudentAccess;
import tr.kasim.system.right.TeacherAccess;
import tr.kasim.system.util.GeneralUtil;

/**
 *
 * @author S.KasımYurtaslan
 */
@Panel(name = "teacher", label = "Teacher Menu")
public class TeacherPanel extends CommonPanel<Teacher> implements MainPanel {

    @Override
    public void display() {
        try {
            showMenu("Teacher Menu", this, getMenuItems());

        } catch (Exception e) {
            System.out.println("Hata olustu : msg:" + e.getMessage());
            e.printStackTrace();
        }
        Application.getApp().getGuiService().display();
    }

    @MenuItem(label = "Yeni Ogretmen Ekle")
    @AdminAccess
    public void addTeacher() throws Exception {

        System.out.println("Yeni Ogretmen Ekle");

        try {
            System.out.print("Ogretmen Adini Giriniz: ");
            String teacherName = GeneralUtil.readConsole();
            System.out.print("Ogretmen Soyadini Giriniz: ");
            String teacherSurname = GeneralUtil.readConsole();

            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            teacher.setSurname(teacherSurname);
            teacher.setUser(new User());
            teacher.getUser().setUsername(teacherName);
            System.out.print("Ogretmen Şifresini Giriniz: ");
            String teacherPass = GeneralUtil.readConsole();
            teacher.getUser().setPassword(teacherPass);
            teacher.getUser().setType(3);

            Teacher savedTeacher = Application.getApp().getMainService().saveTeacher(teacher);

            System.out.println(" " + teacherName + " " + teacherSurname + " isimli ogretmen basariyla eklendi...");
        } catch (Exception e) {
            System.out.println("Bir hata olustu...");
        }
    }

    @MenuItem(label = "Tum Ogretmenleri Goster")
    @AdminAccess
    @TeacherAccess
    @StudentAccess
    public void showAllTeachers(List<Teacher> teachers) {
        try {
            showTables(new String[][]{
                {"id", "id", "1"},
                {"Adi", "teacherName", "2"},
                {"Soyadi", "teacherSurname", "2"},}, teachers);
        } catch (Exception ex) {
            System.out.println("Ogrenci listesi gosterimde hata olustu .Msg:" + ex.getMessage());
        }
    }
}
