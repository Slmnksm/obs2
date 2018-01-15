/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.app;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import tr.kasim.system.gui.UIService;
import tr.kasim.system.login.LoginType;
import tr.kasim.system.remote.MainService;

/**
 *
 * @author S.KasımYurtaslan
 */
public class Application {
    
    static Application app;
    EntityManagerFactory entityManagerFactory;
    MainService mainService = new MainService();
    UIService guiService = new UIService();
    
    Logger log;
    
    Object loginUserObj;
    LoginType loginType;
    
    public static void main(String[] args) {
        app = new Application();
        app.init();
    }
    
    public static Application getApp() {
        return app;
    }
    
    private void init() {
        setLoginType(LoginType.Admin);
        initConsoleLog();                //***********BU SATIR OLMADAN MAIN CALISMAZ***********
        initFileLog();
        initDb();
        initDisplay();
        
    }
    
    private void initDb() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ogrenci");
        if (log.isDebugEnabled()) {
            log.debug("[Application][Db] initialized");
        }
    }
    
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
    private void initFileLog() {
      /*  PropertyConfigurator.configure(ClassLoader.getSystemResource("./log4j/logger.properties"));
        
        log = Logger.getLogger(Application.class);
        if (log.isDebugEnabled()) {
            log.debug("[Application][Logger] initialized");
        }*/
    }
    
    private void initConsoleLog() {
        Properties logProperties = new Properties();
        logProperties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        logProperties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        logProperties.setProperty("log4j.appender.stdout.layout.conversionPattern", "%-5p-%d{ISO8601}: %-10t :[%C{1}][%M]: %m%n");
        logProperties.setProperty("log4j.logger.tr.kasim", "DEBUG,stdout");
        PropertyConfigurator.configure(logProperties);
        
        log = Logger.getLogger(Application.class);
        if (log.isDebugEnabled()) {
            log.debug("[Application][Logger] initialized");
        }
    }
    
    public MainService getMainService() {
        return mainService;
    }
    
    public UIService getGuiService() {
        return guiService;
    }
    
    public Object getLoginUserObj() {
        return loginUserObj;
    }
    
    public void setLoginUserObj(Object loginUserObj) {
        this.loginUserObj = loginUserObj;
    }
    
    public LoginType getLoginType() {
        return loginType;
    }
    
    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
    
    private void initDisplay() {
        if (log.isDebugEnabled()) {
            log.debug("[Application][Display] Starting");
        }
        guiService.init();
    }
    
}
