$(function () {
    initNodeTree();
});

/**
 * 初始化树状结构信息
 */
function initNodeTree() {
    //Ztree 配置
    var setting = {
        view: {
            selectedMulti: false,
            showIcon: false
        },
        async: {
            // 开启异步加载
            enable: true,
            url: childrenForZtreeApi,
            type: 'GET',
            dataType: 'json',
            // 自动提交的参数
            autoParam: ['id'],
            // 其他提交的参数
            otherParam: ["clientKey", clientKey],
            // 返回值处理
            dataFilter: function (treeId, parentNode, responseData) {
                if (responseData.response.id == "/") return responseData.response;
                return responseData.response.children;
            }
        },
        callback: {
            beforeClick: function (treeId, treeNode) {
                $('#id_node_name').html(treeNode.id);
                initNodeInfo(treeNode);
            }
        }
    };

    $.fn.zTree.init($("#id_node_tree"), setting);
}

/**
 * 初始化节点数据信息
 *
 * @param treeNode
 */
function initNodeInfo(treeNode) {
    var nodeData = {clientKey: clientKey, path: treeNode.id};
    ajaxGet(readDataApi, nodeData, function (result) {
        var response = result.response;
        var stat = response.stat;

        $('#id_zinfo_czxid').html(stat.czxid);
        $('#id_zinfo_mzxid').html(stat.mzxid);
        $('#id_zinfo_ctime').html(stat.ctime);
        $('#id_zinfo_mtime').html(stat.mtime);
        $('#id_zinfo_version').html(stat.version);
        $('#id_zinfo_cversion').html(stat.cversion);
        $('#id_zinfo_aversion').html(stat.aversion);
        $('#id_zinfo_ephemeralOwner').html(stat.ephemeralOwner);
        $('#id_zinfo_dataLength').html(stat.dataLength);
        $('#id_zinfo_numChildren').html(stat.numChildren);
        $('#id_zinfo_pzxid').html(stat.pzxid);

        $('#id_table_zinfo').show();
    }, function (error) {
        bootbox.alert("获取节点数据失败");
    });
}