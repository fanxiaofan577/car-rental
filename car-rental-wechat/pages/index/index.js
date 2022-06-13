// pages/index/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        category:[],
        show:false,
		networkErr:false
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
       this.getCategory()
    },
	getCategory(){
		let that = this;
		this.$API.getCategory().then(
		    res=>{
		        that.setData({
		            category:res.data
		        })
		    }
		).catch(err=>{
			that.setData({
				networkErr:true
			})
		})
	},
	reStart(){
		this.setData({
			networkErr:false
		})
		this.getCategory()
	}

})