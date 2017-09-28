var token = '';
$(function(){
    token = window.localStorage.getItem('ics_token');
});

$(document).ready(function() {
    var lis = $('#sidebar').find('li');
    var search = window.location.search;
    search = search.substring(1);
    var searchlist = search.split("&");
    for(var item = 0; item < searchlist.length; item++) {
        var t = searchlist[item].split("=");
        if (t[0] == "p") {
            var tt = t[1];
            var a = $('#sidebar').find('li').find("a[name='" + tt + "']");
            var li = a.parent();
            $(document).attr("title", a.text());//修改title值
            li.addClass("active");
            break;
        } else {
            continue;
        }
    }
    if(search == ""){
        var tt = "Overview";
        var a = $('#sidebar').find('li').find("a[name='" + tt + "']");
        var li = a.parent();
        $(document).attr("title", a.text());//修改title值
        li.addClass("active");
    }
    $('#btn_searchButton').find('a').bind('click',btn_searchButton);

});

function btn_searchButton(){
    $(this).parent().parent().parent().find('button').html($(this).text()+' <span class="caret"></span>');
}
