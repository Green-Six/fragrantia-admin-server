package fragrantia.fragrantiaadminserver.domain.notice.service;

import fragrantia.fragrantiaadminserver.controller.dto.GetNoticesDto;
import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {
    Notice create(Long adminId, String title, String content);

    List<GetNoticesDto> getNoticesWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int getTotalNoticeCount();
}
