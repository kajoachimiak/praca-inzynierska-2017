package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.dao.SzablonyDAO;
import com.pracainzynierska.model.entities.Szablony;

/**
 * Created by karol on 11.12.17.
 */
public class SzablonyService {
    private SzablonyDAO szablonyDAO;

    public SzablonyService() {
    }
    public Szablony getSzablonBySzablonId(Integer szablonId){
        return szablonyDAO.getById(szablonId);
    }
}
