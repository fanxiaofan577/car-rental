// app.js
import request from './utils/request'
import utils from './utils/util'
wx.$utils = utils
App({
  onLaunch() {
    // axios.get("/order/useDay",{cid:13}).then(r=>{})

  },
  globalData: {
    userInfo: null,
    imgurl:"http://localhost:8085/File/downloadImage?imageName="
  }
})