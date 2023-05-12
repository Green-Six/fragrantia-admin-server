package fragrantia.fragrantiaadminserver.domain.notice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NoticeTest {

    @Test
    void 공지사항_생성_성공() {
        Notice notice = Notice.create("title", "content", 1L);

        assertThat(notice.getTitle()).isEqualTo("title");
        assertThat(notice.getContent()).isEqualTo("content");
        assertThat(notice.getAdminId()).isEqualTo(1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 공지사항_생성_실패_제목이_없음(String title) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Notice.create(title, "content", 1L)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 공지사항_생성_실패_본문이_없음(String content) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Notice.create("title", content, 1L)
        );
    }
}
