/**
 * 初始列表
    @autor lxx
    @date   2019/12/25
*/
$(function () {
    goodsList.initList();

})
/**
 * 定义函数
    @autor lxx
    @date   2019/12/25
*/
var goodsList = {};

goodsList.addimg=function(){
    $("#file1").click();
}
$("#file1").change(function (e) {

    var type=e.currentTarget.files[0].type;
    // alert(type);
    var strings = type.split("/");
    if(strings[0]!='image'){
        toastr['error']('上传失败请上传图片');
        return;
    }
    var blob=$("#file1")[0].files[0];
    var url=window.URL.createObjectURL(blob);
    $("#image1").attr("src",url);
})


goodsList.initList = function () {
    $("#showGoodsList").bootstrapTable({
        url: "/goods/getAll", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: goodsList.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [5,10, 25, 50], //每页可选择记录数
        strictSearch: true,
        showColumns: false, // 是否显示所有的列
        showRefresh: false, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        smartDisplay: false,
        onClickRow: function (e, row, element) {
            $(".success").removeClass("success");
            $(row).addClass("success");
        },
        responseHandler: function (result) {
            if (result != null) {
                return {
                    'total':result.mapObject.goods.total, //总条数
                    'rows': result.mapObject.goods.list //所有的数据
                };
            }
            return {
                'total': 0, //总条数
                'rows': [] //所有的数据
            }
        },
        //列表显示
        columns: [{
            field: 'id',
            title: "商品编号"

        }, {
            field: 'goodname',
            title: "商品名称"
        },{
            field: 'image',
            title: "图片",
            formatter: function (value) {
                return "<img src='"+value+"' style='width:50px ;height: 50px'></img>";
            }
        },{
            field: 'createtime',
            title: "创建日期"
        }, {
            field: 'stock',
            title: "库存量"
        }, {
            field: 'name',
            title: "商品类型"
        }, {
            field: 'price',
            title: "价格"
        }, {
            field: 'state',
            title: "状态",
            formatter: function (value) {
                    if (value == 1) {
                        return '<span class="label label-success">上架</span>';
                    } else {
                        return '<span class="label label-danger ">下架</span>';
                    }
            }
        },{
            field: 'operation',
            events:buttonOperateEvent,
            title: '操作',
            formatter: function (value, row,index) {
                return goodsList.buttonOption(value, row,index);
              }
             }
        ]

    });
}

    window.buttonOperateEvent = {
    'click .updateGoods': function (e, value, row, index) {
        //row 这一行的数据
        goodsList.update(row);
    },
    'click .delGoods': function (e, value, row, index) {
        goodsList.del(row);
    }
}
/**
 * 给 update/del添加了 点击事件
    @autor lxx
    @date   2019/12/25
*/
goodsList.buttonOption = function (value, row,index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateGoods">修改</button>');
    if (row.state == 1) {
        returnButton.push('<button class="btn btn-danger delGoods">下架</button>');
    } else {
        returnButton.push('<button class="btn btn-success delGoods">上架</button>');
    }
    switch (row.categoryName) {
        case 1:returnButton.push("")

    }
    return returnButton.join('');
}
/**
    @autor lxx
    @date   2019/12/25
 参数
*/
goodsList.queryParams = function (params) {

    return {
        "pageNumber": params.pageNumber,
        "pageSize": params.pageSize,
        "searchStartTime": $("#searchStartTime").val(),
        "searchEndTime": $("#searchEndTime").val(),
        "searchName": $("#searchName").val(),
        "searchStatus": $("#searchStatus").val()
    }
}
/**当我们点击搜索按钮的时候 table  进行刷新并且跳转到 第一页
 @autor lxx
 @date   2019/12/25
 */
goodsList.search = function () {
    $("#showGoodsList").bootstrapTable('refresh', {pageNumber: 1})
}


goodsList.add=function(){
    var now = new Date();
    var timer = now.getFullYear() + "-" +((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate();
    $("#createtime").val(timer);
    $("#image1").attr("src","http://localhost:9090/uploads/add.png");
    $("#myModal").modal('show');
}
/**
 * 修改商品信息update
*/
goodsList.update = function (row) {
    $("#id").val(row.id);
    $("#goodname").val(row.goodname);
    $("#stock").val(row.stock);
    $("#state").val(row.state);
    $("#categoryName").val(row.typeid);
    $("#price").val(row.price);
    $("#createtime").val(row.createtime);
    if(row.image==""){
        $("#image1").attr("src","http://localhost:9090/uploads/add.png");
    }else{
        $("#image1").attr("src",row.image);
    }
    $("#myModal").modal('show');
};
/**
 * 商品上架与下架del
    @autor lxx
    @date   2019/12/26
*/
goodsList.del = function (row) {

    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/goods/del",
                type: 'post',
                data: {
                    "id": row.id,
                    "state":row.state
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    if (result.successCode == 100) {
                        toastr['success']("修改状态成功");
                        $("#showGoodsList").bootstrapTable('refresh');
                    }
                    if(result.erroCode==101){
                        toastr['error']("修改失败哦了");
                    }
                    if(result.erroCode==500){
                        toastr['error']("权限不足不能进行修改操作");
                    }
                }
            })
        }
    })
}



/**
 *  * 关闭模态框  触发操作
 * 1.将验证重置
 * 2.重置表单内容
*/


$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#goodsForm").data('bootstrapValidator').resetForm();
    $("#goodsForm")[0].reset();
})

/**
 * 确认按钮
 **/
goodsList.operate = function () {
    var bootstrapValidator = $("#goodsForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        var form1 = new FormData(document.getElementById("goodsForm"));
        var image1=$("#image1").attr("src");
        if(image1=="http://localhost:9090/uploads/add.png"){
            toastr['error']("图片未上传");
            return;
        }
        $.ajax({
            url: "/goods/save",
            type: "post",
            data: form1,
            processData:false,
            contentType:false,
            dataType: 'json',
            success: function (result) {
                if (result.successCode==100) {

                    toastr['success']("修改成功");
                    $("#showGoodsList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                }
                if(result.erroCode==101){
                    toastr['success']("修改失败");
                }
                if(result.erroCode==500){
                    toastr['error']("权限不足不能进行修改操作");
                }
            }        })
    }
}


$("#goodsForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        name: {
            validators: {
                notEmpty: {
                    message: "商品名字不能为空"
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: "库存量不能为空"
                }
            }
        },
        categoryName: {
            validators: {
                notEmpty: {
                    message: "商品类型不能为空"
                }
            }
        },
        unitName: {
            validators: {
                notEmpty: {
                    message: "商品单位不能为空"
                }
            }
        },
        price: {
            validators: {
                notEmpty: {
                    message: "商品价格不能为空"
                }
            }
        }
    }
});

goodsList.initCategory=function(){
    $.ajax({
        url:"/goods/getType",
        type:"post",
        success:function(res){
            if(res.successCode==100){
                var typeList=res.mapObject.types;
                console.log(typeList);
                for (var i = 0; i < typeList.length; i++) {
                    $("#categoryName").append("<option value="+typeList[i].id+">"+typeList[i].name+"</option>>")

                }
            }
        }
    })
};

window.onload=function(){
    goodsList.initCategory();
};












