



// 绘制图表。
$(function(){
    userCensus.initUser();
})
var userCensus={};
userCensus.initUser=function(){
    $.ajax({
        url:"/GetOrderStaticServlet",
        type:"post",
        dataType:"json",
        success:function(res){
            console.log(res.data);
            // 绘制图表。
            var myChart = echarts.init(document.getElementById('main'));
            setTimeout(function () {
                option = {
                    tooltip : {
                        trigger: 'item',
                    },
                    legend: {},
                    tooltip: {
                        trigger: 'axis',
                        showContent: true //这个统计通是否显示数据
                    },
                    dataset: {
                        //这一需要动态传参
                        source: [
                            res.data.map,
                            res.data.sum,
                            res.data.count

                        ]
                    },

                    xAxis: {type: 'category'},
                    yAxis: {gridIndex: 0},
                    series: [
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},


                    ]
                };

                myChart.on('updateAxisPointer', function (event) {
                    var xAxisInfo = event.axesInfo[0];
                    if (xAxisInfo) {

                        myChart.setOption({

                        });
                    }
                });

                myChart.setOption(option);

            });

        }
    })
}

