/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.kasim.system.app.Application;
import tr.kasim.system.model.Lecture;
import tr.kasim.system.model.Note;
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
@Panel(name = "note", label = "Note Menu")
public class NotePanel extends CommonPanel<Note> implements MainPanel {

    @Override
    public void display() {
        try {
             showMenu("Note Menu", this, getMenuItems());
        } catch (Exception e) {
            System.out.println("Bir hata olustu. Msg:" + e.getMessage());
        }
        Application.getApp().getGuiService().display();
    }

    @MenuItem(label = "Not Duzenleme")
    @AdminAccess
    @TeacherAccess
    public void addNote() throws Exception {
        LecturePanel lecturePanel = ((LecturePanel) Application.getApp().getGuiService().getPanelByName("lecture"));

        Lecture lecture = lecturePanel.readLectureByCode();
        if (lecture == null) {
            return;
        }

        Map<String, Note> noteMapByStudentId = new TreeMap();
        for (Student student : lecture.getStudents()) {
            Note note = Application.getApp().getMainService().getNoteByStudentAndLectureId(student.getStudentNumber(), lecture.getCode());
            if (note == null) {
                note = new Note();
                note.setNoteNumber("-");
                note.setStudent(student);
                note.setLecture(lecture);
            }
            noteMapByStudentId.put(student.getStudentNumber(), note);
        }

        for (int i = 0; i < lecture.getStudents().size(); i++) {
            Student student = lecture.getStudents().get(i);
            clearScreen();
            showNoteDetails(new ArrayList(noteMapByStudentId.values()));

            Note note = noteMapByStudentId.get(student.getStudentNumber());
            System.out.print("Ogrenci : " + student.getName() + " " + student.getSurname() + "(" + student.getStudentNumber() + ") (s: asagi , w:yukari,q:cikis) : ");
            String noteValues = GeneralUtil.readConsole();
            if ("s".equals(noteValues)) {
                continue;
            } else if ("w".equals(noteValues)) {
                i -= 2;
                continue;
            } else if ("q".equals(noteValues)) {
                break;
            }

            note.setNoteNumber(noteValues);

            if (note.getId() == null) {
                student.getNotes().add(note);
            }

            try {
                Application.getApp().getMainService().saveStudent(student);
            } catch (Exception ex) {
                Logger.getLogger(LecturePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        showNoteDetails(new ArrayList(noteMapByStudentId.values()));
        System.out.println("Devam etmek icin enter'a basin");
        GeneralUtil.readConsole();

    }

    @MenuItem(label = "Ogrencinin Tum Notlarini Goster")
    @AdminAccess
    @TeacherAccess
    @StudentAccess
    private void showAllNotesOfStudent() throws Exception {
        StudentPanel studentPanel = ((StudentPanel) Application.getApp().getGuiService().getPanelByName("student"));

        Student student = studentPanel.readStudentByStudentNumber();
        if (student == null) {
            return;
        }
        showNoteDetails(student.getNotes());

    }

    private void showNoteDetails(List notes) {
        try {
            showTables(new String[][]{
                {"Ders Adi", "lecture.name", "2"},
                {"Ogr No", "student.studentNumber", "1"},
                {"Ogr Adi", "student.name", "2"},
                {"Ogr Soy", "student.surname", "2"},
                {"Notu", "noteNumber", "1"},}, notes);
        } catch (Exception ex) {
            System.out.println("Ders listesi gosterimde hata olustu .Msg:" + ex.getMessage());
        }

    }
}
