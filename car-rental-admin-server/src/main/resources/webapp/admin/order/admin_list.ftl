<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>${siteName!""}|订单管理-${title!""}</title>
    <#include "../common/header.ftl"/>
    <style>
        td {
            vertical-align: middle;
        }
    </style>
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <aside class="lyear-layout-sidebar">
            <!-- logo -->
            <div id="logo" class="sidebar-header">
                <a href="index.html"><h3 style="display: block;margin-top: 20px">跑跑租车管理系统</h3></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <#include "../common/left-menu.ftl"/>
            </div>

        </aside>
        <!--End 左侧导航-->

        <#include "../common/header-menu.ftl"/>

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clearfix">
                                <form class="pull-right search-bar" method="get" action="list" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button style="position: absolute;left: -290px;top: 0px; width: 110px"
                                                    class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                订单号 <#--<span class="caret"></span>-->
                                            </button>
                                            <ul style="position: absolute;left: -290px;top: 38px;"
                                                class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)"
                                                       data-field="title">订单号</a></li>
                                            </ul>
                                        </div>
                                        <input style="position: absolute;left: -180px;top: 0px; width:166px" type="text"
                                               class="form-control" value="${serialNumber!""}" name="serialNumber"
                                               placeholder="请输入订单号">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                手机号 <#--<span class="caret"></span>-->
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)"
                                                       data-field="title">手机号</a></li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control js-datetimepicker"
                                               name="phone" placeholder="请输入下单手机号"
                                               data-side-by-side="true" data-locale="zh-cn" data-format="YYYY-MM-DD">
                                        <span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">搜索</button>
                                            <div id="qrCode"></div>
                    </span>
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">

                                </div>
                                <#include "../common/third-menu.ftl"/>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>订单号</th>
                                            <th>汽车型号</th>
                                            <th>车牌号</th>
                                            <th>下单人</th>
                                            <th>用车人</th>
                                            <th>用车身份证号</th>
                                            <th>手机号</th>
                                            <th>租用天数</th>
                                            <th>租用价格</th>
                                            <th>状态</th>
                                            <th>预约时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as order>
                                                <tr>

                                                    <td style="vertical-align:middle;">${order.serialNumber?c}</td>
                                                    <td style="vertical-align:middle;">${order.car.name}</td>
                                                    <td style="vertical-align:middle;">${order.car.licensePlate}</td>
                                                    <td style="vertical-align:middle;">${order.account.nickName}</td>
                                                    <td style="vertical-align:middle;">${order.getPreson}</td>
                                                    <td style="vertical-align:middle;">${order.cardId}</td>
                                                    <td style="vertical-align:middle;">${order.phone}</td>
                                                    <td style="vertical-align:middle;">${order.rentDays}</td>
                                                    <td style="vertical-align:middle;" class="text-danger">
                                                        ￥${order.relPay}</td>
                                                    <td style="vertical-align:middle;">
                                                        <#if order.status == 0>
                                                            <font class="text-success">预约中</font>
                                                        <#elseif order.status == 1>
                                                            <font class="text-success">租赁中</font>
                                                        <#elseif order.status == 2>
                                                            <font class="text-success">已还车</font>
                                                        <#elseif order.status == 3>
                                                            <font class="text-success">已评价</font>
                                                        </#if>
                                                    </td>
                                                    <td style="vertical-align:middle;" style="vertical-align:middle;">
                                                        <font class="text-success">${(order.bookingTime*1000)?number_to_datetime?string('yyyy-MM-dd')}</font>
                                                    </td>
                                                    <#--                                                    <td style="vertical-align:middle;" style="vertical-align:middle;">-->
                                                    <#--                                                        <font class="text-success">${order.createTime?string('yyyy-MM-dd HH:mm:ss')}</font>-->
                                                    <#--                                                    </td>-->
                                                    <td style="vertical-align:middle;">

                                                        <#if order.status == 0>
                                                            <div class="btn-group">
                                                                <button class="btn btn-success" data-toggle="tooltip"
                                                                        data-placement="top" title="出租"
                                                                        onclick="pay(${order.id})">租
                                                                </button>
                                                                <button class="btn btn-yellow" data-toggle="tooltip"
                                                                        data-placement="top" title="取消">消
                                                                </button>
                                                            </div>
                                                        <#elseif order.status == 1>
                                                            <div class="btn-group">
                                                                <button class="btn btn-danger" data-toggle="tooltip"
                                                                        data-placement="top" title="还车" onclick="backCar(${order.id})">还
                                                                </button>
                                                            </div>
                                                        <#elseif order.status == 2>
                                                            <div class="btn-group">
                                                                <button class="btn btn-secondary" data-toggle="tooltip"
                                                                        data-placement="top" title="取消">完成
                                                                </button>
                                                            </div>
                                                        <#elseif order.status == 3>
                                                            <div class="btn-group">
                                                                <button class="btn btn-success" data-toggle="tooltip"
                                                                        data-placement="top" title="查看评价">查看评价
                                                                </button>
                                                            </div>
                                                        </#if>
                                                    </td>
                                                </tr>
                                            </#list>
                                        <#else>
                                            <tr align="center">
                                                <td colspan="9">这里空空如也！</td>
                                            </tr>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>
                                <#if pageBean.total gt 0>
                                    <ul class="pagination ">
                                        <#if pageBean.pageNum == 1>
                                            <li class="disabled"><span>«</span></li>
                                        <#else>
                                            <li><a href="list?username=${username!""}&pageNum=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.pageNum == showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li>
                                                    <a href="list?username=${username!""}&pageNum=${showPage}">${showPage}</a>
                                                </li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.pageNum == pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li>
                                                <a href="list?username=${username!""}&pageNum=${pageBean.totalPage}">»</a>
                                            </li>
                                        </#if>
                                        <li><span>共${pageBean.totalPage}页,${pageBean.total}条数据</span></li>
                                    </ul>
                                </#if>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript" src="/admin/js/jquery.qrcode.js"></script>
