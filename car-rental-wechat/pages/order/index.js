Page({
  data: {
    active: "-1",
    carinfo: [],
    loading: true,
    err: false
  },

  onChange(event) {
    let that = this
    let name = Number(event.detail.name)
    if (name == -1) {
      this.$API.getOrder().then(res => {
        this.formatter(res.data)
        this.setData({
          orders: res.data
        })
      }).catch(err => {
        this.setData({
          loading: false,
          err: true
        })
      })
    } else {
      this.setData({
        loading: true
      })
      this.$API.getOrder({
        status: name
      }).then(res => {
        this.formatter(res.data)
        this.setData({
          active:name,
          orders: res.data,
          loading: false
        })
      }).catch(res => {
        this.setData({
          loading: false,
          err: true
        })
      })
    }
  },
  onLoad: function (options) {
    let that = this
    let active = options.active
    this.setData({
      active: active
    })
    if (active == "-1") {
      this.$API.getOrder({

      }).then(
        res => {
          this.formatter(res.data)
          let order = res.data
          this.setData({
            orders: order
          })
        }
      )
    } else {
      this.$API.getOrder({
        status: that.data.active
      }).then(
        res => {
          this.formatter(res.data)
          let order = res.data
          this.setData({
            active:active,
            orders: order
          })
        }
      )
    }
  },
  formatter(datas) {
    for (var i = 0; i < datas.length; i++) {
      if (datas[i].status == 0) {
        datas[i].statusStr = "预约中"
      }
      if (datas[i].status == 1) {
        datas[i].statusStr = "租赁中"
      }
      if (datas[i].status == 2) {
        datas[i].statusStr = "待评价"
      }
      if (datas[i].status == 3) {
        datas[i].statusStr = "已完成"
      }
      if (datas[i].status == 4) {
        datas[i].statusStr = "已取消"
      }
    }
  },
  goToDetails(event){
    console.log(event)
    let id = event.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/order_details/index?id='+id,
    })
  },
  refush(){
    var that = this;
    this.$API.getOrder({
      status: that.data.active
    }).then(
      res => {
        this.formatter(res.data)
        let order = res.data
        this.setData({
          orders: order
        })
      }
    )
  }
});