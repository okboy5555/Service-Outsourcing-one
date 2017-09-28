$(document).ready(function() {
    // 会话功能开始

    var connectType = 0; //；连接种类，0为连接机器人，1为连接客服

    var Chat = {};
    var closing = 0;
    Chat.socket = null;
    Chat.token = window.localStorage.getItem('ics_token');
    Chat.URL = "";
    Chat.connect = (function(host) {
        closing= 0;
        if ('WebSocket' in window) {
            Chat.socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            Chat.socket = new MozWebSocket(host);
        } else {
            alert('当前浏览器不支持WebSocket，请更换浏览器。');
            return;
        }

        Chat.socket.onopen = function () {
            console.log('Info: WebSocket connection opened.');
        };

        Chat.socket.onclose = function () {
            connectType = 0;
            console.log('Info: WebSocket closed.');
            closing = 1;
        };

        Chat.socket.onmessage = function (message) {
            $.ajax({
                url: "/handle/admin/checkLogin",
                data: {},
                type: "POST",
                beforeSend: function(xhr){xhr.setRequestHeader('token', token);},//这里设置header
                success: function(res) {
                    if(res.status == "1"){
                        token = res.token;
                        window.localStorage.setItem('ics_token', res.token);
                    }else{
                        //
                    }
                },
                dataType:"JSON"
            });
            setTimeout(function () {
                add_message('System', './images/kefu.png',message.data );
            },'1000');
        };
    });
    Chat.initialize = function () {
        if (window.location.protocol == 'http:') {
            Chat.connect('ws://' + window.location.host + Chat.URL);
        } else {
            Chat.connect('wss://' + window.location.host + Chat.URL);
        }
    };

    Chat.getServiceURL = function () {
        $.ajax({
            url: "/handle/getServiceURL",
            data: {},
            type: "POST",
            beforeSend: function(xhr){xhr.setRequestHeader('token', Chat.token);},//这里设置header
            success: function(res) {
                if(res.status == 1){
                    Chat.URL = res.message;
                    token = res.token;
                    window.localStorage.setItem('ics_token', res.token);
                    add_message('System', './images/kefu.png','正在排队，请等待客服回复...');
                    Chat.initialize();
                } else {
                    console.log('get service url failed.')
                }
            },
            dataType:"JSON",
            error:function () {
                console.log('发送请求失败。');
            }
        });
    };

    Chat.sendMessage = (function() {
        var message = $('#msg-box').val().trim();
        add_message('You', './images/yonghu.png', message, true);
        if (message != '') {
            Chat.socket.send(message);
        }
    });
    // 会话功能结束

    //将连接转到客服
    function changeToCustomer(){
        if(connectType === 1){
            return;
        }
        $.ajax({
            url: "/handle/admin/checkLogin",
            data: {},
            type: "POST",
            beforeSend: function(xhr){xhr.setRequestHeader('token', Chat.token);},//这里设置header
            success: function(res) {
                if(res.status == "1"){
                    token = res.token;
                    window.localStorage.setItem('ics_token', res.token);
                    Chat.getServiceURL();
                    console.log('Info:Connected to Customer Service.');
                    connectType = 1;
                }else{
                    window.location.href = '/login';
                }
            },
            dataType:"JSON"
        });
    }

    var searchTime = 0;
    $('#feedback').click(function () {
        window.location.href = "Feedback.html";
    });
    var msg_template = '<p><span class="msg-block"><strong></strong><span class="time"></span><span class="msg"></span></span></p>';


    var isFirstQuery = true;
    $('.chat-message button').click(function() {
        if(connectType === 0){
            isFirstQuery = true;
            queryFromRobot();
        }
        else {
            Chat.sendMessage();
        }
    });

    $('.chat-message input').keypress(function(e){
        if(e.which == 13) {
            if(connectType === 0){
                isFirstQuery = true;
                queryFromRobot();
            }
            else {
                Chat.sendMessage();
            }
        }
    });

    $('#chat-messages-inner').on('click','.secondQuery',function () {
        var text = $(this).html();
        console.log(text);
        isFirstQuery = false;
        add_message('You', './images/yonghu.png',text);
        queryFromRobot(text);
    });
    $('#chat-messages-inner').on('click','.changeToCustomer',function () {
        changeToCustomer();
    });
    function queryFromRobot(str){
        if(isFirstQuery){
            str = $("#msg-box").val().trim();
            if(str.length == 0 ){
                return;
            }
            add_message('You', './images/yonghu.png', str, true);
        }
        $.ajax({
            url:"/handle/query",
            type:"POST",
            dataType:"Json",
            data:{"str":str},
            success:function (msg) {
                if(msg.status == 1){
                    if(msg.message=="[]"){ //cannot find answer
                        add_message('System', './images/kefu.png','对不起，我们暂时没有找到有关"'+str+'"的答案。');
                        searchTime++;
                        if(searchTime >= 3){//cannot find answer for 3 times or over
                            add_message('System', './images/kefu.png','没有找到合适的答案？您可以试试 <a href="#" class="changeToCustomer">人工客服</a>');
                        }
                        return;
                    }
                    var json = eval('('+msg.message+')'); //解析返回的查询结果
                    if(isFirstQuery){
                        var content = '您要查询的是否是'+'<br/>';
                        $.each(json,function (index) {
                            // strTempQuery = json[index].subject;
                            content += '<a href="#" class="secondQuery">'+json[index].subject+'</a><br/>';
                            console.log(content);
                        });
                        setTimeout(function () {
                            add_message('System', './images/kefu.png',content);
                        },1000);
                    }else{
                        setTimeout(function () {
                            add_message('System', './images/kefu.png',json[0].content);
                        },1000);
                    }
                }
                else
                {
                    console.log("获取结果失败");
                }
            },
            error:function () {
                console.log("请求发送失败");
            }
        });
    }


    var i = 0;

    function add_message(name, img, msg, clear) {
        i = i + 1;
        var inner = $('#chat-messages-inner');
        var time = new Date();
        var hours = time.getHours();
        var minutes = time.getMinutes();
        if (hours < 10) hours = '0' + hours;
        if (minutes < 10) minutes = '0' + minutes;
        var id = 'msg-' + i;
        var idname = name.replace(' ', '-').toLowerCase();
        inner.append('<p id="' + id + '" class="user-' + idname + '"><img src="' + img + '" alt="" />' + '<span class="msg-block"><strong>' + name + '</strong> <span class="time">- ' + hours + ':' + minutes + '</span>' + '<span class="msg">' + msg + '</span></span></p>');
        $('#' + id).hide().fadeIn(800);
        if (clear) {
            $('.chat-message input').val('').focus();
        }
        $('#chat-messages').animate({ scrollTop: inner.height() }, 1000);
    }

    function remove_user(userid, name) {
        i = i + 1;
        $('.contact-list li#user-' + userid).addClass('offline').delay(1000).slideUp(800, function() {
            $(this).remove();
        });
        var inner = $('#chat-messages-inner');
        var id = 'msg-' + i;
        inner.append('<p class="offline" id="' + id + '"><span>User ' + name + ' left the chat</span></p>');
        $('#' + id).hide().fadeIn(800);
    }



});
