/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.services;

import tr.kasim.system.db.dao.UserDao;
import tr.kasim.system.model.User;

/**
 *
 * @author S.KasımYurtaslan
 */
public class UserService extends CommonService<User>{
    
    UserDao userDao;
    
    public UserService(){
        super(new UserDao());
        userDao = (UserDao) getDao();
    }
    
    
}
