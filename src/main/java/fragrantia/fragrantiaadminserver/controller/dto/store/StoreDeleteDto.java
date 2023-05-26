package fragrantia.fragrantiaadminserver.controller.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class StoreDeleteDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreDeleteRequest {
        private List<Long> ids;
    }
}
