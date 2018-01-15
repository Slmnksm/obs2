/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.login;

/**
 *
 * @author S.KasımYurtaslan
 */
public enum LoginType {
    Admin(1),
    Student(2),
    Teacher(3);

    int type;

    private LoginType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
