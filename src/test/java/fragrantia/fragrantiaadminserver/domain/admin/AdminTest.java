package fragrantia.fragrantiaadminserver.domain.admin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class AdminTest {

    @Test
    void 어드민_생성_성공() {
        Admin admin = Admin.create("email", "password", "name", "branch");

        assertThat(admin.getEmail()).isEqualTo("email");
        assertThat(admin.getPassword()).isEqualTo("password");
        assertThat(admin.getName()).isEqualTo("name");
        assertThat(admin.getBranch()).isEqualTo("branch");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패_이메일이_없음(String email) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Admin.create(email, "password", "name", "branch")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패_비밀번호가_없음(String password) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Admin.create("email", password, "name", "branch")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패_이름이_없음(String name) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Admin.create("email", "password", name, "branch")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패_지점명이_없음(String branch) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Admin.create("email", "password", "name", branch)
        );
    }
}
