/**
 * String的format方法,"test {0} js.format('123') => test 123 js"
 * @type {String.f}
 */
String.prototype.format = String.prototype.f = function () {
    var s = this,
        i = arguments.length;

    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};

/**
 * Ajax Get
 * @param url
 * @param data
 * @param success
 * @param error
 */
function ajaxGet(url, data, success, error) {
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        dataType: 'json',
        success: function (result) {
            success(result);
        },
        error: function (result) {
            error(result);
        }
    });
}

/**
 * Ajax Post
 * @param url
 * @param data
 * @param success
 * @param error
 */
function ajaxPost(url, data, success, error) {
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        dataType: 'json',
        success: function (result) {
            success(result);
        },
        error: function (result) {
            error(result);
        }
    });
}