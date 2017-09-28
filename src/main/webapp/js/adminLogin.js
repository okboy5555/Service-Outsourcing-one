$(function(){
    $('#btn_adminLogin').bind('click',adminLogin);
});

function adminLogin() {
    var url = '/handle/login';
    var strLogin = $('input[name="username"]').val();
    var password = $('input[name="password"]').val();
    if (strLogin == "" || password == "") {
        alert('用户名或密码不能为空！');
        return;
    }
    var data = {
        "strLogin":strLogin,
        "password":password,
        "type":"3"
    };
    $.post(url,data,function (res) {
        if(res.status == 0){
            alert(res.message);
            return;
        }
        window.localStorage.setItem('ics_token', res.token);
        location.href = '/admin';
    },"JSON");
}