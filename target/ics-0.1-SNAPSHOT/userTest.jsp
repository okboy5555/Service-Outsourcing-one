<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User Chat Page</title>
</head>
<body>
<div>
    <p id="txtToken">Token</p>
    <p id="txtURL">URL</p>
    <input type="text" name="strLogin" placeholder="Username/Mail/Phone">
    <input type="password" name="password" placeholder="Password">
    <input type="button" onclick="Chat.login();" value="Log in">
    <input type="button" onclick="Chat.getServiceURL();" value="Get Service URL">
    <input type="button" onclick="Chat.initialize();" value="Initialize Connection">
</div>
<div>
    <p>
        <input type="text" placeholder="请输入内容" id="chat" />
    </p>
    <div id="console-container">
        <div id="console"/>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript">

    var Chat = {};

    Chat.socket = null;
    Chat.token = "";
    Chat.URL = "";

    Chat.connect = (function(host) {
        if ('WebSocket' in window) {
            Chat.socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            Chat.socket = new MozWebSocket(host);
        } else {
            Console.log('Error: WebSocket is not supported by this browser.');
            return;
        }

        Chat.socket.onopen = function () {
            Console.log('Info: WebSocket connection opened.');
            document.getElementById('chat').onkeydown = function(event) {
                if (event.keyCode == 13) {
                    Chat.sendMessage();
                }
            };
        };

        Chat.socket.onclose = function () {
            document.getElementById('chat').onkeydown = null;
            Console.log('Info: WebSocket closed.');
        };

        Chat.socket.onmessage = function (message) {
            Console.log(message.data);
        };
    });

    Chat.initialize = function() {
        if (window.location.protocol == 'http:') {
            Chat.connect('ws://' + window.location.host + Chat.URL);
        } else {
            Chat.connect('wss://' + window.location.host + Chat.URL);
        }
    };

    Chat.sendMessage = (function() {
        var message = document.getElementById('chat').value;
        if (message != '') {
            Chat.socket.send(message);
            document.getElementById('chat').value = '';
        }
    });

    var Console = {};

    Console.log = (function(message) {
        var console = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.innerHTML = message;
        console.appendChild(p);
        while (console.childNodes.length > 25) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    });

    Chat.login = function () {
        var url = '/handle/login';
        var data = {
            "strLogin":"test",
            "password":"test",
            "type":1
        };
        $.post(url,data,function (res) {
            if(res.status == "1"){
                Chat.token = res.token;
                $("#txtToken").html(res.token);
            }
        },"JSON");
    };

    Chat.getServiceURL = function () {
        $.ajax({
            url: "/handle/getServiceURL",
            data: {},
            type: "POST",
            beforeSend: function(xhr){xhr.setRequestHeader('token', Chat.token);},//这里设置header
            success: function(res) {
                Chat.URL = res.message;
                $("#txtURL").html(res.message);
            },
            dataType:"JSON"
        });
    };

</script>
</body>
</html>
