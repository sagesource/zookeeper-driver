/*
 业务操作JS
 */

/**
 * 获取 可用server列表
 */
function getUseServerList() {
    ajaxGet(useServerListApi, null, function (result) {
        if (result.code == 100) {
            var response = result.response;

            var values = "";
            var optionFormat = "<option value='{0}' address='{1}'>{2}</option>";

            var defOption = optionFormat.format('0', 'zookeeper 服务信息', '请选择要连接的ZK服务');
            values += defOption;
            response.forEach(function (obj) {
                var option = optionFormat.format(obj.clientKey, obj.servers, obj.serverDesc);
                values += option;
            });

            $('#form-address').html(values);
            //绑定事件
            useServerListChange();
        } else {
            bootbox.alert("获取服务列表失败");
        }
    }, function (error) {
        bootbox.alert("获取服务列表失败");
    });
}

/**
 * 可用server列表修改响应
 */
function useServerListChange() {
    $('#form-address').change(function () {
        var address = $(this).children('option:selected').attr('address');
        $('#form-info').val(address);
    });
}

/**
 * 连接到ZK服务
 */
function connectToServer() {
    var clientKey = $('#form-address').children('option:selected').val();
    if (clientKey != 0) {
        var data = {clientKey: clientKey};
        ajaxPost(connectServerApi, data, function (result) {
            var connectStat = false;

            if (result.code == 100) {
                var response = result.response;
                connectStat = response.link;
            }
            if (connectStat) {
                window.location.href = nodeManagerPage + "?clientKey=" + clientKey;
            } else {
                bootbox.alert("连接Zookeeper服务失败");
            }
        }, function (error) {
            bootbox.alert("连接Zookeeper服务失败");
        });
    }
}