/**
 * 自定义验证
 * @type {boolean}
 */
var userSpan=false;
$("#username").bind("propertychange input",function () {
    this.value=this.value.replace(/[\W]/g,'');

    if(this.value.length<3 ||this.value.length>8){
        $("#userSpan").html("长度在3-8之间");
        $("#userSpan").css("color","red");
    }else{
        userSpan=true;
        $("#userSpan").html("√");
        $("#userSpan").css("color","green");
    }
})
var phoneSpan=false;
$("#registerPhone").bind("propertychange input",function () {
    this.value=this.value.replace(/[^\d]/g,'');

    if(this.value.length!=11){
        $("#phoneSpan").html("请输入正确的手机号");
        $("#phoneSpan").css("color","red");
    }else{
         phoneSpan=true;
        $("#phoneSpan").html("√");
        $("#phoneSpan").css("color","green");
    }
})
var passwordSpan=false;
$("#passwordg").bind("propertychange input",function () {
    if(this.value.length<6 ){
        $("#passwordSpan").html("密码长度不能小于6");
        $("#passwordSpan").css("color","red");
    }else{
        passwordSpan=true;
        $("#passwordSpan").html("√");
        $("#passwordSpan").css("color","green");
    }
})
var passwordSpan1=false;
$("#passwordr").bind("propertychange input",function () {
    if(this.value != $("#passwordg").val() ){
        $("#passwordSpan1").html("密码不一致");
        $("#passwordSpan1").css("color","red");
    }else{
        passwordSpan1=true;
        $("#passwordSpan1").html("√");
        $("#passwordSpan1").css("color","green");
    }
})

$("#submit1").click(function () {
    var from1=$("#formregister").serialize();
    if(passwordSpan&&passwordSpan1&&phoneSpan&&userSpan){
        $.ajax({
            url:"/consumer/register",
            type:"post",
            data:from1,
            success:function (res) {
                console.log(res);
                if(res.successCode==100){
                    window.location.href="/login.html";
                }else{
                    toastr['error']("已存在改用户名");
                }
            }
        })
    }else{
        toastr['error']("填写信息有误");
    }


})
