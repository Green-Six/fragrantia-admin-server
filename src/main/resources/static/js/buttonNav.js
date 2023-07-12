$(document).ready(function () {

    if (
        document.title.includes("Notice") ||
        document.title.includes("Store")
    ) {
        $('.select_box').remove();
    }
    if (
        document.title.includes("Customer Service")
    ) {
        $('.button-nav').before('<div></div>');
        $('.button-nav').remove();
        // select 요소 가져오기
        const select = document.querySelector('#category');

        // 새로운 option 요소 생성하기
        const option1 = document.createElement('option');
        const option2 = document.createElement('option');
        option1.textContent = 'Store';
        option2.textContent = 'Etc.';

        // select 요소에 새로운 option 요소 추가하기
        select.appendChild(option1);
        select.appendChild(option2);
    }

    $('#category').change(function () {
        var selectCategory = $(this).val();
        var list = Array.from(document.getElementsByClassName('category'));

        list.forEach(element => { //display 초기화
            element.closest('tr').style.display = '';
        });

        if (selectCategory === 'All') { //전체 클릭시 리턴
            return;
        }

        var filterList = list.filter(it => {
            if ($(it).html() === 'Category') { //<th>Category</th>는 제외
                return false;
            }
            return $(it).html() !== selectCategory;
        });

        filterList.forEach(element => {
            if (element.textContent !== selectCategory) { // 선택된 카테고리가 아닌 경우에만 숨김 처리
                element.closest('tr').style.display = 'none';
            }
        });

    });

    if (
        document.title.includes("Notice") ||
        document.title.includes("Store") ||
        document.title.includes("Product")
    ) {
        const modal = document.getElementById("modal");
        const modalButton = document.getElementsByClassName("plus-button")[0];
        const modalClose = document.getElementById("modal-close");

        modalButton.addEventListener("click", () => {
            modal.style.display = "block";
        });

        modalClose.addEventListener("click", () => {
            modal.style.display = "none";
        });
    }
});
