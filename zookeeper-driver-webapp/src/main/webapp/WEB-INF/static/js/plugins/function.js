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