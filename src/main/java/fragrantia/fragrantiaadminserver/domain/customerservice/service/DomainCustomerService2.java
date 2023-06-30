package fragrantia.fragrantiaadminserver.domain.customerservice.service;

import fragrantia.fragrantiaadminserver.controller.dto.customerservice.GetCustomerServicesDto;
import fragrantia.fragrantiaadminserver.controller.dto.notice.GetNoticesDto;
import fragrantia.fragrantiaadminserver.domain.customerservice.CustomerService;
import fragrantia.fragrantiaadminserver.domain.customerservice.CustomerServiceMapper;
import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import fragrantia.fragrantiaadminserver.domain.notice.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainCustomerService2 implements CustomerService2{
    private final CustomerServiceMapper customerServiceMapper;

    @Override
    public List<GetCustomerServicesDto> getCustomerServicesWithPaging(int offset, int limit) {
        return customerServiceMapper.getCustomerServicesWithPaging(offset, limit);
    }

    @Override
    public int getTotalCustomerServiceCount() {
        return customerServiceMapper.getTotalCustomerServiceCount();
    }

    @Override
    public void updateCustomerService(String answer, Long id) {
        CustomerService customerService = customerServiceMapper.getCustomerService(id);

        customerService.update(answer);

        customerServiceMapper.updateCustomerService(customerService);
    }

}
