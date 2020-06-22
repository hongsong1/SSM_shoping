// 绘制图表。
$(function(){
    userCensus.initUser();
})
var userCensus={};
userCensus.initUser=function(){
    $.ajax({
        url:"/GetCarTypeServlet",
        type:"post",
        dataType:"json",
        success:function(res){
        console.log(res.data.len);
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
                            res.data.len,
                            res.data.pu,
                            res.data.qin,
                            res.data.bai,
                            res.data.hung,
                            res.data.bo,
                        ]
                    },
                    xAxis: {type: 'category'},
                    yAxis: {gridIndex: 0},
                    grid: {top: '55%'},
                    series: [
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                        {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                        {
                            type: 'pie',
                            id: 'pie',
                            radius: '40%',
                            center: ['50%', '35%'],

                            // label: {
                            //     formatter: '{b}: {@2019} ({d}%)'
                            // },
                            encode: {
                                itemName: 'product',
                                value: res.data.len[1],
                                tooltip: res.data.len[1]
                            }
                        }
                    ]
                };

                myChart.on('updateAxisPointer', function (event) {
                    var xAxisInfo = event.axesInfo[0];
                    if (xAxisInfo) {
                        var dimension = xAxisInfo.value + 1;
                        myChart.setOption({


                            series: {
                                id: 'pie',
                                label: {
                                    formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                                },
                                encode: {
                                    value: dimension,
                                    tooltip: dimension
                                }
                            }
                        });
                    }
                });

                myChart.setOption(option);

            });

        }
    })
}

