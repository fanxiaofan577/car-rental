Page({
    data: {
      time: 30 * 60 * 60 * 1000,
      timeData: {},
      networkErr:false,
      imgUrlPre:getApp().globalData.imgurl,
      steps: [
        {
          text: '预约'
        },
        {
          text: '确认档期'
        },
        {
          text: '取车'
        },
        {
          text: '用车'
        },
        {
          text: '还车' 
        },
        {
          text: '退押金'
        },
      ],
    },
    onChange(e) {
      this.setData({
        timeData: e.detail,
      });
    },
	onLoad(options){
    let id = options.id
		this.getOrder(id).then(res=>{
      let bookingTimeTmp =res.data[0].bookingTime*1000
      res.data[0].useDay=this.formatDateYMDW(bookingTimeTmp)
      res.data[0].reDay=this.formatDateYMDW(bookingTimeTmp+1000*60*60*24*res.data[0].rentDays)
      res.data[0].bookingTime=this.formatDateYMD(bookingTimeTmp);
      this.formatStatus(res.data)
      this.setData({
        order:res.data[0]
      })
      this.getCarInfo(res.data[0].cid).then(res=>{
        this.formatCar(res.data)
        this.setData({
          car:res.data[0]
        })
      })
    })
    
  },
  getCarInfo(cid){
    return this.$API.getCar({
      type:2,
      id:cid
    }).catch(err=>{
			this.setData({
				networkErr:true
			})
		})
  },
  getOrder(oid){
    return this.$API.getOrder({
      id:oid
    }).catch(err=>{
			this.setData({
				networkErr:true
			})
		})
  },
  formatDateYMD(date) {
    date = new Date(date);

    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
  },
  formatDateYMDW(date) {
    date = new Date(date);
    let changeNum = ['零', '一', '二', '三', '四', '五', '六', '天']
    return `${date.getFullYear()}年${date.getMonth() + 1}月${date.getDate()}日 周${changeNum[date.getDay()]}`;
  },
  formatStatus(datas) {
    for (var i = 0; i < datas.length; i++) {
      if (datas[i].status == 0) {
        datas[i].statusStr = "已预约"
      }
      if (datas[i].status == 1) {
        datas[i].statusStr = "已确认档期"
      }
      if (datas[i].status == 2) {
        datas[i].statusStr = "使用中"
      }
      if (datas[i].status == 3) {
        datas[i].statusStr = "已完成"
      }
      if (datas[i].status == 4) {
        datas[i].statusStr = "已取消"
      }
    }
  },
  formatCar(datas) {
    for (var i = 0; i < datas.length; i++) {
      datas[0].picUrls = JSON.parse(datas[0].picUrls.replace(/'/g, "\""))
    }
  },
  });