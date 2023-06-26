package fragrantia.fragrantiaadminserver.domain.admin.service;

import fragrantia.fragrantiaadminserver.domain.admin.Admin;

public interface AdminService {
    Admin create(String email, String password, String name, String branch);

    int isEmailDuplicate(String email);
}
