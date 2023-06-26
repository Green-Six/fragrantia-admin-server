package fragrantia.fragrantiaadminserver.security;

import fragrantia.fragrantiaadminserver.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AdminService adminService;

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http
    ) throws Exception {

        http.csrf().disable();
        http.cors().disable();

        http
            .authorizeRequests()
            .antMatchers(
                HttpMethod.GET,
                "/css/**",
                "/js/**",
                "/images/**",
                "/html/**"
            ).permitAll()
            .mvcMatchers(
                "/auth/login",
                "/auth/join",
                "/auth/find-account"
            ).permitAll()
            .antMatchers("/admin", "/admin/idCheck", "/admin/findPw", "/admin/findPw/sendEmail").permitAll()
            .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/auth/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .permitAll();

        return http.build();
    }
}
