/**
 * Created by AnDong on 2016/11/30.
 */

var server_url = "http://127.0.0.1:8080";
var project_path = "/TestSpring";

//restfunl风格

//post创建
function http_post(method_url, params, success_callback) {
    http_post(method_url, params, success_callback, null);
}
function http_post(method_url, params, success_callback, error_callback) {
    $.ajax({
        type: "Post",
        url: project_path + method_url,
        //async:true,    //或false,是否异步
        //timeout:5000,    //超时时间
        data: params,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        //beforeSend: function () {},
        success: function (data, status) {
            success_callback(data, status);
        },
        error: function (data, status) {
            if (error_callback != null) {
                error_callback(data, status);
            }
        }
        //complete:function(){}
    });
    return false;
}
z
//get查询
function http_get(method_url, success_callback, error_callback) {
    http_get(method_url, success_callback, null);
}
function http_get(method_url, success_callback, error_callback) {
    $.ajax({
        type: "Get",
        url: project_path + method_url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, status) {
            success_callback(data, status);
        },
        error: function (data, status) {
            error_callback(data, status);
        }
    });
    return false;
}

//put更新
function http_put(method_url, data, success_callback) {
    http_put(method_url, data, success_callback, null);
}
function http_put(method_url, data, success_callback, error_callback) {
    $.ajax({
        type: "Put",
        url: project_path + method_url,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, status) {
            success_callback(data, status);
        },
        error: function (data, status) {
            error_callback(data, status);
        }
    });
    return false;
}

//delete删除
function http_delete(method_url, data, success_callback) {
    http_delete(method_url, data, success_callback, null);
}
function http_delete(method_url, data, success_callback, error_callback) {
    $.ajax({
        type: "Delete",
        url: project_path + method_url,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, status) {
            success_callback(data, status);
        },
        error: function (data, status) {
            error_callback(data, status);
        }
    });
    return false;
}