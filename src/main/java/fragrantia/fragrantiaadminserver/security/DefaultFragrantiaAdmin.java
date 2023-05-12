package fragrantia.fragrantiaadminserver.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class DefaultFragrantiaAdmin implements UserDetails, FragrantiaAdmin {
    private Long id;
    private String username;
    private String password;
    private boolean isEnabled = true;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

    public DefaultFragrantiaAdmin(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}