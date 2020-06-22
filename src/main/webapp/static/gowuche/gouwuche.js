
var gouwuche={};
/**
 * 全选 (已完成）
 */
$("#quanxuan").click(function(){
    var checked = $(this).prop("checked");
    // alert(checked);
    // alert($(this).name);
    $(".quanxuan").prop("checked",checked);
})

$(function(){
    gouwuche.init();
    gouwuche.getUser();
})
gouwuche.getUser=function(){
    $.ajax({
        url:"consumer/getSession",
        type:"post",
        success:function(res){
            if(res.successCode==100){
                $("#div_fr >ul").attr("hidden","hidden");
                var span=$("<span></span>").append("<a href=\"self_info.html\" type='color:white'>个人中心</a>");
                // span.css("color","white");
                $("#div_fr").append(span);
            }
        }
    })
}
gouwuche.init=function(){
    $.ajax({
        url:"consumer/getSession",
        type:"post",
        success:function(res){
            if(res.successCode!=100){
                window.location.href="login.html?src";
            }else{
                gouwuche.jiazai();
            }
        }
    })
};
var cartsInfo=[];
/**
 * 加载购物车(已完成）
 * */
gouwuche.jiazai=function(){
    $.ajax({
        url:"cart/getAll",
        type:"post",
        success:function(res){
            console.log(res);
            if(res.successCode==100){
                var carts=res.mapObject.carts;
                console.log(carts);
                for(var i=0;i<carts.length;i++){
                    var div1=$("<div></div>").addClass("content2 center");
                    var div2=$("<div></div>").addClass("sub_content fl");
                    var input=$("<input type='checkbox' data-goods='"+JSON.stringify(carts[i])+"' data-gid='"+carts[i].gid+"' data-price='"+carts[i].gprice+"' id='quanxuan"+carts[i].gid+"' value='"+carts[i].gprice*carts[i].gcount+"'  name='quanxuan'  />").addClass("quanxuan");
                    div2.append(input);
                    var div3=$("<div></div>").addClass("sub_content fl");
                    var img=$("<img src='"+carts[i].gimg+"' width='100%' height='80%'>");
                    img.css("margin-top","20px");
                    div3.append(img);
                    var div4=$("<div></div>").addClass("sub_content fl").append(carts[i].gname);
                    var div5=$("<div id='price'></div>").addClass("sub_content fl").append(carts[i].gprice);
                    var div6=$("<div></div>").addClass("sub_content fl");
                    var input1=$("<input class='shuliang' id='num"+carts[i].gid+"' type='number' value='"+carts[i].gcount+"' step='1' min='1' id='count' onclick='gouwuche.numbertatal("+carts[i].gid+","+carts[i].gprice+",this.value)'>");
                    div6.append(input1);
                    var div7=$("<div id='sub_content"+carts[i].gid+"'></div>").addClass("sub_content fl").append(carts[i].gprice*carts[i].gcount);
                    var div8=$("<div></div>").addClass("sub_content fl");
                    var a=$("<a href='/cart/del?gid="+carts[i].gid+"'></a>").append("×");
                    div8.append(a);
                    var div9=$("<div></div>").addClass("clear'").append("");
                    $("#gwc").append(div1);
                    div1.append(div2,div3,div4,div5,div6,div7,div8,div9);
                    $("#goodCount").html(carts.length);
                    cartsInfo[i]=carts[i].gid;
                }
                /**
                 * 给每个复选框设计点击事件
                 */
                $(".quanxuan").each(function(){
                    $(this).click(function(){
                        var count=0;
                        var tatal=0;
                        $(".quanxuan").each(function(){
                            if($(this).prop("checked")){
                                count=count+1;
                                var val = $(this).val();
                                tatal=parseFloat(val)+tatal;
                            }
                        })
                        if($("#quanxuan").prop("checked")){
                            count=count-1;
                        }
                        $("#countjian").html(count);
                        $("#priceCount").html(tatal);
                    })
                })
                /**
                 * 对于添加数量的刷新(已完成）
                 */
                gouwuche.numbertatal=function(id,price,value){
                    var count =parseFloat(price)*parseInt(value);
                    $("#quanxuan"+id).val(count);
                    $("#sub_content"+id).html(count);
                    var tatal=0;
                    $(".quanxuan").each(function(){
                        if($(this).prop("checked")){
                            count=count+1;
                            var val = $(this).val();

                            tatal=parseFloat(val)+tatal;
                        }
                        $("#priceCount").html(tatal);
                    })
                }
                /**
                 * 账单结算(未完成）
                 */
                $("#jiesuan").click(function () {
                $.ajax({
                    url:"/order/countOrder",
                    type:"post",
                    success:function (res) {
                        if(res.successCode==100){
                            $.ajax({
                                url:"/address/getAddress",
                                type:"post",
                                success:function (res) {
                                    if(res.successCode==100){
                                        var goodsList=[];
                                        $(".quanxuan").each(function(){

                                            if($(this).prop("checked")){
                                                if($(this).val()!="0"){
                                                    var gid=$(this).data("gid");
                                                    var goodsdata = $(this).data("goods");
                                                    // console.log($("#num"+gid).val());
                                                    goodsdata.gcount=$("#num"+gid).val();

                                                    goodsList[goodsList.length]=goodsdata;
                                                }

                                            }
                                        })
                                        $.ajax({
                                            url:"order/dingdan",
                                            type:"post",
                                            data:{
                                                goodsList:JSON.stringify(goodsList),
                                                alltatal:$("#priceCount").text()
                                            },
                                            success:function(res){
                                                if(res.successCode==100){
                                                    window.location.href="dingdanzhifu.html";
                                                }else if(res.erroCode==501){
                                                    toastr['error'](res.object);
                                                }
                                            }
                                        })
                                    }else{
                                        toastr['error']("请先去个人中心设置你的收货地址");
                                    }
                                }
                            })
                        }else{
                            setTimeout(function () {
                                window.location.href="/self_info.html?state";
                            },1000);
                            toastr['error']("你有为支付的订单请完成支付在购买,1秒之后跳转");
                        }
                    }
                })


                })

            }else{
                $("#jiesuangouche").html("");
            }
        }
    })
}
function  self(){
    window.location.href="localhost:8080/self_info.html";
}



