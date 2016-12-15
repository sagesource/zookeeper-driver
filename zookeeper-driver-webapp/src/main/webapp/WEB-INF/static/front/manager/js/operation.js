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

            }
        }
    };

    $.fn.zTree.init($("#id_node_tree"), setting);
}