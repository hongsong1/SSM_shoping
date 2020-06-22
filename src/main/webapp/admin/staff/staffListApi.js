/**
 * @author hs
 * @date 2019/12/24
 * 初始化
 **/
$(function () {
    rechargeManager.initList();

})

/**
 * @author hs
 * @date 2019/12/24
 * 定义函数
 **/
var rechargeManager = {};




/**
 * @author hs
 * @date 2019/12/24
 * 加载列表
 **/
rechargeManager.initList = function () {
    $("#rechargeRuleList").bootstrapTable({
        url: "/staff/getAll", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: rechargeManager.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [10, 25, 50, 100], //每页可选择记录数
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
                console.log(result)
                return {
                    'total': result.mapObject.staffs.total, //总条数
                    'rows': result.mapObject.staffs.list //所有的数据
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
            title: "编号",
            visible: false
        },{
            field: 'staffid',
            title: "员工编号"
        }, {
            field: 'staffname',
            title: "员工姓名"
        }, {
            field: 'phone',
            title: "电话号码"
        }, {
            field: 'idcard',
            title: "身份证号"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                if (value == 1) {
                    return '<span class="label label-info">在职</span>';
                } else if (value == 2){
                    return '<span class="label label-danger">离职</span>';
                }else if (value == 3){
                    return '<span class="label label-danger">黑名单</span>';
                }
            }
        },  {
            field: 'rolename',
            title: "职位",
            formatter: function (value) {
                switch(value){
                    case 0:return '<span class="label label-info">无职位</span>'; break;
                    case "管理员":return '<span class="label label-primary">管理员</span>';break;
                    case "吧台":return '<span class="label label-primary">吧台</span>';break;
                    case "前台":return '<span class="label label-info">前台</span>';break;
                    case "收银员":return '<span class="label label-info">收银员</span>';break;
                    case "店长":return '<span class="label label-success">店长</span>';break;
                    default:
                        return "<span class='label label-info'>"+value+"</span>";
                        break;
                }
            }
        },{
            field: 'createdtime',
            title: "入职时间"
        }, {
            field: 'address',
            title: "家庭住址"
        }, {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return rechargeManager.buttonOption(value, row, index);
            }
        }
        ]
    });
}

/**
 * @author hs
 * @date 2019/12/24
 * 添加事件  给 updateRule/delRule  添加了 点击事件
 **/
window.buttonOperateEvent = {
    'click .updateRule': function (e, value, row, index) {
        //row 这一行的数据
        rechargeManager.update(row);
    },
    'click .delRule': function (e, value, row, index) {
        rechargeManager.del(row);
    }

}

/**
 * @author hs
 * @date 2019/12/24
 * 给操作添加 对象 button
 **/
rechargeManager.buttonOption = function (value, row, index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateRule">修改</button>');
    if (row.status == 1) {
        returnButton.push('<button class="btn btn-danger delRule">离职</button>');
    } else {
        returnButton.push('<button class="btn btn-success delRule">在职</button>');
    }
    return returnButton.join('');
}

/**
 * @author hs
 * @date 2019/12/24
 * 传递参数
 **/
rechargeManager.queryParams = function (params) {

    return {
        "pageNumber": params.pageNumber,
        "pageSize": params.pageSize,
        "searchStartTime": $("#searchStartTime").val(),
        "searchEndTime": $("#searchEndTime").val(),
        "searchName": $("#searchName").val(),
        "searchStatus": $("#searchStatus").val()
    }
}

/**
 * @author hs
 * @date 2019/12/24
 * 搜索按钮
 **/
rechargeManager.search = function () {

    //重新加载 bootstrap table
    $("#rechargeRuleList").bootstrapTable('refresh', {pageNumber: 1})
}

/**
 * @author hs
 * @date 2019/12/24
 * 点击修改时模态框添加
 **/
