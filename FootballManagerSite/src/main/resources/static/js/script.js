
//scroll-header
$(window).scroll(function () {
  if ($(this).scrollTop() > 10) {
    $("header").addClass("scroll-desktop-menu")
  } else {
    $("header").removeClass("scroll-desktop-menu")
  }
});
//End scroll-header

//burger
const btnBurger = document.getElementById('hamburger');

btnBurger.addEventListener('click', function() {
  if (document.body.classList.contains("hamburger-active")) {
    closeBurger ();
  } else {
    openBurger ();
  }
})

function closeBurger () {
  document.body.classList.remove('hamburger-active');
}

function openBurger () {
  document.body.classList.add('hamburger-active');
}
//End burger

var elements = $('.modal-overlay, .modal');

$('.open-modal').click(function(){
  elements.addClass('active');
});

$('.close-modal').click(function(){
  elements.removeClass('active');
});


function handleFileSelect(evt) {
    let file = evt.target.files; // FileList object
    let f = file[0];
    // Only process image files.
    if (!f.type.match('image.*')) {
      alert("Выберете фото...");
    }
    let reader = new FileReader();
    // Closure to capture the file information.
    reader.onload = (function(theFile) {
      return function(e) {
            // Render thumbnail.
            let span = document.createElement('span');
            span.innerHTML = ['<img class="input-img" title="', escape(theFile.name), '" src="', e.target.result, '" />'].join('');
            document.getElementById('output').insertBefore(span, null);
          };
        })(f);
    // Read in the image file as a data URL.
    reader.readAsDataURL(f);
  }
  document.getElementById('file-input').addEventListener('change', handleFileSelect, false);

$('#file-input').on('change', () => {
  $('.file-input').remove()
})


function complete() {
  alert("Данные для регистрации отправлены вам на почту!");
};
function save() {
  alert("Данные успешно сохранены");
}
