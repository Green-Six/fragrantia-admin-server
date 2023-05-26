package fragrantia.fragrantiaadminserver.domain.store.service;

import fragrantia.fragrantiaadminserver.controller.dto.store.GetStoresDto;
import fragrantia.fragrantiaadminserver.domain.store.Store;

import java.util.List;

public interface StoreService {
    Store create(Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file);

    List<GetStoresDto> getStoresWithPaging(int offset, int limit);

    int getTotalStoreCount();

    void updateStore(Long id, Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file);

    void deleteStore(Long Id);
}
