package fragrantia.fragrantiaadminserver.domain.notice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    int create(Notice notice);

    Notice getNotice(Long noticeId);

    List<Notice> getNoticesWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int getTotalNoticeCount();



}
