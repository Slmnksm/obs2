/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui.panel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.kasim.system.app.Application;
import tr.kasim.system.gui.MenuCall;
import tr.kasim.system.login.LoginType;
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
public class CommonPanel<T> {

    Class genericType;

    public CommonPanel() {
        genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void showLabel(String label) {
        System.out.println("******************************************");
        System.out.println(label);
        System.out.println("******************************************");

    }

    protected void showMenu(String label, Object obj, List<MenuCall> menuCalls) throws Exception {
        showLabel(label);
        Integer toPage;
        do {
            int count = 1;
            for (MenuCall menuCall : menuCalls) {
                System.out.println((count++) + " -) " + menuCall.getLabel());
            }
            System.out.println((count++) + " -) Geri ");
            System.out.print("Your choise : ");
            toPage = GeneralUtil.toInt(GeneralUtil.readConsole());
            if (toPage != null && toPage > 0 && toPage < count) {
                break;
            } else {
                System.out.println("Wrong selection. Please select from 1 - " + (count - 1));
            }
        } while (true);

        if (menuCalls.size() <= toPage - 1) {
            return;
        }

        MenuCall menuCall = menuCalls.get(toPage - 1);
        if (menuCall == null) {
            return;
        }

        menuCall.getMethod().invoke(obj, new Object[0]);
    }

    protected void showTables(String[][] columns, List data) {

        List<String> fieldNames = new ArrayList();
        List<Integer> tabs = new ArrayList();
        System.out.print("#\t");
        for (String[] columnData : columns) {
            int tab = GeneralUtil.toInt(columnData[2]);
            tabs.add(tab);
            fieldNames.add(columnData[1]);
            System.out.print("|" + String.format("%8s", columnData[0]));
            for (int i = 0; i < tab; i++) {
                System.out.print("\t");
            }
        }
        System.out.print("\n");
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        int count = 1;
        for (Object object : data) {
            System.out.print((count++) + "\t");
            int fieldCount = 0;
            for (String fieldName : fieldNames) {
                String valueData = "-";
                valueData = getFieldValue(object, fieldName);
                System.out.print("|" + String.format("%8s", valueData));
                int tab = tabs.get(fieldCount);
                for (int i = 0; i < tab; i++) {
                    System.out.print("\t");
                }
                fieldCount++;
            }
            System.out.print("\n");
        }

    }

    private String getFieldValue(Object object, String fieldName) {
        String result = "";
        boolean isObject = fieldName.contains(".");
        try {
            String mainFieldName = isObject ? fieldName.substring(0, fieldName.indexOf(".")) : fieldName;
            Field field = object.getClass().getDeclaredField(mainFieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            if (!isObject) {
                return value + "";
            } else {
                String objFieldName = fieldName.substring(fieldName.indexOf(".") + 1, fieldName.length());
                return getFieldValue(value, objFieldName);
            }

        } catch (Exception e) {
        }
        return result;
    }

    public T getObject(String label, String errorText) throws Exception {
        T t = null;
        do {
            try {
                System.out.println(label);
                String value = GeneralUtil.readConsole();

                if ("q".equals(value)) {
                    return null;
                }

                if (genericType == Student.class) {
                    t = (T) Application.getApp().getMainService().getStudentNumber(value);
                } else if (genericType == Lecture.class) {

                    t = (T) Application.getApp().getMainService().getLectureByCode(value);
                }

                if (t == null) {
                    System.out.println(errorText + " Cikmak icin q basin");
                }
            } catch (Exception ex1) {
                Logger.getLogger(LecturePanel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } while (t == null);
        return t;
    }

    public void clearScreen() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ex) {

        }
    }

    protected List<MenuCall> getMenuItems() {

        List<MenuCall> menuCallList = new ArrayList<>();
        LoginType loginType = Application.getApp().getLoginType();

        Method[] methods = this.getClass().getDeclaredMethods();
        Class accessClassType = null;
        for (Method method : methods) {

            MenuItem menuItem = method.getAnnotation(MenuItem.class);

            if (menuItem == null) {
                continue;
            }

            switch (loginType) {
                case Admin: {
                    accessClassType = AdminAccess.class;
                    break;
                }
                case Student: {
                    accessClassType = StudentAccess.class;
                    break;
                }
                case Teacher: {
                    accessClassType = TeacherAccess.class;
                    break;
                }
            }

            if (method.getAnnotation(accessClassType) != null) {

                menuCallList.add(new MenuCall(menuItem.label(), method));
            }
        }
        return menuCallList;
    }
}
