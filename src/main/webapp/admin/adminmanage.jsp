<%@ page pageEncoding="UTF-8"%>
<div class="searchBox">
    <div class="row">
        <div class="col-lg-4 col-md-4">
            <div class="input-group">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        姓名 <span class="caret"></span></button>
                    <ul id="btn_searchButton" class="dropdown-menu">
                        <li><a href="#">姓名</a></li>
                        <li><a href="#">用户名</a></li>
                        <li><a href="#">等级</a></li>
                        <%--<li role="separator" class="divider"></li>--%>
                        <%--<li><a href="#">Separated link</a></li>--%>
                    </ul>
                </div><!-- /btn-group -->
                <input type="text" class="form-control" placeholder="查找...">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="button">查找</button>
                </span>
            </div><!-- /input-group -->
        </div>
        <button class="btn btn-primary col-lg-1 col-md-1">新建</button>
    </div><!-- /.row -->
</div>
<div class="contentTable">
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <tr class="active">
                <th>用户名</th>
                <th>姓名</th>
                <th>等级</th>
                <th>上次登录时间</th>
                <th>操作</th>
            </tr>
            <tr id="search_content">

            </tr>
        </table>
    </div>
</div>