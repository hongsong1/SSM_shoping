var dingdancenter={};

$(function(){
dingdancenter.init();
})
dingdancenter.init=function(){
    console.log(window.location.search);
    var state=null;
    var strings = window.location.search.split("=");
    if(strings[1]!=""){
        state=strings[1];
        if(strings[1]=="1"){
            $("#name").html("已支付订单");
        }else{
            $("#name").html("未支付订单");
        }

    }
    $.ajax({
        url:"/order/getOrder",
        data:{
            "state":state,
            "oid":$("#oid").val()
        },
        type:"post",
        success:function(res){
            if(res.successCode==100){
                $("#tbody").html("");
                var orders=res.mapObject.orders;
                console.log(res);
                console.log(orders);
                // if (orders.length == 0 || res.mapObject=="") {
                //     $("#body").html("为查询到订单");
                // }
                for(var i=0;i<orders.length;i++){
                    $("#tbody").append("<tr><td colspan='5'>订单时间："+orders[i].ordertime+"</td></tr>")
                    $("#tbody").append("<tr><td colspan='5'>订单编号："+orders[i].oid+"</td></tr>")
                    for (var j=0;j<orders[i].orderItems.length;j++){
                        $("#tbody").append("<tr><td><img src='"+orders[i].orderItems[j].goods[0].image+"' width='100%' height='100%'></td>" +
                            "<td class='td1'>"+orders[i].orderItems[j].goods[0].goodname+"</td>" +
                            "<td class='td1'>"+orders[i].orderItems[j].count+"</td>" +
                            "<td class='td1'>"+orders[i].orderItems[j].goods[0].price+"</td>" +
                            "<td class='td1'>"+orders[i].orderItems[j].subtotal+"</td></tr>")
                    }
                    if(strings[1]=="0"){
                        $("#tbody").append("<tr><td colspan='5'><a href='/order/continueOrder/"+orders[i].oid+"/"+orders[i].total+"' target='_parent'>继续支付</a>&nbsp&nbsp<a href='/order/delOrder/"+orders[i].oid+"'>取消订单</a></td></tr>")
                    }else if(orders[i].state=="3"){
                        $("#tbody").append("<tr><td colspan='5'><a href='/order/updateState/4/"+orders[i].oid+"'>确定收货</a></td></tr>");
                    }else if (orders[i].state=="1"){
                        $("#tbody").append("<tr><td colspan='5'>正在审核，准备发货中</td></tr>");
                    } else if(orders[i].state=="4"){
                        $("#tbody").append("<tr><td colspan='5'>已收货</td></tr>");
                    }
                    $("#tbody").append("<tr><td colspan='5'>总支付"+orders[i].total+"</td></tr>")
                    $("#tbody").append("<tr><td colspan='5'><hr></td></tr>")

                }
            }
            if(res.erroCode==101){
                $("#body").html("未查询到订单");
            }

    }

    })

}
 dingdancenter.url=function () {
     $.ajax({
         url:"",
         type: "json",
         success:function (res) {
            $("#conOrder").html(res.mapObject.conUrl);
         }
     })
 }