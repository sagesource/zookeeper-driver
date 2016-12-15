var zTreeObj;
$(function () {
    initNodeTree();

    eventBtnCreateNode();
    eventBtnSaveNode();
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
                $('#id_input_parent_path').val(treeNode.id);

                initNodeInfo(treeNode);
            }
        }
    };

    zTreeObj = $.fn.zTree.init($("#id_node_tree"), setting);
}

/**
 * 初始化节点数据信息
 *
 * @param treeNode
 */
function initNodeInfo(treeNode) {
    var nodeData = {clientKey: clientKey, path: treeNode.id};
    ajaxGet(readDataApi, nodeData, function (result) {
        if (result.code == 100) {
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

            $('#id_zinfo_data').html(response.data);

            $('#id_table_zinfo').show();
        } else {
            bootbox.alert("获取节点数据失败:" + result.message);
        }
    }, function (error) {
        bootbox.alert("获取节点数据失败");
    });
}

/**
 * 创建节点按钮事件
 */
function eventBtnCreateNode() {
    $('#id_btn_create_node').click(function () {
        $('#id_btn_save_node').removeAttr('disabled');
        $('#id_progress_save_node').hide();

        $('#id_input_node_name').val('');
        $('#id_input_node_data').val('');
    });
}

/**
 * 保存节点按钮事件
 */
function eventBtnSaveNode() {
    $('#id_btn_save_node').click(function () {
        $(this).attr('disabled', true);
        $('#id_progress_save_node').show();

        var data = $('#id_input_node_data').val();
        var parentPath = $('#id_input_parent_path').val();
        var childrenPath = $('#id_input_node_name').val();
        var path = parentPath + "/" + childrenPath;

        var data = {
            clientKey: clientKey,
            path: path,
            data: data
        }

        ajaxPost(createNodeApi, data, function (result) {
            if (result.code == 100) {
                bootbox.alert("节点创建成功", function () {
                    var obj = zTreeObj.getNodeByParam("id", parentPath);
                    //动态添加节点
                    var node = {"id": path, "name": childrenPath, "isParent": false};
                    zTreeObj.addNodes(obj, -1, node);
                });
                $('#id_modal_create_node').modal('hide');
            } else {
                bootbox.alert(result.message);
            }
        }, function (error) {
            bootbox.alert("节点创建失败");
        })
    });
}