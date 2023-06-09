// colspan size 조절 함수
        let reColspan = function () {
            if ($(window).width() <= 425) {
                // colspan 속성이 6인 td 요소를 찾아서
                $("tr.notice-content td[colspan='6']").each(function () {
                    // colspan 속성 값을 4으로 변경
                    $(this).attr("colspan", "4");
                });
            }
            // 데스크탑 버전인 경우
            else {
                // colspan 속성이 4인 td 요소를 찾아서
                $("tr.notice-content td[colspan='4']").each(function () {
                    // colspan 속성 값을 6으로 변경
                    $(this).attr("colspan", "6");
                });
            }
        }

        $(document).ready(function () {
            // 게시물 생성 모달 버튼 동작
            const modalSubmit = document.getElementById("modal-submit");

            modalSubmit.addEventListener("click", () => {
                const json = {
                    title: $('#title').val(),
                    content: $('#content').val(),
                };

                $.ajax({
                    url: "/notice/create",
                    type: "POST",
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function () {
                        alert("공지사항이 성공적으로 작성되었습니다.");
                        location.reload();
                    },
                    error: function () {
                        alert("simpleWithObject err");
                    }
                });
                modal.style.display = "none";
            });

            $('.notice-header').click(function () {
                var content = $(this).closest('tr').next('.notice-content');
                content.fadeToggle('slow'); // 클릭한 요소 열기/닫기
                $('.notice-content').not(content).fadeOut(); // 이미 열려있는 요소 닫기
            });
            $('input[type="checkbox"]').click(function (event) {
                event.stopPropagation(); // 클릭 이벤트 전파 방지
            });

            // 게시물 삭제 버튼 동작
            document.addEventListener("click", function (event) {
                if (event.target.classList.contains("minus-button")) {
                    var checkedBoxes = $('input[type="checkbox"]#chk:checked');
                    console.log(checkedBoxes);

                    var idList = [];

                    $(checkedBoxes).each(function () {
                        var id = $(this)
                            .closest('.notice-header')
                            .find('.no')
                            .text();
                        if (id) { // id가 존재하는지 확인
                            idList.push(id);
                        } else {
                            console.log('id가 없음.')
                        }
                    });

                    if (checkedBoxes.length === 0) {
                        alert("삭제할 게시글을 체크해주세요!")
                        return;
                    } else {
                        var json = {
                            ids: idList
                        };
                        console.log(json);

                        $.ajax({
                            url: "/notice",
                            type: "DELETE",
                            contentType: 'application/json',
                            data: JSON.stringify(json),
                            success: function () {
                                alert("게시물을 삭제했습니다.");
                                location.reload();
                            },
                            error: function () {
                                alert("simpleWithObject err");
                            }
                        });
                    };
                }
            });

            //시작 할때 colspan 화면 크기에 맞게 설정
            reColspan()

            // chkAll을 클릭했을 때, chkAll이 체크되어 있으면, name이 chk인 input 태그의 checked 속성을 true로 설정
            // >> 전체 체크 박스를 체크하면 모든 체크 박스 체크
            $("#chkAll").click(function () {
                if ($("#chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
                else $("input[name=chk]").prop("checked", false);
            });
            // name이 chk인 input 태그를 클릭하면, name이 chk인 요소의 개수를 total 변수에 저장.
            // name이 chk인 요소 중에 checked 되어 있는 요소의 개수를 checked 개수에 저장.
            $("input[name=chk]").click(function () {
                var total = $("input[name=chk]").length;
                var checked = $("input[name=chk]:checked").length;

                // total 값이 checked의 값와 같지 않으면 id가 chkAll인 객체의 checked 속성을 false로 바꿈.
                // 그게 아니면 checked 속성을 true로 바꿈.
                if (total != checked) $("#chkAll").prop("checked", false);
                else $("#chkAll").prop("checked", true);
            });

            // 수정버튼 동작
            $('.edit-btn').click(function () {
                var editBtn = $(this);
                var noticeContent = editBtn.closest('.notice-content');
                var noticeId = noticeContent.prev('.notice-header').find('.no').text();
                var noticeTitle = noticeContent.find('[name="notice-title"]');
                var noticeTextarea = noticeContent.find('[name="notice-textarea"]');

                noticeTitle.addClass('editable').removeAttr('readonly');
                noticeTextarea.addClass('editable').removeAttr('readonly');

                editBtn.hide();

                const saveBtn = document.createElement('button');
                saveBtn.type = 'button';
                saveBtn.className = 'btn btn-primary';
                saveBtn.innerText = 'Save';

                saveBtn.addEventListener('click', () => {
                    noticeTitle.removeClass('editable').prop('readonly', true);
                    noticeTextarea.removeClass('editable').prop('readonly', true);

                    var updatedTitle = noticeContent.find('.notice-title').val();
                    var updatedContent = noticeContent.find('.notice-textarea').val();

                    const json = {
                        id: noticeId,
                        title: updatedTitle,
                        content: updatedContent
                    };

                    console.log(json);

                    $.ajax({
                        url: "/notice/update",
                        type: "POST",
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success: function () {
                            alert("수정되었습니다.")
                            location.reload();
                        },
                        error: function () {
                            alert("simpleWithObject err");
                        }
                    });

                    saveBtn.remove();
                    editBtn.show();
                });

                editBtn.after(saveBtn);
            });
        });
        //화면 바꿀 때 colspan 화면 크기에 맞게 설정
        $(window).resize(
            reColspan
        );