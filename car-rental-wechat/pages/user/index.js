// pages/user/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userInfo:[]

    },

    getUserProfile(){
        let that=this;
        wx.removeStorageSync('Authorization');//将token置空
        wx.removeStorageSync('userinfo');
        wx.getUserProfile({
          desc: '展示用户信息',
          success:response=>{
              console.log(response)
              wx.login({
                success:res=>{
                    this.$API.login({
                        code:res.code,
                        rawData:response.rawData,
                        signature:response.signature
                    }).then(function(res){
                        wx.setStorageSync('Authorization', res.data.Authorization)
                        wx.setStorageSync('userinfo',response.userInfo)
                        that.setData({
                            userInfo:response.userInfo,
                        })
                    }).catch(error=>{
                  
                    })
                }
              })
          }
        })
    },




    loginOut(e){
        let that=this;
        wx.showModal({
          title: '提示',
          content: '您确定要退出登录吗',
          success: function (res) {
            if (res.confirm) {//这里是点击了确定以后
              console.log('用户点击确定')
              wx.removeStorageSync('Authorization');//将token置空
              wx.removeStorageSync('userinfo');
                that.setData({
                    userInfo:[]
                })
            } else {//这里是点击了取消以后
              console.log('用户点击取消')
            }
          }
        })
    },
    goto_order(){
      wx.navigateTo({
        url: '/pages/order/index?active=-1',
      })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})