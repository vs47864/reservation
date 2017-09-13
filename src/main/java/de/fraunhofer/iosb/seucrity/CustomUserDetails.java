package de.fraunhofer.iosb.seucrity;


import de.fraunhofer.iosb.entity.Role;
import de.fraunhofer.iosb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CustomUserDetails implements UserDetails
{
    private User user;
    private List<CustomGrantedAuthority> authorities;

    public CustomUserDetails(User user, List<Role> roles) {
        this.user = user;
        authorities = new LinkedList<>();
        for(Role role: roles) {
            authorities.add(new CustomGrantedAuthority(role.getRole()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static class CustomGrantedAuthority implements GrantedAuthority {
        private String authority;

        private CustomGrantedAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }
}
