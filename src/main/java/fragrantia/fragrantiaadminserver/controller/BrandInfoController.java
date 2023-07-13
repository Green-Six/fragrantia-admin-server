package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.brandinfo.BrandInfoUpdateDto;
import fragrantia.fragrantiaadminserver.domain.brandinfo.BrandInfo;
import fragrantia.fragrantiaadminserver.domain.brandinfo.service.BrandInfoService;
import fragrantia.fragrantiaadminserver.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/brandInfo")
@RequiredArgsConstructor
public class BrandInfoController {

    private final BrandInfoService brandInfoService;
    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateBrandInfo(@RequestBody BrandInfoUpdateDto.BrandInfoUpdateRequest req) {
        brandInfoService.updateBrandInfo(req.getHistoryImgUrl(), req.getHistoryDetail(), req.getNatureImgUrl(), req.getNatureDetail(), req.getHumanImgUrl(), req.getHumanDetail());
    }

    @GetMapping
    public String getBrandInfo(Model model) {
        BrandInfo brandInfo = brandInfoService.getBrandInfo();

        model.addAttribute("brandInfo", brandInfo);

        return "page/brandInfo";
    }
}
