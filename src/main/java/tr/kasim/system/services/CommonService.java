/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.system.services;

import java.util.List;
import tr.kasim.system.db.Session;
import tr.kasim.system.db.dao.CommonDao;

/**
 *
 * @author S.KasımYurtaslan
 */
public class CommonService<T> {

    CommonDao<T> dao;

    public CommonService(CommonDao dao) {
        this.dao = dao;
    }

    public T save(Session session, T object) {
        return (T) dao.save(session, object);
    }

    public List<T> getAll(Session session) {
        return dao.getAll(session);
    }

    public T getById(Session session, int id) {
        return (T) dao.getById(session, id);
    }

    public CommonDao<T> getDao() {
        return dao;
    }

}
