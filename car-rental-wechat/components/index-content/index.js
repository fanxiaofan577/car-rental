// components/index-content/index.js
const app = getApp()
Component({
    options: {
        styleIsolation: 'shared',
      },
    /**
     * 组件的属性列表
     */
    properties: {
        catId:{
            type:Number,
            value:0
        }
    },
  

    /**
     * 组件的初始数据
     */
    data: {
        id:0,
        pageNum:1,
        pageSize:20,
        reco:[],
        colorSet:[],
        show:false,
        imgUrlPre:getApp().globalData.imgurl,
        carList:[],
        networkErr:false
    },
    attached(){
       
       this.getinfo()
    },
    /**
     * 组件的方法列表
     */
    methods: {
        getinfo(){
            let that = this;
           app.$API.getRoughlyCar({
                'cateId':this.data.catId,
                'pageNum':this.data.pageNum,
                'pageSize':this.data.pageSize
           }).then(resp=>{
               for(var i = 0;i<resp.data.length;i++){
                   resp.data[i].colorSet = JSON.parse(resp.data[i].colorSet)
               }
               that.setData({
                 reco: resp.data   
               })
           }).catch(err => {
            this.setData({
                networkErr:true
            })
          })
        },
        reStart(){
            this.setData({
                networkErr:false
            })
            this.getinfo()
            this.getInfo()
        },
        onClose(){
            this.setData({
                show:false
            })
        },
        getInfo(options){
           let id = options.currentTarget.dataset.id
           let poupTitle = options.currentTarget.dataset.title
            let that = this;
            this.setData({
                show:true,
                poupTitle:poupTitle
            })

            app.$API.getCar({
                'type':1,
                'id':id
            }).then(res=>{
               for(var i = 0;i<res.data.length;i++){
                res.data[i].picUrls=JSON.parse(res.data[i].picUrls.replace(/'/g,"\""))
                }
                that.setData({
                    carList:res.data
                })
            }).catch(err => {
                this.setData({
                    networkErr:true
                })
              })
        },
        gotocarInfo(options){
            let id = options.currentTarget.dataset.bzid
            wx.navigateTo({
              url: '/pages/carInfo/index?id='+id
            })
        }
    }
})
