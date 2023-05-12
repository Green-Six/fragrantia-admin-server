package fragrantia.fragrantiaadminserver.domain.admin;


import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;

import static fragrantia.fragrantiaadminserver.utils.Preconditions.require;

@Getter
public class Admin {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String branch;
    private final LocalDateTime createdAt = LocalDateTime.now();

    private Admin(String email, String password, String name, String branch) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.branch = branch;
    }

    //Admin 객체(인스턴스)를 생성하는 메서드.
    public static Admin create(String email, String password, String name, String branch) {
        //인스턴스가 생성되기 전에 각각의 파라미터들이 빈 칸일 수 없도록 설정해줌.
        require(Strings.isNotBlank(email));
        require(Strings.isNotBlank(password));
        require(Strings.isNotBlank(name));
        require(Strings.isNotBlank(branch));

        return new Admin(email, password, name, branch); // 인스턴스 생성
    }
}
