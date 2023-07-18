$(function () {
    $("#join-form").validate({
        rules: {
            email: {
                required: true,
                email: true,
                minlength: 5,
                maxlength: 20,
            },
            password: {
                required: true,
                minlength: 7,
                maxlength: 30,
            },
            'confirm-password': {
                required: true,
                equalTo: "#password"
            },
            name: {
                required: true,
                maxlength: 7,
            },
            branch: {
                required: true,
                maxlength: 7,
            },
        },
        messages: {
            email: {
                required: "아이디를 입력해주세요",
                email: "이메일 형식을 지켜서 입력해주세요",
                minlength: "5자 이상 입력해주세요",
                maxlength: "20자 이하로 입력해주세요",
            },
            password: {
                required: "비밀번호를 입력해주세요",
                minlength: "7자 이상 입력해주세요",
                maxlength: "30자 이하로 입력해주세요",
            },
            'confirm-password': {
                required: "비밀번호를 확인해주세요",
                equalTo: "비밀번호가 일치하지 않습니다",
            },
            name: {
                required: "이름을 입력해주세요",
                maxlength: "7자 이하로 입력해주세요",
            },
            branch: {
                required: "지점명을 입력해주세요",
                maxlength: "7자 이하로 입력해주세요",
            },
        },
        submitHandler: function () {

            checkEmailDuplication(function (cnt) {
                console.log(cnt);
                if (cnt === 1) {
                    alert("이메일을 중복되지 않게 다시 작성해주세요.");
                } else {
                    const json = {
                        email: $('#email').val(),
                        password: $('#password').val(),
                        name: $('#name').val(),
                        branch: $('#branch').val()
                    };

                    $.ajax({
                        url: "/admin",
                        type: "POST",
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success: function () {
                            alert("회원가입이 성공적으로 완료되었습니다.");
                            window.location.href = '/auth/login';
                        },
                        error: function () {
                            alert("simpleWithObject err");
                        }
                    });
                }
            });

        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().next());
        }
    });

    function checkEmailDuplication(callback) {
        var email = $('#email').val();
        $.ajax({
            url: '/admin/idCheck',
            type: "POST",
            data: { email: email },
            success: function (cnt) {
                if (!email) {
                    $('.id_ok').hide();
                    $('.id_already').hide();
                    callback(0);
                    return
                } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                    $('.id_ok').show();
                    $('.id_already').hide();
                    callback(cnt);
                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                    $('.id_already').show();
                    $('.id_ok').hide();
                    callback(cnt);
                }
            },
            error: function () {
                alert("에러가 발생했습니다.");
            }
        });
    }

    $('#email').on('input', function () {
        if ($('#email').valid()) {
            checkEmailDuplication(function () {
                // console.log(cnt);
            });
        } else {
            $('.id_already').hide();
            $('.id_ok').hide();
        }
    });
});
