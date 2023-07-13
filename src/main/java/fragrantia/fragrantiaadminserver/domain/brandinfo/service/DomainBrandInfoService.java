package fragrantia.fragrantiaadminserver.domain.brandinfo.service;

import fragrantia.fragrantiaadminserver.domain.brandinfo.BrandInfo;
import fragrantia.fragrantiaadminserver.domain.brandinfo.BrandInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainBrandInfoService implements BrandInfoService{

    private final BrandInfoMapper brandInfoMapper;

    @Override
    public BrandInfo getBrandInfo() {

        return brandInfoMapper.getBrandInfo();
    }

    @Override
    public void updateBrandInfo(String historyImgUrl, String historyDetail, String natureImgUrl, String natureDetail, String humanImgUrl, String humanDetail) {
        BrandInfo brandInfo = brandInfoMapper.getBrandInfo();

        brandInfo.update(historyImgUrl, historyDetail, natureImgUrl, natureDetail, humanImgUrl, humanDetail);

        brandInfoMapper.updateBrandInfo(brandInfo);
    }
}
