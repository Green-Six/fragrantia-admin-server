// colspan size 조절 함수
        let reColspan = function () {
            if ($(window).width() <= 425) {
                // colspan 속성이 6인 td 요소를 찾아서
                $("tr.store-content td[colspan='6']").each(function () {
                    // colspan 속성 값을 4으로 변경
                    $(this).attr("colspan", "4");
                });
            }
            // 데스크탑 버전인 경우
            else {
                // colspan 속성이 4인 td 요소를 찾아서
                $("tr.store-content td[colspan='4']").each(function () {
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
                    latitude: $('#create_latitude').val(),
                    longitude: $('#create_longitude').val(),
                    zip: $('#create_zip').val(),
                    address: $('#create_addr').val(),
                    name: $('#create_name').val(),
                    detail: $('#create_detail').val(),
                    telephone: $('#create_tel').val(),
                    file: $('#photo').val()
                };

                console.log(json);

                $.ajax({
                    url: "/store/create",
                    type: "POST",
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function () {
                        alert("매장 정보가 성공적으로 등록되었습니다.");
                        location.reload;
                    },
                    error: function () {
                        alert("simpleWithObject err");
                    }
                });
                modal.style.display = "none";
            });

            $('.store-header').click(function () {
                var content = $(this).closest('tr').next('.store-content');
                content.fadeToggle('slow'); // 클릭한 요소 열기/닫기
                $('.store-content').not(content).fadeOut(); // 이미 열려있는 요소 닫기
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
                            .closest('.store-header')
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
                            url: "/store",
                            type: "DELETE",
                            contentType: 'application/json',
                            data: JSON.stringify(json),
                            success: function () {
                                alert("삭제 성공");
                                location.reload;
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

            // chkAll을 클릭했을 때
            $("#chkAll").click(function () {
                if ($("#chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
                else $("input[name=chk]").prop("checked", false);
            });
            // name이 chk인 input 태그를 클릭
            $("input[name=chk]").click(function () {
                var total = $("input[name=chk]").length;
                var checked = $("input[name=chk]:checked").length;

                // total 값이 checked의 값와 같지 않으면 id가 chkAll인 객체의 checked 속성을 false로 바꿈.
                // 그게 아니면 checked 속성을 true로 바꿈.
                if (total != checked) $("#chkAll").prop("checked", false);
                else $("#chkAll").prop("checked", true);
            });

            // 수정버튼 동작
            const storeTextElements = document.querySelectorAll('.store_text');

            $('.edit-btn').click(function () {

                var editBtn = $(this);
                var storeContent = editBtn.closest('.store-content');
                var storeId = storeContent.prev('.store-header').find('.no').text();
                var latitude = storeContent.find('#store_latitude').val();
                var longitude = storeContent.find('#store_longitude').val();
                var zip = storeContent.find('#store_zip').val();
                var address = storeContent.find('#store_addr').val();
                var name = storeContent.find('#store_name').val();
                var detail = storeContent.find('#store_detail').val();
                var telephone = storeContent.find('#store_tel').val();
                var file = storeContent.find('#store_file_name').val();
                console.log("id는 " + storeId + "입니다.")

                storeTextElements.forEach((storeTextElement) => {
                    storeTextElement.classList.add('editable');
                    storeTextElement.removeAttribute('readonly');
                });

                editBtn.hide();

                const saveBtn = document.createElement('button');
                saveBtn.type = 'button';
                saveBtn.className = 'btn btn-primary';
                saveBtn.innerText = 'Save';

                saveBtn.addEventListener('click', () => {
                    storeTextElements.forEach((storeTextElement) => {
                        storeTextElement.classList.remove('editable');
                        storeTextElement.setAttribute('readonly', '');
                    });

                    var updatedLatitude = storeContent.find('#store_latitude').val();
                    var updatedLongitude = storeContent.find('#store_longitude').val();
                    var updatedZip = storeContent.find('#store_zip').val();
                    var updatedAddress = storeContent.find('#store_addr').val();
                    var updatedName = storeContent.find('#store_name').val();
                    var updatedDetail = storeContent.find('#store_detail').val();
                    var updatedTelephone = storeContent.find('#store_tel').val();
                    var updatedFile = storeContent.find('#store_file_name').val();

                    const json = {
                        id: storeId,
                        latitude: updatedLatitude,
                        longitude: updatedLongitude,
                        zip: updatedZip,
                        address: updatedAddress,
                        name: updatedName,
                        detail: updatedDetail,
                        telephone: updatedTelephone,
                        file: updatedFile
                    };

                    console.log(json);

                    $.ajax({
                        url: "/store/update",
                        type: "POST",
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success: function () {
                            alert("수정되었습니다.")
                            location.reload;
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