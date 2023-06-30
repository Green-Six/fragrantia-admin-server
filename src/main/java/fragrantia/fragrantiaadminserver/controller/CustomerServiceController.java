package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.admin.MailDto;
import fragrantia.fragrantiaadminserver.controller.dto.customerservice.CustomerServiceSaveDto;
import fragrantia.fragrantiaadminserver.controller.dto.customerservice.CustomerServiceUpdateDto;
import fragrantia.fragrantiaadminserver.controller.dto.customerservice.GetCustomerServicesDto;
import fragrantia.fragrantiaadminserver.domain.admin.service.SendEmailService;
import fragrantia.fragrantiaadminserver.domain.customerservice.service.CustomerService2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customerService")
@RequiredArgsConstructor
public class CustomerServiceController {

    private final CustomerService2 customerService2;
    private final SendEmailService sendEmailService;

    @GetMapping
    public String getCustomerService(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        int offset = page * size;
        List<GetCustomerServicesDto> customerServices = customerService2.getCustomerServicesWithPaging(offset, size);
        int totalCount = customerService2.getTotalCustomerServiceCount();

        model.addAttribute("customerServices", customerServices);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", calculateTotalPages(totalCount, size));

        return "page/customerService";
    }

    private int calculateTotalPages(int totalCount, int size) {
        return (int) Math.ceil((double) totalCount / size);
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateCustomerService(@RequestBody CustomerServiceUpdateDto.CustomerServiceUpdateRequest req) {
        customerService2.updateCustomerService(req.getAnswer(), req.getId());
    }

    @PostMapping("/sendEmail")
    @ResponseBody
    public void sendAnswerByEmail(@RequestBody CustomerServiceSaveDto.CustomerServiceSaveRequest req){
        MailDto dto = sendEmailService.sendAnswer(req.getUserName(), req.getEmail(), req.getAnswer());
        sendEmailService.mailSend(dto);
    }
}
