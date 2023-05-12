package fragrantia.fragrantiaadminserver.domain.admin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    int create(Admin admin);

    Admin getAdmin(Long adminId);

    List<Admin> getAdmins();

    Admin getByEmail(String email);
}
