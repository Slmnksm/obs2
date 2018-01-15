/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.gui;

import java.util.ArrayList;
import java.util.List;
import tr.kasim.system.gui.panel.MainPanel;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;
import tr.kasim.system.gui.panel.CommonPanel;
import tr.kasim.system.gui.panel.Panel;
import tr.kasim.system.model.Student;
import tr.kasim.system.util.GeneralUtil;

/**
 *
 * @author S.KasımYurtaslan
 */
public class UIService extends CommonPanel<Student> implements MainPanel {

    Map<String, MainPanel> panels = new TreeMap<>();

    List<MainPanel> panelList = new ArrayList();

    public void init() {
        for (Class type : getPanels()) {
            try {
                Panel panel = (Panel) type.getAnnotation(Panel.class);
                MainPanel mainPanel = (MainPanel) type.newInstance();
                panels.put(panel.name(), mainPanel);
                panelList.add(mainPanel);
            } catch (InstantiationException ex) {
                Logger.getLogger(UIService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UIService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        display();
    }

    @Override
    public void display() {
        showLabel("Main Menu");
        Integer toPage;
        do {
            int count = 1;
            for (MainPanel mainPanel : panelList) {
                Panel panel = mainPanel.getClass().getAnnotation(Panel.class);
                System.out.println((count++) + " -) " + panel.label());
            }
            System.out.println((count++) + " -) Exit");
            System.out.print("Your choise : ");
            toPage = GeneralUtil.toInt(GeneralUtil.readConsole());
            if (toPage != null && toPage > 0 && toPage < count) {
                break;
            } else {
                System.out.println("Wrong selection. Please select from 1 - " + (count - 1));
            }
        } while (true);

        if (panelList.size() == toPage - 1) {
            System.exit(0);
        }
        panelList.get(toPage - 1).display();
    }

    private Set<Class<?>> getPanels() {
        Reflections reflections = new Reflections("tr.kasim.system.gui");
        return reflections.getTypesAnnotatedWith(Panel.class);
    }

    public MainPanel getPanelByName(String name) {
        return panels.get(name);
    }

}
