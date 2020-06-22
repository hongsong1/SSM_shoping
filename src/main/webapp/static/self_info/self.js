/**
 * 这个市地址模态框提交事件修改事件
 * @type {string}
 */
var updateURL="address/updateAddress";
var addressInfo;
var selfinfo={};
$(function(){
    // var l=window.location.search.split('?');
    // if(l[1]=="address"){
    //     $("#selfa8").click();
    //     return ;
    // }
    // if(l[1]=="state"){
    //     $("#selfa2").click();
    //     return;
    // }
    selfinfo.init();
})


/**
 * 个人信息
 */
$("#selfa5").click(function () {
    window.location.href="/self_info.html";
})



/**
 * 已支付
 */
$("#selfa1").click(function(){
    for(i=1;i<9;i++){
        $("#selfa"+i).css("color","rgb(51,51,51)");
    }
    $(this).css({"color":"#ff6700"});
    selfinfo.zhifu(1);
})
/**
 * 未支付
 */
$("#selfa2").click(function(){
    for(i=1;i<9;i++){
        $("#selfa"+i).css("color","rgb(51,51,51)");
    }
    $(this).css({"color":"#ff6700"});
    selfinfo.zhifu(0);
})

selfinfo.zhifu=function(res){
   var iframe="<iframe name=\"iframe1\" src='dingdancenter.html?state="+res+"'  style=\"width:100%;height:100%;border:0px\">\n" +
        "\n" +
        "\t\t\t</iframe>";
    $("#infoAll").html("");
    $("#infoAll").append(iframe);
}



/**
 *显示个人信息
 */
selfinfo.init=function(){
    var l=window.location.search.split('?');
    if(l[1]=="address"){
        $("#selfa8").click();
        return false;
    }
    if(l[1]=="state"){
        $("#selfa2").click();
        return false;
    }
    $.ajax({
        url:"consumer/getSession",
        type:"post",
        success:function(res){
            if(res.successCode!=100){
                window.location.href="login.html";
            }else{
                $.ajax({
                    url:"/consumer/getself",
                    type:"post",
                    success:function(res){
                        if(res.successCode==100){
                            var user=res.mapObject.users;
                            $("#infoAll").html("");
                            $("#infoAll").append("\t\t<div class=\"grzlbt ml40\">用户信息</div>\n" +
                                "\t\t\t<div class=\"subgrzl ml40\"><span width=\"500px\">真实姓名</span><span id=\"name3\">"+user.name+"</span></div>\n" +
                                "\t\t\t<div class=\"subgrzl ml40\"><span width=\"500px\">个性签名</span><span id=\"qianming1\">"+user.qianming+"</span></div>\n" +
                                "\t\t\t<div class=\"subgrzl ml40\"><span width=\"500px\">身份证号</span><span id=\"idcard1\">"+user.idcard+"</span></div>\n" +
                                "\t\t\t<div class=\"subgrzl ml40\"><span>联系电话</span><span id=\"phone\">"+user.phone+"</span></div>\n" +
                                "\t\t\t<div class=\"subgrzl ml40\"><span>性别</span><span id=\"sex1\">"+user.sex+"</span></div>\n" +
                                "\t\t\t<div class=\"subgrzl ml40\"><span>生日</span><span id=\"birthday1\">"+user.birthday+"</span></div>\n" +
                                "\t\t\t<div class=\"jsanniu fr\"><input  id=\"addressupdate\" type=\"submit\" name=\"jiesuan\"  value=\"修改\" onclick=\"selfinfo.updateself1()\" /></div>")

                            if(user.name!="待完善"){
                                $("#nameself").val(user.name);
                            }
                            if(user.qianming!="待完善"){
                                $("#qianming").val(user.qianming);
                            }
                            if(user.idcard!="待完善"){
                                var idcard=res.idcard;
                                $("#idcard").val(user.idcard);
                            }
                            if(user.sex!="待完善"){
                                $("#sex").val(user.sex);
                            }
                            if(user.phone!="待完善"){
                                $("#phoneself").val(user.phone);
                            }
                            if(user.email!="待完善"){
                                $("#emailself").val(user.email);
                            }

                            selfinfo.updateself1=function () {
                                $("#selfModel").modal('show');
                            }
                        }
                    }
                })
            }
        }
    })
};

/**
 * 修改个人信息
 */
