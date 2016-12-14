<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>Zookeeper-Driver</title>


    <link rel="stylesheet" href="${rc.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/jstree/css/style.css">

    <link rel="stylesheet" href="${rc.contextPath}/static/front/manager/css/style.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/front/manager/css/node_manager.css">
</head>
<body>
<!-- HEADER END-->
<div class="navbar navbar-inverse set-radius-zero">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <strong>Zookeeper-Driver</strong>
            </a>
        </div>

    </div>
</div>
<!-- LOGO HEADER END-->
<section class="menu-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="navbar-collapse collapse ">
                    <ul id="menu-top" class="nav navbar-nav navbar-right">
                        <li><a class="menu-top-active" href="index.html">节点管理</a></li>
                        <li><a href="ui.html">集群监控</a></li>
                        <li><a href="table.html">操作日志</a></li>
                        <li><a href="forms.html">系统管理</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- MENU SECTION END-->

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="cls_node_tree">
                <ul>
                    <li data-jstree='{ "opened" : true }'>Root node
                        <ul>
                            <li data-jstree='{ "selected" : true }'>Child node 1</li>
                            <li>Child node 2</li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-9 cls_node_operation">
            ......
        </div>
    </div>
</div>
<!-- CONTENT-WRAPPER SECTION END-->

<script src="${rc.contextPath}/static/js/function.js"></script>
<script src="${rc.contextPath}/static/js/api.js"></script>
<script src="${rc.contextPath}/static/js/jquery.min.js"></script>

<script src="${rc.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/static/js/plugins/bootbox.min.js"></script>

<script src="${rc.contextPath}/static/jstree/js/jstree.min.js"></script>
<script src="${rc.contextPath}/static/front/manager/js/operation.js"></script>
</body>
</html>
