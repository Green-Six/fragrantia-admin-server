package fragrantia.fragrantiaadminserver.domain.notice;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;

import static fragrantia.fragrantiaadminserver.utils.Preconditions.require;

@Getter
public class Notice {
    private Long id;
    private Long adminId;
    private String title;
    private String content;
    private Long view = 0L;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Notice(Long id, Long adminId, String title, String content, Long view, LocalDateTime createdAt) {
        this.id = id;
        this.adminId = adminId;
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
    }

    private Notice(String title, String content, Long adminId) {
        this.title = title;
        this.content = content;
        this.adminId = adminId;
    }

    public static Notice create(String title, String content, Long adminId) {
        require(Strings.isNotBlank(title));
        require(Strings.isNotBlank(content));

        return new Notice(title, content, adminId);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
