package com.pracainzynierska.model.daoservice;

import com.pracainzynierska.model.dao.UczestnikDAO;
import com.pracainzynierska.model.dao.impl.UczestnikDAOImpl;
import com.pracainzynierska.model.entities.Rola;
import com.pracainzynierska.model.entities.Uczestnik;
import com.pracainzynierska.util.HibernateUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by karol on 14.01.18.
 * http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
 */
@Service("uczestnikService")
public class UczestnikService {
    private UczestnikDAO uczestnikDAO;

    public UczestnikService() {
        this.uczestnikDAO = new UczestnikDAOImpl(HibernateUtil.getSessionFactory());
    }

    public UserDetails loadUserByLogin(final String login) throws UsernameNotFoundException {
        Uczestnik uczestnik = uczestnikDAO.findByLogin(login);
        List<GrantedAuthority> authorities =
                buildUserAuthority(uczestnik.getRola());
        return  buildUserForAuthentication(uczestnik, authorities);
    }

    private User buildUserForAuthentication(Uczestnik uczestnik,
                                            List<GrantedAuthority> authorities) {
        return new User(uczestnik.getLogin(), uczestnik.getHaslo(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Rola> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (Rola userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getNazwa()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}
