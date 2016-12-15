<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>Zookeeper-Driver</title>


    <link rel="stylesheet" href="${rc.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/ztree/css/zTreeStyle.css">

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
        <div class="col-md-2 cls_node_operation">
            <h4 class="page-head-line">节点结构</h4>

            <div class="cls_node_tree">
                <ul id="id_node_tree" class="ztree"></ul>
            </div>
        </div>
        <div class="col-md-5 cls_node_operation">
            <h4 class="page-head-line" id="id_node_name">当前节点</h4>
        </div>
        <div class="col-md-5 cls_node_operation">
            <h4 class="page-head-line">节点信息</h4>
            <table class="table table-bordered table-hover" style="display: none" id="id_table_zinfo">
                <thead>
                <tr>
                    <th>属性</th>
                    <th>值</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>czxid</td>
                    <td><span id="id_zinfo_czxid"></span></td>
                    <td>节点创建时的zxid</td>
                </tr>
                <tr>
                    <td>mzxid</td>
                    <td><span id="id_zinfo_mzxid"></span></td>
                    <td>节点最新一次更新发生时的zxid</td>
                </tr>
                <tr>
                    <td>ctime</td>
                    <td><span id="id_zinfo_ctime"></span></td>
                    <td>节点创建时的时间戳</td>
                </tr>
                <tr>
                    <td>mtime</td>
                    <td><span id="id_zinfo_mtime"></span></td>
                    <td>节点最新一次更新发生时的时间戳</td>
                </tr>
                <tr>
                    <td>version</td>
                    <td><span id="id_zinfo_version"></span></td>
                    <td>节点数据的更新次数</td>
                </tr>
                <tr>
                    <td>cversion</td>
                    <td><span id="id_zinfo_cversion"></span></td>
                    <td>子节点的更新次数</td>
                </tr>
                <tr>
                    <td>aversion</td>
                    <td><span id="id_zinfo_aversion"></span></td>
                    <td>节点ACL(授权信息)的更新次数</td>
                </tr>
                <tr>
                    <td>ephemeralOwner</td>
                    <td><span id="id_zinfo_ephemeralOwner"></span></td>
                    <td>如果该节点为ephemeral节点,与该节点绑定的session id</td>
                </tr>
                <tr>
                    <td>dataLength</td>
                    <td><span id="id_zinfo_dataLength"></span></td>
                    <td>节点数据的字节数</td>
                </tr>
                <tr>
                    <td>numChildren</td>
                    <td><span id="id_zinfo_numChildren"></span></td>
                    <td>子节点个数</td>
                </tr>
                <tr>
                    <td>pzxid</td>
                    <td><span id="id_zinfo_pzxid"></span></td>
                    <td>子节点（或该节点）的最近一次 创建 / 删除 的id</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- CONTENT-WRAPPER SECTION END-->
<script type="application/javascript">
    var baseContext = "${rc.contextPath}";
    var clientKey = "${clientKey}";
</script>

<script src="${rc.contextPath}/static/js/function.js"></script>
<script src="${rc.contextPath}/static/js/api.js"></script>
<script src="${rc.contextPath}/static/js/jquery.min.js"></script>

<script src="${rc.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/static/js/plugins/bootbox.min.js"></script>

<script src="${rc.contextPath}/static/ztree/js/jquery.ztree.core.js"></script>
<script src="${rc.contextPath}/static/front/manager/js/operation.js"></script>
</body>
</html>