rechargeManager.update = function (row) {
    $("#myModal").modal('show');
    $("#Id").val(row.id);
    $("#staffName").val(row.staffname);
    $("#staffPhone").val(row.phone);
    $("#staffStatus").val(row.status);
    $("#staffData").val(row.createdtime);
    $("#staffAdress").val(row.address);
    $("#staffId").val(row.staffid);
    $("#staffIdcard").val(row.idcard);
    $("#staffroldId").val(row.roleid);

};

/**
 * @author hs
 * @date 2019/12/24
 * 规则删除
 **/

rechargeManager.del = function (row) {
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/staff/del",
                type: "post",
                data: {
                    "status":row.status,
                    "id": row.id
                },
                dataType: 'json',
                success: function (result) {
                    if (result.successCode==100) {
                        toastr['success']("修改成功");
                        $("#rechargeRuleList").bootstrapTable('refresh');
                    }
                    if(result.erroCode==101){
                        toastr['success']("修改失败");
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
 * @author hs
 * @date 2019/12/24
 * 添加工作人员对入职时间进行自动添加
 **/
rechargeManager.add = function () {
    var date = new Date();
    var dd = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    $("#staffData").val(dd);
    $("#myModal").modal('show');
}

/**
 * @author hs
 * @date 2019/12/25
 * 关闭模态框  触发操作
 * 1.将验证重置
 * 2.重置表单内容
 **/
$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#ruleForm").data('bootstrapValidator').resetForm();
    $("#ruleForm")[0].reset();
})

/**
 * @author hs
 * @date 2019/12/25
 * 模态框确认提交按钮
 **/
rechargeManager.operate = function () {
    var bootstrapValidator = $("#ruleForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/staff/update",
            type: "post",
            data: {
                "id":$("#Id").val(),
                "staffid": $("#staffId").val(),
                "staffname": $("#staffName").val(),
                "address": $("#staffAdress").val(),
                "phone": $("#staffPhone").val(),
                "createdtime": $("#staffData").val(),
                "status": $("#staffStatus").val(),
                "idcard": $("#staffIdcard").val(),
                "roleid":$("#staffroldId").val()
            },
            dataType:"json",
            success: function (result) {
                if (result.successCode == 100) {
                    toastr['success']("修改成功");
                    $("#rechargeRuleList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                }
                if(result.erroCode==101){
                    toastr['success']("修改失败");
                }
                if(result.erroCode==500){
                    toastr['error']("权限不足不能进行修改操作");
                }
            }
        })
    }
}

/**
 * @author hs
 * @date 2019/12/25
 * 验证
**/
$("#ruleForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        staffName: {
            validators: {
                notEmpty: {
                    message: "员工姓名不能为空"
                }
            }
        },
        staffPhone: {
            validators: {
                notEmpty: {
                    message: "电话号码不能为空"
                },
                regexp: { //正则表达式
                    regexp: /^[0-9]{11}$/,
                    message: '电话号码格式不正确'
                }

            }
        },
        staffIdcard: {
            validators: {
                notEmpty: {
                    message: "身份证号不能为空"
                },
                regexp: { //正则表达式
                    regexp: /^[0-9]{17}[0-9||x||X]{1}$/,
                    message: '身份证号格式不正确'
                }
            }
        },


    }
});


/**
 * 加载角色
 */
window.onload=function(){
    rechargeManager.initRole();
};
rechargeManager.initRole=function(){
    $.ajax({
        url:"/staff/getRole",
        type:"post",
        dataType:"json",
        success:function(res){
            var roleList=res.mapObject.roles;
            if(res.successCode==100){
                console.log(roleList);
                for (var i = 0; i < roleList.length; i++) {
                    console.log(roleList[i].roleName);
                    $("#staffroldId").append("<option value="+roleList[i].id+">"+roleList[i].roleName+"</option>>")
                }
            }else{
                toastr['error']("数据有误");
            }
        }
    })
}