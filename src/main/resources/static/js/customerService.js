// colspan size 조절 함수
let reColspan = function () {
    if ($(window).width() <= 425) {
        // colspan 속성이 6인 td 요소를 찾아서
        $("tr.customerService-content td[colspan='5']").each(function () {
            // colspan 속성 값을 4으로 변경
            $(this).attr("colspan", "4");
        });
    }
    // 데스크탑 버전인 경우
    else {
        // colspan 속성이 4인 td 요소를 찾아서
        $("tr.customerService-content td[colspan='4']").each(function () {
            // colspan 속성 값을 6으로 변경
            $(this).attr("colspan", "5");
        });
    }
}

$(document).ready(function () {

    $('.customerService-header').click(function () {
        var content = $(this).closest('tr').next('.customerService-content');
        content.fadeToggle('slow'); // 클릭한 요소 열기/닫기
        $('.customerService-content').not(content).fadeOut(); // 이미 열려있는 요소 닫기
    });
    $('input[type="checkbox"]').click(function (event) {
        event.stopPropagation(); // 클릭 이벤트 전파 방지
    });

    //시작 할때 colspan 화면 크기에 맞게 설정
    reColspan()

    // 수정버튼 동작
    // const saveBtns = document.querySelectorAll('.save-btn');

    // saveBtns.forEach((saveBtn) => {
    $('.save-btn').click(function () {
        // 수정 전 코드 >> const customerServiceId = document.querySelector('.customerService-header .no').textContent;
        var customerServiceId = $(this).closest('.customerService-content').prev('.customerService-header').find('.no').text();
        var answer = $(this).closest('.customerService-content').find('#ask_answer').val();

        const json = {
            id: customerServiceId,
            answer: answer,
        };

        console.log(json);

        $.ajax({
            url: "/customerService/update",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function () {
                alert("답변이 저장되었습니다.")
                location.reload;
            },
            error: function () {
                alert("답변 저장에 실패했습니다.");
            }
        });
    });

    $('.send-btn').click(function () {
        var sendBtn = $(this);
        var customerServiceContent = sendBtn.closest('.customerService-content');
        var customerServiceName = customerServiceContent.find('#ask_userName').val();
        var customerServiceEmail = customerServiceContent.find('#ask_email').val();
        var customerServiceAnswer = customerServiceContent.find('#ask_answer').val();

        const json = {
            userName: customerServiceName, 
            email: customerServiceEmail, 
            answer: customerServiceAnswer
        };

        console.log(json);

        $.ajax({
            url: "/customerService/sendEmail",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function () {
                swal("발송 완료!", "저장된 답변이 메일로 전송되었습니다.", "success");
                location.reload;
            },
            error: function () {
                alert("메일 발송에 실패했습니다.");
            }
        });
    });
});
//화면 바꿀 때 colspan 화면 크기에 맞게 설정
$(window).resize(
    reColspan
);