package com.pracainzynierska.controller.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pracainzynierska.controller.helper.JsonBuilderHelper;
import com.pracainzynierska.controller.helper.jsonObjects.Course;
import com.pracainzynierska.controller.helper.jsonObjects.Edition;
import com.pracainzynierska.controller.helper.jsonObjects.Group;
import com.pracainzynierska.controller.helper.jsonObjects.User;
import com.pracainzynierska.model.dao.UczestnikDAO;
import com.pracainzynierska.model.entities.Edycja;
import com.pracainzynierska.model.entities.Grupa;
import com.pracainzynierska.model.entities.Przedmiot;
import com.pracainzynierska.model.entities.Uczestnik;
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

    public String buildUserRelationsResponse(String username){
        Uczestnik user = getUserByLogin(username);
        Grupa grup = user.getGrupa();
        Edycja edition = grup.getEdycja();
        Przedmiot course = edition.getPrzedmiot();

        List<Course> courses = new ArrayList<>();
        List<Edition> editions = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        List<User> users = new ArrayList<>();

        users.add(new User(user.getLogin()));
        groups.add(new Group(grup.getNazwa(),users));
        editions.add(new Edition(edition.getNazwa(), groups));
        courses.add(new Course(course.getNazwa(), editions));

        JsonBuilderHelper jsonBuilderHelper = new JsonBuilderHelper(courses);

        return new Gson().toJson(jsonBuilderHelper);
    }
}
