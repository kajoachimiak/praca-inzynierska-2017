package com.pracainzynierska.controller.service.impl;

import com.google.gson.Gson;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.TreeData;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.Course;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.Edition;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.Group;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.User;
import com.pracainzynierska.model.dao.UczestnikDAO;
import com.pracainzynierska.model.entities.Edycja;
import com.pracainzynierska.model.entities.Grupa;
import com.pracainzynierska.model.entities.Przedmiot;
import com.pracainzynierska.model.entities.Uczestnik;
import com.pracainzynierska.model.enums.NodeType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 14.01.18.
 */
@Service("uczestnikService")
public class UczestnikService {
    private static final Logger LOG = Logger.getLogger(UczestnikService.class);

    @Autowired
    private UczestnikDAO uczestnikDAO;

    public UczestnikService() {
    }

    public Uczestnik getUserByLogin(final String login) throws UsernameNotFoundException {
        return uczestnikDAO.findByLogin(login);
    }

    public String getCurrentUserUsername(Principal principal){
        String username;
        try {
            username = principal.getName();
        }catch (NullPointerException e){
            username = null;
            LOG.error("Requested principal does not exist. User not authorized.", e);
        }
        return username;
    }
}
