/*
 业务操作JS
 */

/**
 * 获取 可用server列表
 */
function getUseServerList() {
    $.get(useServerListApi, function (result) {
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
    }, 'json');

    //绑定事件
    useServerListChange();
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