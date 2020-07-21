layui.define(['jquery'], function (exports) {
    var $ = layui.jquery,
        requestUrl = 'http://localhost:9090/';

    let ajax = function (method, type, data, async, callback) {
        $.ajax({
            url: requestUrl + method,
            type: type,
            dataType: 'json',
            async: async,
            headers: {
                accessToken: layui.data('accessToken').accessToken
            },
            data: data,
            success: function (res) {
                if (res.code == 1000){
                    // layer.msg(res.msg,{
                    //     icon: 2
                    // },function () {
                    //
                    // })
                    window.location = '/login.html'
                }
                if (method == '/auth/login'){
                    layui.data('accessToken', {
                        key: 'accessToken',
                        value: res.data.accessToken
                    })
                }else {
                    //刷新token
                }
                callback(res)
            }
        });
    }

    let refreshAuth = function () {
        $.ajax({
            url: requestUrl + 'auth/refreshToken',
            type: 'post',
            dateType: 'json',
            data: {
                accessToken: layui.data('accessToken').accessToken
            },
            success: function (token) {
                layui.data('accessToken', {
                    key: 'accessToken',
                    value: token.data
                })
            }
        })
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