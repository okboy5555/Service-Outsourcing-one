$(document).ready(function(){
    //鼠标单击
    if (Modernizr.touch) {
            $('#container').on('touchstart', function (e) {
                var left = e.originalEvent.touches[0].pageX;
                var top = e.originalEvent.touches[0].pageY;

                $(this).append('<div class="dot" style="top:' + top + 'px;left:' + left + 'px;"></div>')
                setTimeout(function () {
                    $('#container .dot:first-of-type').remove();
                }, 3000);
            });
            document.body.addEventListener('touchmove', function (e) {
                e.preventDefault();
            });
        } else {
            $('#container').on('mousedown', function (e) {
                var left = e.pageX;
                var top = e.pageY;

                $(this).append('<div class="dot" style="top:' + top + 'px;left:' + left + 'px;"></div>')
                setTimeout(function () {
                    $('#container .dot:first-of-type').remove();
                }, 3000);
            });
        } //@ sourceURL=pen.js

});
	