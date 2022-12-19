package com.example.club.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User {

    private String email;
    private String name;
    private boolean fromSocial;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return super.getUsername().equals(((User) obj).getUsername());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.getUsername().hashCode();
    }


    public ClubAuthMemberDTO(String username, String password, boolean fromSocial,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
