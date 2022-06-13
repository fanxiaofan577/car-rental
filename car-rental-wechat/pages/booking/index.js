import Dialog from '@vant/weapp/dialog/dialog';
Page({
  data: {
    timeNums: [],
    car_id: '',
    car_name: '',
    car_licensePlate: '',
    car_color: '',
    car_plateYear: '',
    cardIDMessage: '',
    phoneMessage: '',
    nameMessage: '',
    useDay: [],
    date: '',
    days: '',
    bookingTime:'',
    day_num:'',
    username: '',
    phone: '',
    card_id: '',
    currentDate: new Date().getTime(),
    minDate: new Date().getTime(),
    show: false,
    formatter(day) {
      const month = day.date.getMonth() + 1;
      const date = day.date.getDate();
      if (month === 5) {
        if (date === 1) {
          day.topInfo = '已预约';
        } else if (date === 4) {
          day.topInfo = '已预约';
        }
      }
      if (day.type === 'start') {
        day.bottomInfo = '借车';
      } else if (day.type === 'end') {
        day.bottomInfo = '还车';
      }

      return day;
    },
  },
  onSelect(value) {
    if (value.type == "select" && value.detail[1]) {
      let start = Date.parse(value.detail[0]);
      let end = Date.parse(value.detail[1])
      let timenum = this.data.timeNums
      for (var i = 0; i < timenum.length; i++) {
        if (start <= timenum[i] && timenum[i] <= end) {
          this.selectComponent("#calendar").reset()
        }
      }
    }
  },
  onDisplay() {
    this.setData({
      show: true,
    });
  },
  onClose() {
    this.setData({
      show: false
    });
  },
  formatDate(date) {
    date = new Date(date);
    return `${date.getFullYear()}年${date.getMonth() + 1}月${date.getDate()}日`;
  },
  onConfirm(date) {
    let end =Date.parse(date.detail[1])
    let start =Date.parse(date.detail[0])
    let daynum=(end-start)/86400000;
    this.setData({
      bookingTime:start,
      day_num:daynum,
      show: false,
      date: this.formatDate(start)+"-"+this.formatDate(end),
    });
  },
  onInput(event) {
    this.setData({
      currentDate: event.detail,
      show: false
    });
    this.formatDate(event.detail)
  },
  cancel() {
    this.setData({
      show: false
    })
  },
  cardIDrequired(event) {
    let cardID = event.detail.value
    if (cardID == '' || cardID == null) {
      this.setData({
        cardIDMessage: ''
      })
    } else if (!wx.$utils.IdentityCodeValid(cardID)) {
      this.setData({
        cardIDMessage: '身份证号码有误！'
      })
    } else {
      this.setData({
        cardIDMessage: '',
        card_id: cardID

      })
    }
  },
  phonerequired(event) {
    let phoneNum = event.detail.value
    if (!(/^1[3456789]\d{9}$/.test(phoneNum))) {
      this.setData({
        phoneMessage: "请输入正确的手机号码"
      })
    } else {
      this.setData({
        phoneMessage: "",
        phone: phoneNum,
      })
    }
  },
  namerequired(event) {
    let name = event.detail.value
    if ((/[^\u4e00-\u9fa5]/.test(name))) {
      this.setData({
        nameMessage: "请输入正确的姓名"
      })
    } else {
      this.setData({
        nameMessage: "",
        username: name
      })
    }
  },
  onLoad(options) {
    let that = this
    that.setData({
      car_name: options.car_name,
      car_licensePlate: options.car_licensePlate,
      car_color: options.car_color,
      car_plateYear: options.car_plateYear,
      car_id: options.car_id
    })
    this.$API.getUseDay( {
      cid: options.car_id
    }).then(res => {
      console.log(res)
      let dates = res.data
      var days = [];
      for (var i = 0; i < dates.length; i++) {
        for (var j = 0; j < dates[i].rentDays; j++) {
          let nums = (dates[i].bookingTime + (86400 * j)) * 1000
          let date = new Date(nums)
          this.setData({
            timeNums: this.data.timeNums.concat(nums)
          })
          let flagMonth = date.getMonth() + 1;
          let flagDay = date.getDate()
          let day = {
            'month': flagMonth,
            'day': flagDay
          }
          days.push(day);
        }
      }
      console.log(days)
      this.setData({
        days: days,
        formatter: day => {
          const month = day.date.getMonth() + 1;
          const date = day.date.getDate();

          for (var i = 0; i < days.length; i++) {

            if (month === days[i].month) {
              if (date === days[i].day) {
                day.topInfo = '已预约';
              }
            }
          }

          if (day.type === 'start') {
            day.bottomInfo = '借车';
          } else if (day.type === 'end') {
            day.bottomInfo = '还车';
          }

          return day;
        }
      })
    })


  },
  onClickButton() {
    if (this.data.username == "" || this.data.nameMessage != "") {
      Dialog.alert({
        title: '用户名格式错误',
        message: '请填写正确的姓名',
        theme: 'round-button'
      }).then(() => {
        // on close
      });
    } else if (this.data.phone == "" || this.data.phoneMessage != "") {
      Dialog.alert({
        title: '手机号码格式错误',
        message: '请填写正确的手机号码',
        theme: 'round-button'
      }).then(() => {
        // on close
      });
    } else if (this.data.card_id == "" || this.data.cardIDMessage != "") {
      console.log(this.data.card_id)
      Dialog.alert({
        title: '身份证号码格式错误',
        message: '请填写正确的身份证号码',
        theme: 'round-button'
      }).then(() => {
        // on close
      });
    } else if (this.data.date == "") {
      Dialog.alert({
        title: '未选择用车时间',
        message: '请选择用车时间',
        theme: 'round-button'
      }).then(() => {
        // on close
      });
    } else {
      let cid = this.data.car_id;
      let bookingTime = this.data.bookingTime;
      let rentDays = this.data.day_num;
      let getPreson = this.data.username;
      let cardId = this.data.card_id
      let phone = this.data.phone
      this.$API.setOrder( {
        cid: cid,
        bookingTime: bookingTime,
        rentDays: rentDays,
        getPreson: getPreson,
        cardId: cardId,
        phone: phone
      }).then(res=>{
        if(res.data){
			wx.navigateTo({
			  url: '/pages/order_details/index?id='+res.data,
			  complete:res=>{
				wx.showToast({
					title: '提交成功',
					icon: 'success',
					duration: 2000
				})  
			  }
			})
		}else{
			wx.showToast({
				title: '提交失败',
				icon: 'error',
				duration: 2000
			})
		}
      })
    }
  }
});