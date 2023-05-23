package fragrantia.fragrantiaadminserver.domain.notice;

import fragrantia.fragrantiaadminserver.controller.dto.GetNoticesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    int create(Notice notice);

    Notice getNotice(Long noticeId);

    List<GetNoticesDto> getNoticesWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int getTotalNoticeCount();

    void updateNotice(Notice notice);

    void deleteNotice(Notice notice);
}
