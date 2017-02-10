//检查cookie
var username = checkCookie();
if (username != null) {
    $("#name").val(username);
}

//登陆按钮
$("#loginBtn").click(function () {
    var name = $("#name").val();
    var password = $("#password").val();
    var remember = $("#rememberCheck").prop("checked");
    if (remember) {
        setCookie("username", name, 90);
    }

    var jsonObj = {"name": name, "passwd": BASE64.encoder(password)};

    http_post("/login", JSON.stringify(jsonObj), function (data, status) {
        alert("登陆成功" + JSON.stringify(data));
    });
});

//注册按钮
$("#registerBtn").click(function () {
    window.location.href = "register.html";
});
