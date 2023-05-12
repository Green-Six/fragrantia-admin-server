package fragrantia.fragrantiaadminserver.domain.admin.service;


import fragrantia.fragrantiaadminserver.domain.admin.Admin;
import fragrantia.fragrantiaadminserver.domain.admin.AdminMapper;
import fragrantia.fragrantiaadminserver.security.DefaultFragrantiaAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainAdminService implements AdminService, UserDetailsService {

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin create(String email, String password, String name, String branch) {
        String encodePassword = passwordEncoder.encode(password);

        Admin admin = Admin.create(email, encodePassword, name, branch);

        adminMapper.create(admin);

        return admin;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminMapper.getByEmail(email);

        if (admin == null)
            throw new UsernameNotFoundException("error");

        return new DefaultFragrantiaAdmin(admin.getId(), admin.getEmail(), admin.getPassword());
    }
}
