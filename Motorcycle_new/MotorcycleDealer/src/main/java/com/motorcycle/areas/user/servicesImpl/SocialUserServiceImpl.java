package com.motorcycle.areas.user.servicesImpl;

import com.motorcycle.areas.role.services.RoleService;
import com.motorcycle.areas.user.entities.SocialUser;
import com.motorcycle.areas.user.services.SocialUserService;
import com.motorcycle.areas.user.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if (socialUser == null) {
            socialUser = registerUser(email);
        }

        loginUser(socialUser);
    }

    private SocialUser registerUser(String email) {
        SocialUser user = new SocialUser();
        user.setUsername(email);
        user.setProvider("FACEBOOK");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.socialUserRepository.save(user);
        return user;
    }

    private void loginUser(SocialUser socialUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser.getUsername(), null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
