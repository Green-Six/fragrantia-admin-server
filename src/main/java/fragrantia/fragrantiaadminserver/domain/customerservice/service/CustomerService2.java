package fragrantia.fragrantiaadminserver.domain.customerservice.service;

import fragrantia.fragrantiaadminserver.controller.dto.customerservice.GetCustomerServicesDto;

import java.util.List;

public interface CustomerService2 {
    List<GetCustomerServicesDto> getCustomerServicesWithPaging(int offset, int limit);

    int getTotalCustomerServiceCount();

    void updateCustomerService(String answer, Long id);
}
