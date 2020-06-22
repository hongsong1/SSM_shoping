var initURL="/order/getdingdan";
var AddUrl="/order/addOrder";
var province1;
var city1;
var country1;
var addressInfo;
$(function(){
    dingdan.init();
    dingdan.address();
})
dingdan={};

dingdan.address=function(){
    $.ajax({
        url:"address/getAddress",
        type:"post",
        success:function (res) {
            console.log(res);
            var res1=res.mapObject.address;
            if(res.successCode==100){
                for(var i=0;i<data.provinces.length;i++){
                    if(res1.province==data.provinces[i].name){
                        province1=data.provinces[i].title;

                    }
                }
                // alert(province1);
                for(var j=0;j<data.cities[res1.province].length;j++){
                    var opc1=document.createElement("option");
                    if(res1.city==data.cities[res1.province][j].name){
                        city1=data.cities[res1.province][j].title;
                    }
                    for(var a=0;a<data.areas[res1.city].length;a++){
                        if(res1.country==data.areas[res1.city][a].name){
                            country1=data.areas[res1.city][a].title;
                        }
                    }
                }

                addressInfo="收货人姓名:"+res1.name+"  "+"联系电话:"+res1.phone+"收货地址"+province1+city1+country1+res1.street;
                $("#tbody").append("<tr><td colspan='5'>"+addressInfo+"<a href='self_info.html?address'>修改地址</a></td></tr>");
                console.log("地址"+addressInfo);
            }
            if(res.erroCode==101){
                $("#infoAll").html("");
                toastr['info']("你没有设置默认地址请添加");
                $("#myModal").modal('show');
            }
        }
    })
}
dingdan.init=function(){
    $.ajax({
        url:initURL,
        type:"post",
        success:function (res) {
            console.log(res);
            var goodsLists=res.mapObject.dingdan.goodsList;
            for(var i=0;i<goodsLists.length;i++){
                $("#tbody").append("<tr><td><img src='"+goodsLists[i].gimg+"' width='100%' height='100%'></td>" +
                    "<td class='td1'>"+goodsLists[i].gname+"</td>" +
                    "<td class='td1'>"+goodsLists[i].gcount+"</td>" +
                    "<td class='td1'>"+goodsLists[i].gprice+"</td>" +
                    "<td class='td1'>"+goodsLists[i].gprice*goodsLists[i].gcount+"</td></tr>")

            }
            $("#tbody").append("<tr><td colspan='5'>需支付<span>"+res.mapObject.dingdan.alltatal+"</span>"+"<button onclick='dingdan.addorder()' >立即支付</button>"+"</td></tr>")

            dingdan.addorder=function(){
                $.ajax({
                    url:AddUrl,
                    type:"post",
                    data:{
                      address:addressInfo
                    },
                    success:function(res){
                        $("#addOrder").append(res.mapObject.str);
                    }
                })
            }
        }
    })
}