selfinfo.updateself=function(){
   var selfForm1= $("#selfForm").serialize();
    $.ajax({
        url:"/consumer/updateself",
        type:"post",
        data:selfForm1,
        success:function (res) {
            if(res.successCode==100){
                toastr['success']("操作成功");
                $("#selfModel").modal('hide');
             selfinfo.init();
            }else{
                toastr['error']("修改失败");
            }
        }
    })
}


/**
 * 我的订单 (已完成功能)
 */
$("#selfa8").click(function(){
    for(i=1;i<9;i++){
        $("#selfa"+i).css("color","rgb(51,51,51)");
    }
    $(this).css({"color":"#ff6700"});
    selfinfo.getAddress();
})

/**
 * 获取地址 (已完成功能)
 */
selfinfo.getAddress=function () {
    $.ajax({
        url:"address/getAddress",
        type:"post",
        success:function (res) {
            console.log(res);
            var res1=res.mapObject.address;
            if(res.successCode==100){
                $("#addressid").val(res1.id);
                $("#name1").val(res1.name);
                $("#phone1").val(res1.phone);
                $("#email1").val(res1.email);
                $("#province1").val(res1.province);
                for(var j=0;j<data.cities[res1.province].length;j++){
                    var opc1=document.createElement("option");
                    opc1.innerHTML=data.cities[res1.province][j].title;
                    opc1.value=data.cities[res1.province][j].name;
                    form2.appendChild(opc1)
                }
                $("#city").val(res1.city);
                for(var a=0;a<data.areas[res1.city].length;a++){
                    var opc3=document.createElement("option");
                    opc3.innerHTML=data.areas[res1.city][a].title;
                    opc3.value=data.areas[res1.city][a].name;
                    form3.appendChild(opc3)
                }
                $("#country").val(res1.country);
                $("#street1").val(res1.street);

                var province=$("#province1 option:selected").text();
                var country=$("#country option:selected").text();
                var city=$("#city option:selected").text();

                $("#infoAll").html("<div class=\"grzlbt ml40\">地址信息</div>\n" +
                    "\t\t\t<div class=\"subgrzl ml40\"><span width=\"500px\">收货人姓名</span><span id=\"name\">"+res1.name+"</span></div>\n" +
                    "\t\t\t<div class=\"subgrzl ml40\"><span>收货人手机号</span><span id=\"phone\">"+res1.phone+"</span></div>\n" +
                    "\t\t\t<div class=\"subgrzl ml40\"><span>收货人邮箱</span><span id=\"email\">"+res1.email+"</span></div>\n" +
                    "\t\t\t<div class=\"subgrzl ml40\"><span>收货地址</span><span id=\"province\">"+province+city+country+"</span></div>\n" +
                    "\t\t\t<div class=\"subgrzl ml40\"><span>详细地址信息</span><span id=\"street\">"+res1.street+"</span></div>\n" +
                    "\t\t\t<div class=\"jsanniu fr\"><input  id=\"addressupdate\" type=\"submit\" name=\"jiesuan\"  value=\"修改\" onclick=\"selfinfo.showModel()\" /></div>\n");
                // addressInfo="收货人姓名:"+res1.name+"  "+"联系电话:"+res1.phone+"收货地址"+province+city+country+res1.street;
                // $.ajax({
                //     url:"address/addressInfo",
                //     type:"post",
                //     data: {addressInfo: addressInfo
                //             }
                // })
                selfinfo.showModel=function(){
                    $("#myModal").modal('show');
                }
            }
            if(res.erroCode==101){
                $("#infoAll").html("");
                toastr['info']("你没有设置默认地址请添加");
                $("#myModal").modal('show');
            }
        }
    })

}
/**
 * 修改地址 (已完成功能)
 */
selfinfo.update=function () {
    var bootstrapValidator = $("#addressForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()){
        var address=$("#addressForm").serialize();
        $.ajax({
            url:updateURL,
            type:'post',
            data:address,
            dataType:'json',
            success:function (result) {
                if(result.successCode==100){
                    toastr['success']("操作成功");
                    $("#myModal").modal('hide');
                    selfinfo.getAddress();
                }
                if(result.errorCode==101){
                    toastr['error']("增加失败");
                }
            }
        })
    }
}


/**
 * 验证信息(已完成功能)
 */
$("#addressForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {

        name: {
            validators: {
                notEmpty: {
                    message: "收货人姓名"
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: "收货人手机号不能为空"
                }
            }
        },
        email: {
            validators: {
                notEmpty: {
                    message: "收货人电子邮箱不能为空"
                }
            }
        }

    }
});
