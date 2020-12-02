layui.define(['jquery','layer'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        // requestUrl = 'http://192.168.157.154:9090/';
        /** war包 如果有项目名后面填写项目名 没有项目名就去除（没有项目名需要保留斜杠）*/
        // requestUrl = window.location.origin + '/archives-1.1.0/';
        requestUrl = window.location.origin + '/management/';

    let ajax = function (method, type, data, async, callback) {
        var loading = layer.load(0, {shade: [0.3, '#000']});
        $.ajax({
            url: requestUrl + method,
            type: type,
            dataType: 'json',
            //async:false 同步 true异步
            async: async,
            headers: {
                accessToken: layui.data('accessToken').accessToken,
                'Content-Type': 'application/json;charset=utf-8;'
            },
            data: data,
            success: function (res) {
                layer.close(loading);
                if (res.code == 1000){
                    window.location = '/management/login.html'
                }else if (res.code == 200){

                }else {
                    layer.msg('出错了，错误码' + res.code + ',原因:' + res.msg, {
                        icon: 2
                    });
                }
                if (method == 'auth/login'){
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
            url: requestUrl + '/management/auth/refreshToken',
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
            ajax(method, 'GET', data, async, callback);
        },
        post: function (method, data, async, callback) {
            ajax(method, 'POST', data, async, callback);
        },
        delete: function (method, data, async, callback) {
            var loading = layer.load(0, {shade: [0.3, '#000']});
            $.ajax({
                url: requestUrl + method,
                type: 'DELETE',
                dataType: 'json',
                //async:false 同步 true异步
                async: async,
                headers: {
                    accessToken: layui.data('accessToken').accessToken
                },
                data: data,
                success: function (res) {
                    layer.close(loading);
                    if (res.code == 1000){
                        window.location = '/login.html'
                    }else if (res.code == 200){

                    }else {
                        layer.msg('出错了，错误码' + res.code + ',原因:' + res.msg, {
                            icon: 2
                        });
                    }
                    if (method == 'system/auth/login'){
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
        },
        getRoleButton: function(menuId) {
            // 显示在头部的按钮
            let header = ['add','download','import','batchDel','foldAll','unfoldAll','clearThirtyOut','clearAll']
            // 显示在行里的按钮
            let row = ['show','update','delete','export','up','down','setRole','resetPassword']
            http.get('/management/system/auth/getLoginButtons',{menuId: menuId},false,function (res) {
                let headerHtml = '<div class="layui-btn-container">'
                let rowHtml = ''
                res.data.forEach(function (item) {
                    if (header.includes(item.href)){
                        headerHtml += '<button class="layui-btn layui-btn-sm" lay-event="'+item.href+'">'+item.title+'</button>'
                    }
                    if (row.includes(item.href)){
                        if (item.href == 'delete') {
                            rowHtml += '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="'+item.href+'">'+item.title+'</a>'
                        }else {
                            rowHtml += '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="'+item.href+'">'+item.title+'</a>'
                        }
                    }
                })
                headerHtml += '</div>'
                $("#toolbar").append(headerHtml)
                $("#operating").append(rowHtml)
            })
        },
        requestUrl: requestUrl
    };
    //输出接口
    exports('http', http);
});
