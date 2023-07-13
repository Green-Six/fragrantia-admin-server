package fragrantia.fragrantiaadminserver.domain.brandinfo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BrandInfo {
    private Long id = 1L;
    private String historyImgUrl;
    private String historyDetail;
    private String natureImgUrl;
    private String natureDetail;
    private String humanImgUrl;
    private String humanDetail;
    private LocalDateTime createdAt = LocalDateTime.now();

    public BrandInfo() {
    }

    public BrandInfo(Long id, String historyImgUrl, String historyDetail, String natureImgUrl, String natureDetail, String humanImgUrl, String humanDetail, LocalDateTime createdAt) {
        this.id = id;
        this.historyImgUrl = historyImgUrl;
        this.historyDetail = historyDetail;
        this.natureImgUrl = natureImgUrl;
        this.natureDetail = natureDetail;
        this.humanImgUrl = humanImgUrl;
        this.humanDetail = humanDetail;
        this.createdAt = createdAt;
    }

    public void update(String historyImgUrl, String historyDetail, String natureImgUrl, String natureDetail, String humanImgUrl, String humanDetail) {
        this.historyImgUrl = historyImgUrl;
        this.historyDetail = historyDetail;
        this.natureImgUrl = natureImgUrl;
        this.natureDetail = natureDetail;
        this.humanImgUrl = humanImgUrl;
        this.humanDetail = humanDetail;
    }
}
