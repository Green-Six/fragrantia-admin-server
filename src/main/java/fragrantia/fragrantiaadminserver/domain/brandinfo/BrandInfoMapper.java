package fragrantia.fragrantiaadminserver.domain.brandinfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandInfoMapper {

    BrandInfo getBrandInfo();

    void updateBrandInfo(BrandInfo brandInfo);
}
