$(document).ready(function(){


	//客服回话开始
    var Chat = {};

    Chat.socket = null;
    Chat.URL = "";
    Chat.token = window.localStorage.getItem('ics_token');
    var closing = 0;
    Chat.connect = (function(host) {
        closing = 0;
        if ('WebSocket' in window) {
            Chat.socket=(new WebSocket(host));
        } else if ('MozWebSocket' in window) {
            Chat.socket=(new MozWebSocket(host));
        } else {
            alert('Error: WebSocket is not supported by this browser.');
            return;
        }

        Chat.socket.onopen = function () {
            console.log('Info: WebSocket connection opened.');
        };

        Chat.socket.onclose = function () {
            closing = 1;
            document.getElementById('msg-box').onkeydown = null;
            Console.log('Info: WebSocket closed.');
        };

        Chat.socket.onmessage = function (message) {
            setTimeout(function () {
                add_message('Neytiri', './images/yonghu.png',message.data );
            },'1000');
        };
    });

    Chat.sendMessage = (function() {
        var message = $('#msg-box').val().trim();
        add_message('You', './images/kefu.png', message, true);
        if (message != '') {
            Chat.socket.send(message);
        }
    });

	//客服回话结束
	var msg_template = '<p><span class="msg-block"><strong></strong><span class="time"></span><span class="msg"></span></span></p>';

    $("#add").click(function () {
        add_message('System', './images/kefu.png', '正在接入用户...');

        $.ajax({
            url: "/handle/admin/connectCustomer",
            data: {},
            type: "POST",
            beforeSend: function(xhr){xhr.setRequestHeader('token',Chat.token);},//这里设置header
            success: function(res) {
                Chat.URL = res;
                add_message('System', './images/kefu.png', '接入成功！');
                add_user('yonghu','yonghu');
                if (window.location.protocol == 'http:') {
                    Chat.connect('ws://' + window.location.host + '/ws/cs'+Chat.URL);
                } else {
                    Chat.connect('wss://' + window.location.host + '/ws/cs'+Chat.URL);
                }


            }

        });
    })

    $('.chat-message button').click(function(){
        if($("#msg-box").val() != ''){
            Chat.sendMessage();
        }
    });

    $('.chat-message input').keypress(function(e){
        if(e.which == 13) {
            if($(this).val() != ''){
                Chat.sendMessage();
            }
        }
    });



   	var i = 0;
	function add_message(name,img,msg,clear) {
		i = i + 1;
		var  inner = $('#chat-messages-inner');
		var time = new Date();
		var hours = time.getHours();
		var minutes = time.getMinutes();
		if(hours < 10) hours = '0' + hours;
		if(minutes < 10) minutes = '0' + minutes;
		var id = 'msg-'+i;
        var idname = name.replace(' ','-').toLowerCase();
		inner.append('<p id="'+id+'" class="user-'+idname+'"><img src="'+img+'" alt="" />'
										+'<span class="msg-block"><strong>'+name+'</strong> <span class="time">- '+hours+':'+minutes+'</span>'
										+'<span class="msg">'+msg+'</span></span></p>');
		$('#'+id).hide().fadeIn(800);
		if(clear) {
			$('.chat-message input').val('').focus();
		}
		$('#chat-messages').animate({ scrollTop: inner.height() },1000);
	}
	//删除用户
    function remove_user(userid,name) {
        i = i + 1;
        $('.contact-list li#user-'+userid).addClass('offline').delay(1000).slideUp(800,function(){
            $(this).remove();
        });
        var  inner = $('#chat-messages-inner');
        var id = 'msg-'+i;
        inner.append('<p class="offline" id="'+id+'"><span>User '+name+' left the chat</span></p>');
        $('#'+id).hide().fadeIn(800);
    }
    //增加用户
    function add_user(userid,name) {
        i=i+1;
        $('.contact-list').append('<li id="user-'+userid+'" class="online new">\n' + '<a href="#"><span>'+userid+'</span></a><span class="msg-count badge badge-info">1</span>\n' + '</li>');
        var  inner = $('#chat-messages-inner');
        var id = 'msg-'+i;
        inner.append('<p class="offline" id="'+id+'"><span>User '+name+' add the chat</span></p>');
        $('#'+id).hide().fadeIn(800);
    }



    //判断用户是否存在
    if(closing == 1){
        remove_user('yonghu','yonghu');
    }
    //结束
});
