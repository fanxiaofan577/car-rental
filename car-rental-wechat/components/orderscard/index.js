// components/orderscard/index.js
const app = getApp();
Component({
    /**
     * 组件的属性列表
     */
    properties: {
        serial_number: Number,
        cid: Number,
        originalPrice: Number,
        relPay: Number,
        statusStr: {
            type: String,
            value: ''
        },
        createDate: {
            type: String,
            value: ''
        },
        oid:Number
    },

    /**
     * 组件的初始数据
     */
    data: {
        car: '',
        imgUrlPre:getApp().globalData.imgurl,
    },

    /**
     * 组件的方法列表
     */
    methods: {
        goToDetails() {
            let id = this.data.oid;
            wx.navigateTo({
                url: '/pages/order_details/index?id=' + id,
            })
        },
        cancelOrder(){
            let id = this.data.oid;
            let that = this;
            app.$API.cancelOrder({id:id}).then(
                res=>{
                    that.triggerEvent("transfer")
                    wx.showToast({
                        title: '取消成功',
                        icon: 'success',
                        duration: 2000
                    })
                }
            )
        },
        getOrder(){
            let that = this
        let cid = this.data.cid;
        app.$API.getCar({
            'type': 2,
            'id': cid
        }).then(
            res => {
                res.data[0].picUrls = JSON.parse(res.data[0].picUrls.replace(/'/g, "\""))
                res.data[0].configuration = JSON.parse(res.data[0].configuration.replace(/'/g, "\""))
                that.setData({
                    car: res.data[0]
                })
            }
        )
        }
    },
    attached() {
        this.getOrder()
    }


})