<script type="text/javascript">

    function pay(id) {
        $.confirm({
            title: '请支付',
            content: '<div id="qrCode" style="margin:0 auto;text-align:center"></div>',
            buttons: {
                formSubmit: {
                    text: '支付成功？',
                    btnClass: 'btn-blue',
                    action: function () {
                        location.reload()
                    }
                },
                cancel: {
                    text: '取消'
                },
            },
            onContentReady: function () {
                var content = this;
                payReq(id, content);
            }
        });
    }

    function payReq(id, content) {
        $.ajax({
            url: "/alipay/qrCodePay",
            method: "post",
            data: {
                id: id
            },
            success: function (response) {
                console.log(response)
                var codeFigure = new AraleQRCode({
                    "render": "svg", // 生成的类型 'svg' or 'table'
                    "text": response, // 需要生成二维码的链接
                    "size": 160 // 生成二维码大小
                });

                content.$content.find("#qrCode").prepend(codeFigure)
                if ("WebSocket" in window) {
                    // 打开一个 web socket
                    // 通道地址按照项目进行配置
                    var ws = new WebSocket("ws://localhost:8080/bindingRecord");
                    ws.onopen = function () {
                    };
                    ws.onmessage = function (evt) {
                        var received_msg = evt.data;
                        if (Boolean(evt.data)) {

                            alert("支付成功")
                            location.reload()
                        }
                        ws.close();
                    };
                    ws.onclose = function () {

                    };
                } else {
                    // 浏览器不支持 WebSocket
                    alert("您的浏览器不支持 WebSocket!");
                }
            }

        })
    }

    function backCar(id){
        $.confirm({
            title: '确认还车',
            content: '确认车辆状况良好！',
            buttons: {
                formSubmit: {
                    text: '确定',
                    btnClass: 'btn-blue',
                    action: function () {
                        backCarReq(id)
                    }
                },
                cancel: {
                    text: '取消'
                },
            },
        });
    }
    function backCarReq(id){
        $.ajax({
            url: "/admin/order/backCar",
            method: "post",
            dataType:"json",
            data: {
                id: id
            },
            success:function (resp) {
                alert(resp.msg)
                location.reload()
            }
        });
    }
</script>

</body>
</html>