package com.pracainzynierska.model.daoservice;

import com.pracainzynierska.model.dao.SzablonyDAO;
import com.pracainzynierska.model.dao.SzablonyDAOImpl;
import com.pracainzynierska.model.entities.Szablony;
import com.pracainzynierska.util.HibernateUtil;

/**
 * Created by karol on 11.12.17.
 */
public class SzablonyService {
    private SzablonyDAO szablonyDAO;

    public SzablonyService() {
        this.szablonyDAO = new SzablonyDAOImpl(HibernateUtil.getSessionFactory());
    }
    public Szablony getSzablonBySzablonId(Integer szablonId){
        return szablonyDAO.getById(szablonId);
    }
}
