package fragrantia.fragrantiaadminserver.domain.notice.service;

import fragrantia.fragrantiaadminserver.controller.dto.GetNoticesDto;
import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import fragrantia.fragrantiaadminserver.domain.notice.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<GetNoticesDto> getNoticesWithPaging(int offset, int limit) {
        return noticeMapper.getNoticesWithPaging(offset, limit);
    }

    @Override
    public int getTotalNoticeCount() {
        return noticeMapper.getTotalNoticeCount();
    }
}
