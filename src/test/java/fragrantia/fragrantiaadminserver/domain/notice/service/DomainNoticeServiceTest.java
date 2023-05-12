package fragrantia.fragrantiaadminserver.domain.notice.service;

import fragrantia.fragrantiaadminserver.domain.admin.Admin;
import fragrantia.fragrantiaadminserver.domain.admin.AdminMapper;
import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import fragrantia.fragrantiaadminserver.domain.notice.NoticeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class DomainNoticeServiceTest {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private AdminMapper adminMapper;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = Admin.create("email", "password", "name", "branch");
        adminMapper.create(admin);
    }

    @Test
    void 공지사항_생성_성공() {
        Notice notice = noticeService.create(admin.getId(), "title", "content");

        Notice foundNotice = noticeMapper.getNotice(notice.getId());

        assertThat(foundNotice.getId()).isNotNull();
        assertThat(foundNotice.getTitle()).isEqualTo("title");
        assertThat(foundNotice.getContent()).isEqualTo("content");
    }
}