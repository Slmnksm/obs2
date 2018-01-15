/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui.panel;

import java.util.List;
import tr.kasim.system.app.Application;
import tr.kasim.system.model.Student;
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
@Panel(name = "student", label = "Student Menu")
public class StudentPanel extends CommonPanel<Student> implements MainPanel {

    @Override
    public void display() {
        try {
            showMenu("Student Menu", this, getMenuItems());

        } catch (Exception e) {
            System.out.println("Hata olustu : msg:" + e.getMessage());
            e.printStackTrace();
        }
        Application.getApp().getGuiService().display();
    }

    @MenuItem(label = "Yeni Ogrenci Ekle")
    @AdminAccess()
    public void addStudent() {
        System.out.println("Yeni Ogrenci Ekle");
        try {
            System.out.print("Ogrenci Adi Giriniz: ");
            String studentName = GeneralUtil.readConsole();

            System.out.print("Ogrenci Soyadi Giriniz: ");
            String studentSurname = GeneralUtil.readConsole();

            System.out.print("Ogrenci Numarasi Giriniz: ");
            String studentNumber = GeneralUtil.readConsole();

            System.out.print("Ogrenci Adresi Giriniz: ");
            String studentAddress = GeneralUtil.readConsole();

            System.out.print("Ogrenci Telefonu Giriniz: ");
            String studentTel = GeneralUtil.readConsole();

            Student student = new Student();

            student.setName(studentName);
            student.setSurname(studentSurname);
            student.setStudentNumber(studentNumber);
            student.setAddress(studentAddress);
            student.setTel(studentTel);

            student.setUser(new User());

            student.getUser().setUsername(studentNumber);

            System.out.print("Ogrenci Şifresini Giriniz: ");
            String studentPass = GeneralUtil.readConsole();
            student.getUser().setPassword(studentPass);
            student.getUser().setType(2);

            Student savedStudent = Application.getApp().getMainService().saveStudent(student);

            System.out.println(" " + studentName + " " + studentSurname + " isimli ogrenci basariyla eklendi...");

        } catch (Exception ex1) {
        }
    }

    @MenuItem(label = "Tum ogrencileri listele")
    @TeacherAccess()
    @AdminAccess()
    @StudentAccess()
    public void displayStudents(List<Student> students) {
        try {
            showTables(new String[][]{
                {"id", "id", "1"},
                {"No", "studentNumber", "1"},
                {"Adi", "name", "2"},
                {"Soyadi", "surname", "2"},
                {"Adres", "address", "3"},}, students);
        } catch (Exception ex) {
            System.out.println("Ogrenci listesi gosterimde hata olustu .Msg:" + ex.getMessage());
        }

    }

    @StudentAccess
    @TeacherAccess
    @AdminAccess
    @MenuItem(label = "Ogrencinin Aldigi Dersleri Goster")
    public void displayStudentLectures() throws Exception {
        Student student = readStudentByStudentNumber();
        if (student == null) {
            return;
        }
        try {
            System.out.println(student.getName() + " " + student.getSurname() + " Isimli Ogrencinin Aldigi Dersler: ");
            showTables(new String[][]{
                {"id", "id", "1"},
                {"Kodu", "code", "2"},
                {"Adi", "name", "2"},
                {"Kapasite", "capacity", "1"},}, student.getLectures());
        } catch (Exception ex) {
            System.out.println("Ders listesi gosterimde hata olustu .Msg:" + ex.getMessage());
        }
    }

    public Student readStudentByStudentNumber() throws Exception {
        return getObject("Ogrenci No giriniz:", "Girdiginiz no ya gore ogrenci bulunamadi");
    }

}
