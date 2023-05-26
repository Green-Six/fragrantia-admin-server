package fragrantia.fragrantiaadminserver.domain.store.service;

import fragrantia.fragrantiaadminserver.controller.dto.store.GetStoresDto;
import fragrantia.fragrantiaadminserver.domain.store.Store;
import fragrantia.fragrantiaadminserver.domain.store.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainStoreService implements StoreService{

    private final StoreMapper storeMapper;

    @Override
    public Store create(Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file) {
        Store store = Store.create(latitude, longitude, zip, address, name, detail, telephone, file);

        storeMapper.create(store);

        return store;
    }

    @Override
    public List<GetStoresDto> getStoresWithPaging(int offset, int limit) {
        return storeMapper.getStoresWithPaging(offset, limit);
    }

    @Override
    public int getTotalStoreCount() {
        return storeMapper.getTotalStoreCount();
    }

    @Override
    public void updateStore(Long id, Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file) {
        Store store = storeMapper.getStore(id);

        store.update(latitude, longitude, zip, address, name, detail, telephone, file);

        storeMapper.updateStore(store);
    }

    @Override
    public void deleteStore(Long id) {
        Store store = storeMapper.getStore(id);

        storeMapper.deleteStore(store);
    }
}
