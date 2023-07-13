package fragrantia.fragrantiaadminserver.domain.brandinfo.service;

import fragrantia.fragrantiaadminserver.domain.brandinfo.BrandInfo;

public interface BrandInfoService {
    BrandInfo getBrandInfo();
    void updateBrandInfo(String historyImgUrl, String historyDetail, String natureImgUrl, String natureDetail, String humanImgUrl, String humanDetail);
}
