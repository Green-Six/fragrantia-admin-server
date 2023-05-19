package fragrantia.fragrantiaadminserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetNoticesDto {
    private Long id;
    private String name;
    private String title;
    private String content;
    private Long view;
    private LocalDateTime createdAt;
}
