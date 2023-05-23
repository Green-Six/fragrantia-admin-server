package fragrantia.fragrantiaadminserver.domain.notice.service;

import fragrantia.fragrantiaadminserver.controller.dto.GetNoticesDto;
import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {
    Notice create(Long adminId, String title, String content);

    List<GetNoticesDto> getNoticesWithPaging(int offset, int limit);

    int getTotalNoticeCount();

    void updateNotice(String title, String content, Long id);

    void deleteNotice(Long Id);
}
