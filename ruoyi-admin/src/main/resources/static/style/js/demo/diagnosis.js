function updateValue() {
    var action = $("#action option:selected").val();
    if(action!="请选择") {
        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/change')",
            data: {
                "action": action,
                "emp_num": document.getElementById("emp_num").value,
                "process_capacity": document.getElementById("process_capacity").value
            },
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    reloadAction();
                    toastr[success]('修改成功!')
                }
            }
        });
    }else {
        $("#emp_num").html("");
        $("#process_capacity").html("");
    }
}
function reloadAction() {
    var action = $("#action option:selected").val();
    if(action!="请选择") {
        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/bar1')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    $("#emp_num").val(d.data.emp_num);
                    $("#process_capacity").val(d.data.process_capacity);
                }
            }
        });
        if (action == 1) {
            $("#title").html("卸货情况");
            $("#title1").html("卸货人员闲忙程度");
        } else if (action == 2) {
            $("#title").html("检验情况");
            $("#title1").html("检验人员闲忙程度");
        } else if (action == 3) {
            $("#title").html("上架情况");
            $("#title1").html("上架人员闲忙程度");
        } else if (action == 4) {
            $("#title").html("分拣情况");
            $("#title1").html("分拣人员闲忙程度");
        }else {
            $("#title").html("");
            $("#title1").html("");
        }

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/Simulation')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var arr1=[];
                    var arr2=[];
                    for(var i=0;i<d.data.length;i++){
                        arr1.push(d.data[i].date);
                        arr2.push(d.data[i].value);
                    }
                    var lineData = {
                        labels: arr1,
                        datasets: [
                            {
                                label: "闲忙程度",
                                backgroundColor: 'rgba(26,179,148,0.5)',
                                borderColor: "rgba(26,179,148,0.7)",
                                pointBackgroundColor: "rgba(26,179,148,1)",
                                pointBorderColor: "#fff",
                                data: arr2
                            }
                        ]
                    };

                    var lineOptions = {
                        responsive: true
                    };
                    var ctx = document.getElementById('Simulation').getContext('2d');
                    new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

                }
            }
        });


        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/emp_Simulation')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var doughnutData = {
                        labels: ["休息时间","忙碌时间"],
                        datasets: [{
                            data: [d.data.freeTime,d.data.workTime],
                            backgroundColor: ["#a3e1d4","#dedede"]
                        }]
                    } ;
                    var ctx4 = document.getElementById('Emp_Simulation').getContext('2d');
                    new Chart(ctx4, {type: 'doughnut', data: doughnutData, options:doughnutOptions});

                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/en')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    $("#average_value1").html(d.data.average_value);
                    $("#min_value1").html(d.data.average_value);
                    $("#max_value1").html(d.data.max_value);
                    $("#range1").html(d.data.range);
                    $("#median1").html(d.data.median);
                    $("#mode1").html(d.data.mode);
                    $("#kurtosis1").html(d.data.kurtosis);
                    $("#skewness1").html(d.data.skewness);
                    $("#standard_deviation1").html(d.data.standard_deviation);
                    $("#standard_error1").html(d.data.standard_error);
                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/eq')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    $("#average_value2").html(d.data.average_value);
                    $("#min_value2").html(d.data.average_value);
                    $("#max_value2").html(d.data.max_value);
                    $("#range2").html(d.data.range);
                    $("#median2").html(d.data.median);
                    $("#mode2").html(d.data.mode);
                    $("#kurtosis2").html(d.data.kurtosis);
                    $("#skewness2").html(d.data.skewness);
                    $("#standard_deviation2").html(d.data.standard_deviation);
                    $("#standard_error2").html(d.data.standard_error);
                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/iq')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    $("#average_value3").html(d.data.average_value);
                    $("#min_value3").html(d.data.average_value);
                    $("#max_value3").html(d.data.max_value);
                    $("#range3").html(d.data.range);
                    $("#median3").html(d.data.median);
                    $("#mode3").html(d.data.mode);
                    $("#kurtosis3").html(d.data.kurtosis);
                    $("#skewness3").html(d.data.skewness);
                    $("#standard_deviation3").html(d.data.standard_deviation);
                    $("#standard_error3").html(d.data.standard_error);
                }
            }
        });
        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/ik')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    $("#average_value4").html(d.data.average_value);
                    $("#min_value4").html(d.data.average_value);
                    $("#max_value4").html(d.data.max_value);
                    $("#range4").html(d.data.range);
                    $("#median4").html(d.data.median);
                    $("#mode4").html(d.data.mode);
                    $("#kurtosis4").html(d.data.kurtosis);
                    $("#skewness4").html(d.data.skewness);
                    $("#standard_deviation4").html(d.data.standard_deviation);
                    $("#standard_error4").html(d.data.standard_error);
                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/enMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var echart1 = echarts.init(document.getElementById('echart1'));
                    var xAxisData = [];
                    var data1 = [];
                    for (var i = 0; i < d.data.length; i++) {
                        xAxisData.push(i);
                        data1.push(d.data[i].order_frequency);
                    }
                    var lineData = {
                        labels: xAxisData,
                        datasets: [
                            {
                                label: "闲忙程度",
                                backgroundColor: 'rgba(26,179,148,0.5)',
                                borderColor: "rgba(26,179,148,0.7)",
                                pointBackgroundColor: "rgba(26,179,148,1)",
                                pointBorderColor: "#fff",
                                data: data1
                            }
                        ]
                    };

                    var lineOptions = {
                        responsive: true
                    };
                    var ctx = document.getElementById('echart1').getContext('2d');
                    new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/enMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var dataAxis = [];
                    var data = [];
                    var dataShadow = [];
                    var yMax = 500;
                    for (var i = 0; i < d.data.length; i++) {
                        dataShadow.push(yMax);
                    }
                    for (var i = 0; i < d.data.length; i++) {
                        dataAxis.push(d.data[i].order_frequency);
                        data.push(d.data[i].goods_num);
                    }
                    var lineData = {
                        labels: dataAxis,
                        datasets: [
                            {
                                label: "闲忙程度",
                                backgroundColor: 'rgba(26,179,148,0.5)',
                                borderColor: "rgba(26,179,148,0.7)",
                                pointBackgroundColor: "rgba(26,179,148,1)",
                                pointBorderColor: "#fff",
                                data: data
                            }
                        ]
                    };

                    var lineOptions = {
                        responsive: true
                    };
                    var ctx = document.getElementById('echart2').getContext('2d');
                    new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

                }
            }
        });
        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/eqMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var dataAxis = [];
                    var data = [];
                    var dataShadow = [];
                    var yMax = 500;
                    for (var i = 0; i < d.data.length; i++) {
                        dataShadow.push(yMax);
                    }
                    for (var i = 0; i < d.data.length; i++) {
                        dataAxis.push(d.data[i].order_frequency);
                        data.push(d.data[i].goods_num);
                    }
                    var lineData = {
                        labels: dataAxis,
                        datasets: [
                            {
                                label: "闲忙程度",
                                backgroundColor: 'rgba(26,179,148,0.5)',
                                borderColor: "rgba(26,179,148,0.7)",
                                pointBackgroundColor: "rgba(26,179,148,1)",
                                pointBorderColor: "#fff",
                                data: data
                            }
                        ]
                    };

                    var lineOptions = {
                        responsive: true
                    };
                    var ctx = document.getElementById('echart21').getContext('2d');
                    new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

                }
            }
        });
        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/iqMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var dataAxis = [];
                    var data = [];
                    var dataShadow = [];
                    var yMax = 500;
                    for (var i = 0; i < d.data.length; i++) {
                        dataShadow.push(yMax);
                    }
                    for (var i = 0; i < d.data.length; i++) {
                        dataAxis.push(d.data[i].order_frequency);
                        data.push(d.data[i].goods_num);
                    }
                    var lineData = {
                        labels: dataAxis,
                        datasets: [
                            {
                                label: "闲忙程度",
                                backgroundColor: 'rgba(26,179,148,0.5)',
                                borderColor: "rgba(26,179,148,0.7)",
                                pointBackgroundColor: "rgba(26,179,148,1)",
                                pointBorderColor: "#fff",
                                data: data
                            }
                        ]
                    };

                    var lineOptions = {
                        responsive: true
                    };
                    var ctx = document.getElementById('echart22').getContext('2d');
                    new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/ikMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var dataAxis = [];
                    var data = [];
                    var dataShadow = [];
                    var yMax = 500;
                    for (var i = 0; i < d.data.length; i++) {
                        dataShadow.push(yMax);
                    }
                    for (var i = 0; i < d.data.length; i++) {
                        dataAxis.push(d.data[i].order_frequency);
                        data.push(d.data[i].goods_num);
                    }
                    var lineData = {
                        labels: dataAxis,
                        datasets: [
                            {
                                label: "闲忙程度",
                                backgroundColor: 'rgba(26,179,148,0.5)',
                                borderColor: "rgba(26,179,148,0.7)",
                                pointBackgroundColor: "rgba(26,179,148,1)",
                                pointBorderColor: "#fff",
                                data: data
                            }
                        ]
                    };

                    var lineOptions = {
                        responsive: true
                    };
                    var ctx = document.getElementById('echart23').getContext('2d');
                    new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

                }
            }
        });
        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/enMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var echart3 = echarts.init(document.getElementById('echart3'));
                    var data1 = [];
                    for (var i = 0; i < d.data.length; i++) {
                        data1.push(parseInt(d.data[i].order_frequency));
                    }
                    var data2 = [];
                    data2.push(data1);
                    var data = echarts.dataTool.prepareBoxplotData(data2);
                    var  option = {
                        title: [
                        ],
                        tooltip: {
                            trigger: 'item', //触发类型,数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                            axisPointer: { //指示器类型。
                                type: 'shadow'
                            }
                        },
                        grid: { //直角坐标系网格。
                            //show: true,//default: false
                            left: '10%',
                            right: '10%',
                            bottom: '15%',
                            //borderWidth: 1,
                            //borderColor: '#000',
                        },

                        xAxis: { //X轴
                            type: 'category', //'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
                            //data: data.axisData,
                            data: [''],
                            boundaryGap: true, //类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true，这时候刻度只是作为分隔线，标签和数据点都会在两个刻度之间的带(band)中间。
                            nameGap: 30, //坐标轴名称与轴线之间的距离。
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true, //是否显示分隔区域
                                //interval: 'auto', //坐标轴分隔区域的显示间隔，在类目轴中有效
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: 'black',

                            },
                            splitLine: { //坐标轴在 grid 区域中的分隔线。
                                show: true, //是否显示分隔线。默认数值轴显示，类目轴不显示。
                                lineStyle: { //分隔线样式
                                    type: 'dashed', //分隔线线的类型。
                                },
                            },
                            axisLine: { //坐标轴轴线相关设置。
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头, 默认不显示箭头，即 'none'
                                lineStyle: { //轴线样式
                                    width: 2,
                                    color: 'green',
                                    //opacity: 1, //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                                },
                            },
                            axisTick: { //坐标轴刻度相关设置。
                                show: true, //是否显示坐标轴刻度。
                                //alignWithLabel: true,//类目轴中在 boundaryGap 为 true 的时候有效，可以保证刻度线和标签对齐,default: false

                            }
                        },
                        yAxis: { //y轴
                            type: 'value',
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: '#fff',
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    type: 'dashed'
                                },
                            },
                            axisLine: {
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头
                                lineStyle: {
                                    width: 2,
                                    color: 'green'
                                },
                            },
                        },
                        dataZoom: [
                            {
                                type: 'slider',
                                show: true,
                                yAxisIndex: [0],
                                left: '93%',
                                start: 0, //数据窗口范围的起始百分比
                                end: 36
                            },
                            {
                                type: 'inside',
                                yAxisIndex: [0],
                                start: 0,
                                end: 36
                            }
                        ],
                        series: [
                            {
                                name: 'boxplot',//箱形图
                                type: 'boxplot',
                                //legendHoverLink: true, //是否启用图例 hover 时的联动高亮。
                                //hoverAnimation: false, //是否开启 hover 在 box 上的动画效果。
                                itemStyle: { //盒须图样式。
                                    //color: '#fff', //boxplot图形的颜色。 默认从全局调色盘 option.color 获取颜色
                                    borderColor: 'blue', //boxplot图形的描边颜色。支持的颜色格式同 color，不支持回调函数。
                                },
                                data: data.boxData,
                                tooltip: { //注意：series.tooltip 仅在 tooltip.trigger 为 'item' 时有效。
                                    formatter: function(param) {
                                        /*
                                            第一个参数 param 是 formatter 需要的数据集。 格式如下：
                                            {
                                                //组件类型
                                                componentType: 'series',
                                                // 系列类型
                                                seriesType: string,
                                                // 系列在传入的 option.series 中的 index
                                                seriesIndex: number,
                                                // 系列名称
                                                seriesName: string,
                                                // 数据名，类目名
                                                name: string,
                                                // 数据在传入的 data 数组中的 index
                                                dataIndex: number,
                                                // 处理过的数据项
                                                data: Object | Array,
                                                // 处理过的数据项
                                                value: number | Array,
                                                // 数据图形的颜色
                                                color: string,

                                                // 饼图的百分比
                                                percent: number,

                                            }
                                        */

                                        return [
                                            '值 ' + param.name + ': ',
                                            'lower: ' + param.data[0]
                                        ].join('<br/>')
                                    }
                                }
                            },
                            {
                                name: '异常值',//异常值
                                type: 'scatter',//分散
                                data: data.outliers
                            }
                        ]

                    };
                    echart3.setOption(option);
                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/eqMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var echart3 = echarts.init(document.getElementById('echart31'));
                    var data1 = [];
                    for (var i = 0; i < d.data.length; i++) {
                        data1.push(parseInt(d.data[i].order_frequency));
                    }
                    var data2 = [];
                    data2.push(data1);
                    var data = echarts.dataTool.prepareBoxplotData(data2);
                    var  option = {
                        title: [
                        ],
                        tooltip: {
                            trigger: 'item', //触发类型,数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                            axisPointer: { //指示器类型。
                                type: 'shadow'
                            }
                        },
                        grid: { //直角坐标系网格。
                            //show: true,//default: false
                            left: '10%',
                            right: '10%',
                            bottom: '15%',
                            //borderWidth: 1,
                            //borderColor: '#000',
                        },

                        xAxis: { //X轴
                            type: 'category', //'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
                            //data: data.axisData,
                            data: [''],
                            boundaryGap: true, //类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true，这时候刻度只是作为分隔线，标签和数据点都会在两个刻度之间的带(band)中间。
                            nameGap: 30, //坐标轴名称与轴线之间的距离。
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true, //是否显示分隔区域
                                //interval: 'auto', //坐标轴分隔区域的显示间隔，在类目轴中有效
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: 'black',

                            },
                            splitLine: { //坐标轴在 grid 区域中的分隔线。
                                show: true, //是否显示分隔线。默认数值轴显示，类目轴不显示。
                                lineStyle: { //分隔线样式
                                    type: 'dashed', //分隔线线的类型。
                                },
                            },
                            axisLine: { //坐标轴轴线相关设置。
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头, 默认不显示箭头，即 'none'
                                lineStyle: { //轴线样式
                                    width: 2,
                                    color: 'green',
                                    //opacity: 1, //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                                },
                            },
                            axisTick: { //坐标轴刻度相关设置。
                                show: true, //是否显示坐标轴刻度。
                                //alignWithLabel: true,//类目轴中在 boundaryGap 为 true 的时候有效，可以保证刻度线和标签对齐,default: false

                            }
                        },
                        yAxis: { //y轴
                            type: 'value',
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: '#fff',
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    type: 'dashed'
                                },
                            },
                            axisLine: {
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头
                                lineStyle: {
                                    width: 2,
                                    color: 'green'
                                },
                            },
                        },
                        dataZoom: [
                            {
                                type: 'slider',
                                show: true,
                                yAxisIndex: [0],
                                left: '93%',
                                start: 0, //数据窗口范围的起始百分比
                                end: 36
                            },
                            {
                                type: 'inside',
                                yAxisIndex: [0],
                                start: 0,
                                end: 36
                            }
                        ],
                        series: [
                            {
                                name: 'boxplot',//箱形图
                                type: 'boxplot',
                                //legendHoverLink: true, //是否启用图例 hover 时的联动高亮。
                                //hoverAnimation: false, //是否开启 hover 在 box 上的动画效果。
                                itemStyle: { //盒须图样式。
                                    //color: '#fff', //boxplot图形的颜色。 默认从全局调色盘 option.color 获取颜色
                                    borderColor: 'blue', //boxplot图形的描边颜色。支持的颜色格式同 color，不支持回调函数。
                                },
                                data: data.boxData,
                                tooltip: { //注意：series.tooltip 仅在 tooltip.trigger 为 'item' 时有效。
                                    formatter: function(param) {
                                        /*
                                            第一个参数 param 是 formatter 需要的数据集。 格式如下：
                                            {
                                                //组件类型
                                                componentType: 'series',
                                                // 系列类型
                                                seriesType: string,
                                                // 系列在传入的 option.series 中的 index
                                                seriesIndex: number,
                                                // 系列名称
                                                seriesName: string,
                                                // 数据名，类目名
                                                name: string,
                                                // 数据在传入的 data 数组中的 index
                                                dataIndex: number,
                                                // 处理过的数据项
                                                data: Object | Array,
                                                // 处理过的数据项
                                                value: number | Array,
                                                // 数据图形的颜色
                                                color: string,

                                                // 饼图的百分比
                                                percent: number,

                                            }
                                        */

                                        return [
                                            '值 ' + param.name + ': ',
                                            'lower: ' + param.data[0]
                                        ].join('<br/>')
                                    }
                                }
                            },
                            {
                                name: '异常值',//异常值
                                type: 'scatter',//分散
                                data: data.outliers
                            }
                        ]

                    };
                    echart3.setOption(option);
                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/iqMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var echart3 = echarts.init(document.getElementById('echart32'));
                    var data1 = [];
                    for (var i = 0; i < d.data.length; i++) {
                        data1.push(parseInt(d.data[i].order_frequency));
                    }
                    var data2 = [];
                    data2.push(data1);
                    var data = echarts.dataTool.prepareBoxplotData(data2);
                    var  option = {
                        title: [
                        ],
                        tooltip: {
                            trigger: 'item', //触发类型,数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                            axisPointer: { //指示器类型。
                                type: 'shadow'
                            }
                        },
                        grid: { //直角坐标系网格。
                            //show: true,//default: false
                            left: '10%',
                            right: '10%',
                            bottom: '15%',
                            //borderWidth: 1,
                            //borderColor: '#000',
                        },

                        xAxis: { //X轴
                            type: 'category', //'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
                            //data: data.axisData,
                            data: [''],
                            boundaryGap: true, //类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true，这时候刻度只是作为分隔线，标签和数据点都会在两个刻度之间的带(band)中间。
                            nameGap: 30, //坐标轴名称与轴线之间的距离。
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true, //是否显示分隔区域
                                //interval: 'auto', //坐标轴分隔区域的显示间隔，在类目轴中有效
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: 'black',

                            },
                            splitLine: { //坐标轴在 grid 区域中的分隔线。
                                show: true, //是否显示分隔线。默认数值轴显示，类目轴不显示。
                                lineStyle: { //分隔线样式
                                    type: 'dashed', //分隔线线的类型。
                                },
                            },
                            axisLine: { //坐标轴轴线相关设置。
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头, 默认不显示箭头，即 'none'
                                lineStyle: { //轴线样式
                                    width: 2,
                                    color: 'green',
                                    //opacity: 1, //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                                },
                            },
                            axisTick: { //坐标轴刻度相关设置。
                                show: true, //是否显示坐标轴刻度。
                                //alignWithLabel: true,//类目轴中在 boundaryGap 为 true 的时候有效，可以保证刻度线和标签对齐,default: false

                            }
                        },
                        yAxis: { //y轴
                            type: 'value',
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: '#fff',
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    type: 'dashed'
                                },
                            },
                            axisLine: {
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头
                                lineStyle: {
                                    width: 2,
                                    color: 'green'
                                },
                            },
                        },
                        dataZoom: [
                            {
                                type: 'slider',
                                show: true,
                                yAxisIndex: [0],
                                left: '93%',
                                start: 0, //数据窗口范围的起始百分比
                                end: 36
                            },
                            {
                                type: 'inside',
                                yAxisIndex: [0],
                                start: 0,
                                end: 36
                            }
                        ],
                        series: [
                            {
                                name: 'boxplot',//箱形图
                                type: 'boxplot',
                                //legendHoverLink: true, //是否启用图例 hover 时的联动高亮。
                                //hoverAnimation: false, //是否开启 hover 在 box 上的动画效果。
                                itemStyle: { //盒须图样式。
                                    //color: '#fff', //boxplot图形的颜色。 默认从全局调色盘 option.color 获取颜色
                                    borderColor: 'blue', //boxplot图形的描边颜色。支持的颜色格式同 color，不支持回调函数。
                                },
                                data: data.boxData,
                                tooltip: { //注意：series.tooltip 仅在 tooltip.trigger 为 'item' 时有效。
                                    formatter: function(param) {
                                        /*
                                            第一个参数 param 是 formatter 需要的数据集。 格式如下：
                                            {
                                                //组件类型
                                                componentType: 'series',
                                                // 系列类型
                                                seriesType: string,
                                                // 系列在传入的 option.series 中的 index
                                                seriesIndex: number,
                                                // 系列名称
                                                seriesName: string,
                                                // 数据名，类目名
                                                name: string,
                                                // 数据在传入的 data 数组中的 index
                                                dataIndex: number,
                                                // 处理过的数据项
                                                data: Object | Array,
                                                // 处理过的数据项
                                                value: number | Array,
                                                // 数据图形的颜色
                                                color: string,

                                                // 饼图的百分比
                                                percent: number,

                                            }
                                        */

                                        return [
                                            '值 ' + param.name + ': ',
                                            'lower: ' + param.data[0]
                                        ].join('<br/>')
                                    }
                                }
                            },
                            {
                                name: '异常值',//异常值
                                type: 'scatter',//分散
                                data: data.outliers
                            }
                        ]

                    };
                    echart3.setOption(option);
                }
            }
        });

        $.ajax({
            url: "#springUrl('/edu/analysis/resourcesCount/ikMap')",
            data: {"action": action},
            type: "POST",
            success: function (text) {
                var d = JSON.parse(text);
                if (d.success && d.data) {
                    var echart3 = echarts.init(document.getElementById('echart33'));
                    var data1 = [];
                    for (var i = 0; i < d.data.length; i++) {
                        data1.push(parseInt(d.data[i].order_frequency));
                    }
                    var data2 = [];
                    data2.push(data1);
                    var data = echarts.dataTool.prepareBoxplotData(data2);
                    var  option = {
                        title: [
                        ],
                        tooltip: {
                            trigger: 'item', //触发类型,数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                            axisPointer: { //指示器类型。
                                type: 'shadow'
                            }
                        },
                        grid: { //直角坐标系网格。
                            //show: true,//default: false
                            left: '10%',
                            right: '10%',
                            bottom: '15%',
                            //borderWidth: 1,
                            //borderColor: '#000',
                        },

                        xAxis: { //X轴
                            type: 'category', //'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
                            //data: data.axisData,
                            data: [''],
                            boundaryGap: true, //类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true，这时候刻度只是作为分隔线，标签和数据点都会在两个刻度之间的带(band)中间。
                            nameGap: 30, //坐标轴名称与轴线之间的距离。
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true, //是否显示分隔区域
                                //interval: 'auto', //坐标轴分隔区域的显示间隔，在类目轴中有效
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: 'black',

                            },
                            splitLine: { //坐标轴在 grid 区域中的分隔线。
                                show: true, //是否显示分隔线。默认数值轴显示，类目轴不显示。
                                lineStyle: { //分隔线样式
                                    type: 'dashed', //分隔线线的类型。
                                },
                            },
                            axisLine: { //坐标轴轴线相关设置。
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头, 默认不显示箭头，即 'none'
                                lineStyle: { //轴线样式
                                    width: 2,
                                    color: 'green',
                                    //opacity: 1, //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                                },
                            },
                            axisTick: { //坐标轴刻度相关设置。
                                show: true, //是否显示坐标轴刻度。
                                //alignWithLabel: true,//类目轴中在 boundaryGap 为 true 的时候有效，可以保证刻度线和标签对齐,default: false

                            }
                        },
                        yAxis: { //y轴
                            type: 'value',
                            splitArea: { //坐标轴在 grid 区域中的分隔区域，默认不显示。
                                //show: true
                            },
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                //formatter: 'expr {value}',  // 使用字符串模板，模板变量为刻度默认标签 {value}
                                show: true, //是否显示刻度标签。
                                //interval: 'auto', //坐标轴刻度标签的显示间隔，在类目轴中有效。
                                color: '#fff',
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    type: 'dashed'
                                },
                            },
                            axisLine: {
                                show: true, //是否显示坐标轴轴线。
                                //onZero:false,//X 轴或者 Y 轴的轴线是否在另一个轴的 0 刻度上，只有在另一个轴为数值轴且包含 0 刻度时有效。
                                //symbol:'arrow', //轴线两边的箭头
                                lineStyle: {
                                    width: 2,
                                    color: 'green'
                                },
                            },
                        },
                        dataZoom: [
                            {
                                type: 'slider',
                                show: true,
                                yAxisIndex: [0],
                                left: '93%',
                                start: 0, //数据窗口范围的起始百分比
                                end: 36
                            },
                            {
                                type: 'inside',
                                yAxisIndex: [0],
                                start: 0,
                                end: 36
                            }
                        ],
                        series: [
                            {
                                name: 'boxplot',//箱形图
                                type: 'boxplot',
                                //legendHoverLink: true, //是否启用图例 hover 时的联动高亮。
                                //hoverAnimation: false, //是否开启 hover 在 box 上的动画效果。
                                itemStyle: { //盒须图样式。
                                    //color: '#fff', //boxplot图形的颜色。 默认从全局调色盘 option.color 获取颜色
                                    borderColor: 'blue', //boxplot图形的描边颜色。支持的颜色格式同 color，不支持回调函数。
                                },
                                data: data.boxData,
                                tooltip: { //注意：series.tooltip 仅在 tooltip.trigger 为 'item' 时有效。
                                    formatter: function(param) {
                                        /*
                                            第一个参数 param 是 formatter 需要的数据集。 格式如下：
                                            {
                                                //组件类型
                                                componentType: 'series',
                                                // 系列类型
                                                seriesType: string,
                                                // 系列在传入的 option.series 中的 index
                                                seriesIndex: number,
                                                // 系列名称
                                                seriesName: string,
                                                // 数据名，类目名
                                                name: string,
                                                // 数据在传入的 data 数组中的 index
                                                dataIndex: number,
                                                // 处理过的数据项
                                                data: Object | Array,
                                                // 处理过的数据项
                                                value: number | Array,
                                                // 数据图形的颜色
                                                color: string,

                                                // 饼图的百分比
                                                percent: number,

                                            }
                                        */

                                        return [
                                            '值 ' + param.name + ': ',
                                            'lower: ' + param.data[0]
                                        ].join('<br/>')
                                    }
                                }
                            },
                            {
                                name: '异常值',//异常值
                                type: 'scatter',//分散
                                data: data.outliers
                            }
                        ]

                    };
                    echart3.setOption(option);
                }
            }
        });
    }else {
        var lineData = {
            labels: null,
            datasets: [
                {
                    label: "闲忙程度",
                    backgroundColor: 'rgba(26,179,148,0.5)',
                    borderColor: "rgba(26,179,148,0.7)",
                    pointBackgroundColor: "rgba(26,179,148,1)",
                    pointBorderColor: "#fff",
                    data: null
                }
            ]
        };

        var lineOptions = {
            responsive: true
        };
        var ctx = document.getElementById('Simulation').getContext('2d');
        new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

        var doughnutData = {
            labels: ["休息时间","忙碌时间"],
            datasets: [{
                data: [0,0],
                backgroundColor: ["#a3e1d4","#dedede"]
            }]
        } ;

        var doughnutOptions = {
            responsive: true
        };

        var ctx4 = document.getElementById('Emp_Simulation').getContext('2d');
        new Chart(ctx4, {type: '忙闲程度', data: doughnutData, options:doughnutOptions});

        $("#average_value1").html("");
        $("#min_value1").html("");
        $("#max_value1").html("");
        $("#range1").html("");
        $("#median1").html("");
        $("#mode1").html("");
        $("#kurtosis1").html("");
        $("#skewness1").html("");
        $("#standard_deviation1").html("");
        $("#standard_error1").html("");

        $("#title").html("");
        $("#title1").html("");
        $("#emp_num").html("");
        $("#process_capacity").html("");

    }
}