Component({
    options: {
        styleIsolation: 'shared',
      },
    properties: {
        image: {
            type: String,
            value: ''
        },
        title: {
            type: String,
            value: ''
        },
        price: Number,
        color: Object,
        type: {
            type: String,
            value: 'default'
        },
        colorcount:Number,
        licensePlate:{
            type: String,
            value: ''
        },
        plateYear:{
            type: String,
            value: ''
        },

    },
    data: {
        imgUrlPre:getApp().globalData.imgurl,

    },
    methods: {}
});