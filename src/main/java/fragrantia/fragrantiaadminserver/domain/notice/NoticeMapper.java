package fragrantia.fragrantiaadminserver.domain.notice;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    int create(Notice notice);

    Notice getNotice(Long noticeId);

    List<Notice> getNotices();

}
