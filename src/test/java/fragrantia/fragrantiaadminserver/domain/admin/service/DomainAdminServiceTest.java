package fragrantia.fragrantiaadminserver.domain.admin.service;

import fragrantia.fragrantiaadminserver.domain.admin.Admin;
import fragrantia.fragrantiaadminserver.domain.admin.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class DomainAdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void 어드민_생성_성공() {
        Admin admin = adminService.create("email", "password", "name", "branch");

        Admin foundAdmin = adminMapper.getAdmin(admin.getId());

        assertThat(foundAdmin.getId()).isNotNull();
        assertThat(foundAdmin.getEmail()).isEqualTo("email");
        assertThat(foundAdmin.getPassword()).isNotNull();
        assertThat(foundAdmin.getName()).isEqualTo("name");
        assertThat(foundAdmin.getBranch()).isEqualTo("branch");
        assertThat(foundAdmin.getCreatedAt()).isNotNull();
    }
}