package fragrantia.fragrantiaadminserver.domain.notice.service;

import fragrantia.fragrantiaadminserver.domain.notice.Notice;

public interface NoticeService {
    Notice create(Long adminId, String title, String content);
}
