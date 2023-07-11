function loadModule(element) {
    let targetElement = '#' + element;

    let css = '../../css/module/' + element + '.css';
    let html = '../../html/module/' + element + '.html';

    $.get(css, function (data) {
        $('head').append('<style class="' + element + '" >' + data + '</style>');
        $(targetElement).load(html);
    });
}

$(function () {
    if (
        document.title.includes("Notice") ||
        document.title.includes("Store") ||
        document.title.includes("Product")||
        document.title.includes("Customer Service")
    ) {
        loadModule('header')
    }
})
