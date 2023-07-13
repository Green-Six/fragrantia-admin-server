FilePond.registerPlugin(FilePondPluginImagePreview);
FilePond.registerPlugin(FilePondPluginFileValidateType);
FilePond.registerPlugin(FilePondPluginFileValidateSize);

const inputComponents = document.querySelectorAll('input[type="file"]');

let ponds = [];
let images = ['', '', ''];

var historyImg = new FormData();
var natureImg = new FormData();
var humanImg = new FormData();

inputComponents.forEach(e => {
  ponds.push(FilePond.create(e, {
    storeAsFile: true,
    labelIdle: "클릭 혹은 끌어오기를 통해 이미지를 변경하십시오.",
    maxFileSize: '20MB',
    acceptedFileTypes: ['image/*'],
  }))
})

function reader(file, callback) {
  const fr = new FileReader();
  fr.onload = () => callback(null, fr.result);
  fr.onerror = (err) => callback(err);
  fr.readAsArrayBuffer(file);
}

function reader2(file, callback) {
  const fr = new FileReader();
  fr.onload = () => callback(null, fr.result);
  fr.onerror = (err) => callback(err);
  fr.readAsDataURL(file);
}

ponds.forEach((p, index) => {
  p.on('addfile', (error, file) => {
    if (error) {
      console.log("Error!", error);
    }
    reader2(file.file, (err, res) => { images[index] = res });

    reader(file.file, (err, res) => {
      const blob = new Blob([res], { type: file.file.type });
      const img = new File([blob], file.file.name, { type: file.file.type });
      if (index === 0) {
        historyImg.set("file", img);
      } else if (index === 1) {
        natureImg.set("file", img);
      } else if (index === 2) {
        humanImg.set("file", img);
      }
    });
  });
});

const textAreas = document.querySelectorAll('textarea');
const reset = document.getElementById('reset');

reset.addEventListener('click', function () {
  if (window.confirm("다시 작성하시겠습니까?")) {
    ponds.forEach(e => { e.removeFiles() })
    textAreas.forEach(e => e.value = '')
  }
})

const submit = document.getElementById('submit');
submit.addEventListener('click', function () {
  var isSomeTextAreaHasValue = false;
  textAreas.forEach(e => {
    if (e.value.length > 0)
      isSomeTextAreaHasValue = true;
  })

  var historyImgUrl;
  var natureImgUrl;
  var humanImgUrl;

  if (ponds.some((e) => e.getFiles().length > 0) || isSomeTextAreaHasValue) {
    if (window.confirm("제출하시겠습니까?")) {

      $.ajax({
        url: "/brandInfo/upload",
        type: "POST",
        data: historyImg,
        processData: false,
        contentType: false,
        async: false,
        success: function (response) {
          historyImgUrl = response;
          console.log("history 이미지 url: ", historyImgUrl);
        },
        error: function () {
          console.error();
          alert("history 이미지 등록 실패");
        }
      });

      $.ajax({
        url: "/brandInfo/upload",
        type: "POST",
        data: natureImg,
        processData: false,
        contentType: false,
        async: false,
        success: function (response) {
          natureImgUrl = response;
          console.log("nature 이미지 url: ", natureImgUrl);
        },
        error: function () {
          alert("nature 이미지 등록 실패");
        }
      });

      $.ajax({
        url: "/brandInfo/upload",
        type: "POST",
        data: humanImg,
        processData: false,
        contentType: false,
        async: false,
        success: function (response) {
          alert("모든 이미지가 성공적으로 등록되었습니다.");
          humanImgUrl = response;
          console.log("human 이미지 url: ", humanImgUrl);

          const json = {
            historyImgUrl: historyImgUrl,
            historyDetail: $('#namingTextarea').val(),
            natureImgUrl: natureImgUrl,
            natureDetail: $('#natureTextarea').val(),
            humanImgUrl: humanImgUrl,
            humanDetail: $('#humanTextarea').val()
          };

          console.log(json);

          $.ajax({
            url: "/brandInfo/update",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(json),
            async: false,
            success: function () {
              alert("수정되었습니다.")
              // location.reload();
            },
            error: function () {
              alert("simpleWithObject err");
            }
          });
        }
      });
    }

  }
  else {
    var msg = '변경사항이 없습니다.';
    alert(msg);
  }
})
let previeRef = null;
const preview = document.getElementById('preview')
preview.addEventListener('click', () => {

  if (previeRef != null) {
    if (!previeRef.closed) {
      previeRef.close();
    }
  }

  previeRef = openPreview()

})

function openPreview() {
  var inputTexts = []
  textAreas.forEach(e => {
    inputTexts.push(e.value.replaceAll('\n', `<br>`))
  })

  var popup = open("", "previewPage");
  var defaultTexts = ['default', 'default', 'default']
  popup.document.write(
    `<!DOCTYPE html>
  <html lang="ko">

  <head>
    <title>미리보기 - 클릭시 닫힙니다.</title>

    <!-- meta Setting -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 외부 스타일시트 -->
    <link rel="stylesheet" href="../../css/global.css">
    <link rel="stylesheet" href="../../css/page/brandInfo.css">

    <!-- 외부 스크립트(자바스크립트, jQuery) -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  </head>
  <body>
    <div id="overlay"></div>
    <div id="header"></div>
    <section class="brand_story naming" ${ponds[0].getFiles().length > 0 && `style="background-image:url(${images[0]})"`}>
      <div class="description">
        <h1>#1 History</h1>
        <p>
          ${inputTexts[0].length > 0 ? inputTexts[0] : defaultTexts[0]}
        </p>
      </div>
    </section>
    <section class="brand_story nature"${ponds[1].getFiles().length > 0 && `style="background-image:url(${images[1]})"`}>
      <div class="description">
        <h1>#2 Nature</h1>
        <p>
        ${inputTexts[1].length > 0 ? inputTexts[1] : defaultTexts[1]}
        </p>
      </div>
    </section>
    <section class="brand_story human"${ponds[2].getFiles().length > 0 && `style="background-image:url(${images[2]})"`}>
      <div class="description">
        <h1>#3 Human</h1>
        <p>
        ${inputTexts[2].length > 0 ? inputTexts[2] : defaultTexts[2]}
        </p>
      </div>
    </section>
    <div id="footer"></div>
    <script>
    var overlay= document.getElementById('overlay');
    overlay.addEventListener('click',()=>{
      window.close();
    });
    </script>
  </body>

  </html>`
  )
  return popup;
}