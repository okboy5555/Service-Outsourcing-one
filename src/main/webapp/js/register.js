$("#login_flag").click(function () {
    $('.login').addClass('test');
    setTimeout(function () {
        $('.login').addClass('testtwo');
    }, 300);
    setTimeout(function () {
        $('.authent').show().animate({ right: -320 }, {
            easing: 'easeOutQuint',
            duration: 600,
            queue: false
        });
        $('.authent').animate({ opacity: 1 }, {
            duration: 200,
            queue: false
        }).addClass('visible');
    }, 500);
    setTimeout(function () {
        $('.authent').show().animate({ right: 90 }, {
            easing: 'easeOutQuint',
            duration: 600,
            queue: false
        });
        $('.authent').animate({ opacity: 0 }, {
            duration: 200,
            queue: false
        }).addClass('visible');
        $('.login').removeClass('testtwo');
    }, 2500);
    setTimeout(function () {
        $('.login').removeClass('test');
        $('.login div').fadeOut(123);
    }, 2800);
});

$('input[type="text"],input[type="password"]').focus(function () {
    $(this).prev().animate({ 'opacity': '1' }, 200);
});
$('input[type="text"],input[type="password"]').blur(function () {
    $(this).prev().animate({ 'opacity': '.5' }, 200);
});
$('input[type="text"],input[type="password"]').keyup(function () {
    if (!$(this).val() == '') {
        $(this).next().animate({
            'opacity': '1',
            'right': '30'
        }, 200);
    } else {
        $(this).next().animate({
            'opacity': '0',
            'right': '20'
        }, 200);
    }
});
var open = 0;
$('.tab').click(function () {
    $(this).fadeOut(200, function () {
        $(this).parent().animate({ 'left': '0' });
    });
});

//登陆后发送ajax验证账户密码

$("#login_flag").click(function(){
    var username = $("#username").val();
    var password = $("#password").val();
    var phone = $('#phone').val();
    var mail = $('#mail').val();
    if(username.length == 0)
    {
        alert("请输入你的用户名");
        location.reload();
    }else if(password.length == 0)
    {
        alert("请输入你的密码");
        location.reload();
    }
    else if(phone.length == 0)
    {
        alert("请输入你的电话号码");
        location.reload();
    }
    else if(mail.length == 0){
        alert("请输入你的邮箱");
        location.reload();
    }
    else
    {
        $.ajax({
            type: "post",
            url: "/handle/register",
            dataType:"json",
            data: "username="+ username+"&password="+password+"&phone="+phone+"&mail="+mail,
            error: function() {
                /*维护*/
            },
            success: function(msg) {
                var json = msg;
                if(json.status == 1){
                    console.log(json);
                    console.log(json.message);
                    setTimeout(function() {
                            $('.login').removeClass('test');
                            $('.login div').fadeOut(123);
                            $('.success').fadeIn();
                            setTimeout(function () {
                                    window.location.href = "index.html";
                                },
                                2000);
                        },
                        3000);
                }
                else
                {
                    setTimeout(function() {
                            $('.login').removeClass('test');
                            $('.login div').fadeOut(123);
                            $('.fail').fadeIn();
                            setTimeout(function () {
                                    location.reload();
                                },
                                2000);
                        },
                        3000);

                    console.log("请求发送失败");

                }
            }
        });
    }

})
