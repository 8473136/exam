layui.define(['jquery'], function (exports) {
    var $ = layui.jquery,
        requestUrl = 'http://localhost:9090/';

    let ajax = function (method, type, data, async, callback) {
        $.ajax({
            url: requestUrl + method,
            type: type,
            dataType: 'json',
            async: async,
            data: data,
            success: callback
        });
    }

    var http = {
        get: function (method, data, async, callback) {
            ajax(method, 'get', data, async, callback);
        },
        post: function (method, data, async, callback) {
            ajax(method, 'post', data, async, callback);
        },
        delete: function (method, data, async, callback) {
            ajax(method, 'delete', data, async, callback);
        }
    };
    //输出接口
    exports('http', http);
});