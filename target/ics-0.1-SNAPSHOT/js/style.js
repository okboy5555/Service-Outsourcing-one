$(document).ready(function() {

    /* 
     ** 不同页面切换转场效果
     ** $.mobile.changePage ('/test.html', 'slide/pop/fade/slideup/slidedown/flip/none', false, false);
     */
    $('.list-group-item,.menu a').click(function() {
        $.mobile.changePage($(this).attr('href'), {
            transition: 'flip', //转场效果
            reverse: true //默认为false,设置为true时将导致一个反方向的转场
        });
    });


    // 轮播图js
    // 初始化轮播
    $(".start-slide").click(function() {
        $("#myCarousel").carousel('cycle');
    });
 
    // 循环轮播到下一个项目
    $(".next-slide").click(function() {
        $("#myCarousel").carousel('next');
    });
    

});
