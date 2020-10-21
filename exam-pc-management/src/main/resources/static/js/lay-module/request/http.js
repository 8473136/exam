layui.define(['jquery'], function (exports) {
    var $ = layui.jquery,
        // requestUrl = 'http://192.168.157.154:9090/';
        requestUrl = 'http://127.0.0.1:9091/';

    let ajax = function (method, type, data, async, callback) {
        var loading = layer.load(0, {shade: [0.3, '#000']});
        $.ajax({
            url: requestUrl + method,
            type: type,
            dataType: 'json',
            async: async,
            headers: {
                accessToken: layui.data('accessToken').accessToken,
                'Content-Type': 'application/json;charset=utf-8;'
            },
            data: data,
            success: function (res) {
                layer.close(loading);
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
            },
            error: function(){
                // console.log('请求失败')
                // layer.close(loading);
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