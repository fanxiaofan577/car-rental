// pages/carInfo/index.js
import deviceUtil from "../../miniprogram_npm/lin-ui/utils/device-util"
Page({

    /**
     * 页面的初始数据
     */
    data: {
        autoplay: true,
        interval: 5000,
        duration: 1000,
        id: 0,
        carinfo: [],
        imgUrlPre:getApp().globalData.imgurl,
        networkErr: false
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.setData({
            id: Number(options.id)
        })
        this.getCar()
           
    },
    onClickButton() {
        let car_id = this.data.carinfo.id
        let car_name = this.data.carinfo.name
        let car_licensePlate = this.data.carinfo.licensePlate
        let car_colo = JSON.parse(this.data.carinfo.configuration.replace(/'/g, "\""))
        let car_color = car_colo['color']
        let car_plateYear = this.data.carinfo.plateYear
        wx.navigateTo({
            url: '/pages/booking/index?car_name=' + car_name + '&car_licensePlate=' + car_licensePlate + '&car_color=' + car_color + '&car_plateYear=' + car_plateYear + '&car_id=' + car_id,
        })
    },
    getNavigationBarHeight() {
        const capsuleBarHeight = deviceUtil.getNavigationBarHeight()
        console.log(`CapsuleBar 的高度为${capsuleBarHeight}rpx`)
    },
    getCar(){
        let that = this
        this.$API.getCar({
            'type': 2,
            'id': this.data.id
        }).then(
            res => {
                res.data[0].picUrls = JSON.parse(res.data[0].picUrls.replace(/'/g, "\""))
                that.setData({
                    carinfo: res.data[0]
                })
            }
        ).catch(err => {
            that.setData({
                networkErr: true
            })
        })
    },
    reStart() {
        this.setData({
            networkErr: false
        })
        this.getCar()
    }

})