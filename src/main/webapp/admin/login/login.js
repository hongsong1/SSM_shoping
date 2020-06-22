
$(function () {

})

/**
 * 定义函数
 **/
var loginManager = {};

loginManager.login = function () {
    $.ajax({
        url: "/admin/getAdmin",
        type: "post",
        data: {
            "userName": $("#loginName").val(),
            "password": $("#loginPwd").val()
        },
        success: function (result) {
            console.log(result);
            if (result.successCode==100){
                toastr['success'](result.message);
                console.log(result.mapObject.data);
                /**
                 * localStorage 是一个数据库
                 * 只能存 键值对 而且是前端的 小型数据库 数据存放在 页面中
                 */
                localStorage.setItem("userName",result.mapObject.data.staffname);
                localStorage.setItem("staffId",result.mapObject.data.staffid);
                localStorage.setItem("roleId",result.mapObject.data.roleid);
                window.location.href = "/admin/index/views/main.html";
            }else {
                toastr['error']("登录失败");
            }
        }
    })
}

