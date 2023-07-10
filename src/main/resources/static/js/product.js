// colspan size 조절 함수
let reColspan = function () {
    if ($(window).width() <= 425) {
        // colspan 속성이 7인 td 요소를 찾아서
        $("tr.product-content td[colspan='7']").each(function () {
            // colspan 속성 값을 4으로 변경
            $(this).attr("colspan", "4");
        });
    }
    // 데스크탑 버전인 경우
    else {
        // colspan 속성이 4인 td 요소를 찾아서
        $("tr.product-content td[colspan='4']").each(function () {
            // colspan 속성 값을 7으로 변경
            $(this).attr("colspan", "7");
        });
    }
}

$(document).ready(function () {
    // 게시물 생성 모달 버튼 동작
    const modalSubmit = document.getElementById("modal-submit");

    modalSubmit.addEventListener("click", () => {
        var formData = new FormData();
        var inputFile = $("input[id=create_file]");
        var file = inputFile[0].files[0];
        var imgUrl;

        formData.append("file", file);

        $.ajax({
            url: "/product/upload",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert("이미지가 성공적으로 등록되었습니다.");
                imgUrl = response;
                console.log("이미지 URL: ", imgUrl);

                const json = {
                    name: $('#create_name').val(),
                    price: $('#create_price').val(),
                    category: $('#create_category').val(),
                    detail: $('#create_detail').val(),
                    imgUrl: imgUrl
                };

                $.ajax({
                    url: "/product/create",
                    type: "POST",
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function () {
                        alert("상품이 성공적으로 등록되었습니다.");
                        // location.reload();
                    },
                    error: function () {
                        alert("상품 등록에 실패했습니다.");
                    }
                });
            },
            error: function () {
                alert("simpleWithObject err");
            }
        });

        modal.style.display = "none";
    });

    $('.product-header').click(function () {
        var content = $(this).closest('tr').next('.product-content');
        content.fadeToggle('slow'); // 클릭한 요소 열기/닫기
        $('.product-content').not(content).fadeOut(); // 이미 열려있는 요소 닫기
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
                    .closest('.product-header')
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
                    url: "/product",
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
    const productTextElements = document.querySelectorAll('.product_text');

    $('.edit-btn').click(function () {
        var editBtn = $(this);
        var productContent = editBtn.closest('.product-content');
        var productId = productContent.prev('.product-header').find('.no').text();

        productTextElements.forEach((productTextElement) => {
            productTextElement.classList.add('editable');
            productTextElement.removeAttribute('readonly');
        });

        editBtn.hide();

        const saveBtn = document.createElement('button');
        saveBtn.type = 'button';
        saveBtn.className = 'btn btn-primary';
        saveBtn.innerText = 'Save';

        saveBtn.addEventListener('click', () => {
            productTextElements.forEach((productTextElement) => {
                productTextElement.classList.remove('editable');
                productTextElement.setAttribute('readonly', '');
            });

            var updatedProductName = productContent.find('#item_name').val();
            var updatedProductPrice = productContent.find('#item_price').val();
            var updatedProductCategory = productContent.find('#item_category').val();
            var updatedProductDetail = productContent.find('#item_detail').val();
            var updatedProductFile = productContent.find('#item_file').val();

            const json = {
                id: productId,
                name: updatedProductName,
                price: updatedProductPrice,
                category: updatedProductCategory,
                detail: updatedProductDetail,
                file: updatedProductFile
            };

            console.log(json);

            $.ajax({
                url: "/product/update",
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