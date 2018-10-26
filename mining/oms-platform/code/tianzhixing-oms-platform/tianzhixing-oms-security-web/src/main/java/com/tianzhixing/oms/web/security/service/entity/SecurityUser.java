package com.tianzhixing.oms.web.security.service.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author jinkai.xie
 */
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -4294694251587167808L;

    private UserInfo user;

    public SecurityUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new LinkedList<GrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
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
        return user.getEnable();
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SecurityUser other = (SecurityUser) obj;
        if (this.user.getId().longValue() != other.user.getId().longValue()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31;
        if (null == user) {
            return super.hashCode();
        }
        result = 11 * result + user.getAccount().hashCode();
        return result;
    }

}
