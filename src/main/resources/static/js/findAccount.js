$(function () {
    $("#find-account-form").validate({
        rules: {
            name: {
                required: true,
                minlength: 1,
                maxlength: 7,
            },
            email: {
                required: true,
                email: true
            },
            branch: {
                required: true,
                minlength: 1,
                maxlength: 7,
            },
        },
        messages: {
            name: {
                required: "이름을 입력해주세요",
                minlength: "최소 1자 이상 입력해주세요",
                maxlength: "최대 7자 이하로 입력해주세요",
            },
            email: {
                required: "이메일을 입력해주세요",
                email: "올바른 이메일 형식을 입력해주세요"
            },
            branch: {
                required: "매장 이름을 입력해주세요",
                minlength: "최소 1자 이상 입력해주세요",
                maxlength: "최대 7자 이하로 입력해주세요",
            },
        },
        submitHandler: function (form) {
            var name = $("#name").val();
            var email = $("#email").val();
            var branch = $("#branch").val();

            $.ajax({
                type: "GET",
                url: "/admin/findPw",
                data: {
                    "name": name,
                    "email": email,
                    "branch": branch
                },
                success: function (res) {
                    console.log(res);
                    if (res['check']) {
                        swal("발송 완료!", "입력하신 이메일로 임시비밀번호가 발송되었습니다.", "success").then((OK) => {
                            if (OK) {
                                $.ajax({
                                    type: "POST",
                                    url: "/admin/findPw/sendEmail",
                                    data: {
                                        "name": name,
                                        "email": email,
                                        "branch": branch
                                    }
                                })
                                window.location = "/auth/login";
                            }
                        });
                        $('#checkMsg').html('<p style="color:darkblue"></p>');
                    } else {
                        $('#checkMsg').html('<p style="color:red">일치하는 정보가 없습니다.</p>');
                    }
                }
            })
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().next());
        }
    });
});
