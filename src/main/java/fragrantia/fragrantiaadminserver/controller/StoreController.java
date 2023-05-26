package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.store.GetStoresDto;
import fragrantia.fragrantiaadminserver.controller.dto.store.StoreCreateDto;
import fragrantia.fragrantiaadminserver.controller.dto.store.StoreDeleteDto;
import fragrantia.fragrantiaadminserver.controller.dto.store.StoreUpdateDto;
import fragrantia.fragrantiaadminserver.domain.store.service.StoreService;
import fragrantia.fragrantiaadminserver.security.DefaultFragrantiaAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create")
    @ResponseBody
    public void create(
        @RequestBody StoreCreateDto.StoreCreateRequest req
    ) {
        storeService.create(req.getLatitude(), req.getLongitude(), req.getZip(), req.getAddress(), req.getName(), req.getDetail(), req.getTelephone(), req.getFile());
    }

    @GetMapping
    public String getStore(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        int offset = page * size;
        List<GetStoresDto> stores = storeService.getStoresWithPaging(offset, size);
        int totalCount = storeService.getTotalStoreCount();

        model.addAttribute("stores", stores);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", calculateTotalPages(totalCount, size));

        return "page/store";
    }

    private int calculateTotalPages(int totalCount, int size) {
        return (int) Math.ceil((double) totalCount / size);
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateStore(@RequestBody StoreUpdateDto.StoreUpdateRequest req) {
        storeService.updateStore(req.getId(), req.getLatitude(), req.getLongitude(), req.getZip(), req.getAddress(), req.getName(), req.getDetail(), req.getTelephone(), req.getFile());
    }

    @DeleteMapping
    @ResponseBody
    public void deleteStore(@RequestBody StoreDeleteDto.StoreDeleteRequest req) {
        req.getIds()
            .forEach(storeService::deleteStore);
    }
}
