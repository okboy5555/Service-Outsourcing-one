<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User Chat Page</title>
</head>
<body>
<div>
    <p id="txtToken">txtToken</p>
    <p id="txtURL">txtURL</p>
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
            Chat.connect('ws://' + window.location.host + '/ws/cs'+Chat.URL);
        } else {
            Chat.connect('wss://' + window.location.host + '/ws/cs'+Chat.URL);
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

    Chat.getServiceURL = function () {
        $.ajax({
            url: "/handle/admin/connectCustomer",
            data: {},
            type: "POST",
            success: function(res) {
                Chat.URL = res;
            }
        });
    };

</script>
</body>
</html>
