package fragrantia.fragrantiaadminserver.domain.store;

import fragrantia.fragrantiaadminserver.controller.dto.store.GetStoresDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    int create(Store store);

    Store getStore(Long id);

    List<GetStoresDto> getStoresWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int getTotalStoreCount();

    void updateStore(Store store);

    void deleteStore(Store store);
}
