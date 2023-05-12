package fragrantia.fragrantiaadminserver.domain.notice.service;

import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import fragrantia.fragrantiaadminserver.domain.notice.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainNoticeService implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public Notice create(Long adminId, String title, String content) {
        Notice notice = Notice.create(title, content, adminId);

        noticeMapper.create(notice);

        return notice;
    }
}
