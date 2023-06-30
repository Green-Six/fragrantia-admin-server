package fragrantia.fragrantiaadminserver.domain.customerservice;

import fragrantia.fragrantiaadminserver.controller.dto.customerservice.GetCustomerServicesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerServiceMapper {
    CustomerService getCustomerService(Long customerServiceId);

    List<GetCustomerServicesDto> getCustomerServicesWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int getTotalCustomerServiceCount();

    void updateCustomerService(CustomerService customerService);
}
