/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui;

import java.lang.reflect.Method;

/**
 *
 * @author S.KasımYurtaslan
 */
public class MenuCall {

    String label;
    Method method;

    public MenuCall(String label, Method method) {
        this.label = label;
        this.method = method;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
