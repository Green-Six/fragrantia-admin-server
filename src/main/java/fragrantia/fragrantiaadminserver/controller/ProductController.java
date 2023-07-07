package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.product.GetProductsDto;
import fragrantia.fragrantiaadminserver.controller.dto.product.ProductCreateDto;
import fragrantia.fragrantiaadminserver.controller.dto.product.ProductDeleteDto;
import fragrantia.fragrantiaadminserver.controller.dto.product.ProductUpdateDto;
import fragrantia.fragrantiaadminserver.domain.product.service.ProductService;
import fragrantia.fragrantiaadminserver.s3.S3Uploader;
import fragrantia.fragrantiaadminserver.security.DefaultFragrantiaAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }

    @PostMapping("/create")
    @ResponseBody
    public void create(
        @AuthenticationPrincipal DefaultFragrantiaAdmin admin,
        @RequestBody ProductCreateDto.ProductCreateRequest req,
        @RequestParam(value = "file", required = false) MultipartFile multipartFile
    ) throws IOException {

        productService.create(admin.getId(), req.getName(), req.getPrice(), req.getCategory(), req.getDetail(), req.getImgUrl());
    }

    @GetMapping
    public String getProduct(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        int offset = page * size;
        List<GetProductsDto> products = productService.getProductsWithPaging(offset, size);
        int totalCount = productService.getTotalProductCount();

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", calculateTotalPages(totalCount, size));

        return "page/product";
    }

    private int calculateTotalPages(int totalCount, int size) {
        return (int) Math.ceil((double) totalCount / size);
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateProduct(@RequestBody ProductUpdateDto.ProductUpdateRequest req) {
        productService.updateProduct(req.getAdminId(), req.getName(), req.getPrice(), req.getCategory(), req.getDetail(), req.getImgUrl(), req.getId());
    }

    @DeleteMapping
    @ResponseBody
    public void deleteProduct(@RequestBody ProductDeleteDto.ProductDeleteRequest req) {
        req.getIds()
            .forEach(productService::deleteProduct);
    }
}
