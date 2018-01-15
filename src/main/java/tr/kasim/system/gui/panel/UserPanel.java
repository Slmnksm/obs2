/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui.panel;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.kasim.system.app.Application;
import tr.kasim.system.model.User;
import tr.kasim.system.util.*;

/**
 *
 * @author S.KasımYurtaslan
 */
@Panel(name = "user", label = "User Menu")
public class UserPanel extends CommonPanel<User> implements MainPanel {

    @Override
    public void display() {
        try {
            /*
        String[] menuItems = new String[]{"User Duzenle", "Tum Userlari Göster"};
        int selection = showMenu("User Menu", menuItems);
    
            switch (selection) {
                case 0: {
                    addUser();
                    break;
                }
                case 1: {
                    //displayUsers(Application.getApp().getMainService().get?Users?);
                    break;
                }

            }*/
        } catch (Exception e) {
            System.out.println("Bir hata olustu. msg:" + e.getMessage());
        }
        Application.getApp().getGuiService().display();

    }

    public void addUser() {
        Scanner input1 = new Scanner(System.in);
        try {
            System.out.println("Yeni User Ekle");

            System.out.print("Username Giriniz: ");
            String username = GeneralUtil.readConsole();

            System.out.print("Password Giriniz : ");
            String password = GeneralUtil.readConsole();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            user.setType(1);

            User savedUser = Application.getApp().getMainService().saveUser(user);

            System.out.println(" " + username + " dersi basariyla eklendi..");
            GeneralUtil.readConsole();

        } catch (Exception ex) {
            Logger.getLogger(LecturePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayUsers() {

    }
}
