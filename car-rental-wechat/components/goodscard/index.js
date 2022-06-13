"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var link_1 = require("../../miniprogram_npm/@vant/weapp/mixins/link");
var component_1 = require("../../miniprogram_npm/@vant/weapp/common/component");
(0, component_1.VantComponent)({
    classes: [
        'num-class',
        'desc-class',
        'thumb-class',
        'title-class',
        'price-class',
        'origin-price-class',
    ],
    mixins: [link_1.link],
    props: {
        tag: {
            type: String,
            value: ''
        },
        date: {
            type: String,
            value: ''
        },
        desc:{
            type: String,
            value: ''
        },
        color: {
            type: String,
            value: ''
        },
        exhaustVolume: {
            type: String,
            value: ''
        },
        thumb: {
            type: String,
            value: ''
        },
        title: {
            type: String,
            value: ''
        },
        price: {
            type: Number,
            observer: 'updatePrice',
        },
        centered: Boolean,
        lazyLoad: Boolean,
        thumbLink: {
            type: String,
            value: ''
        },
        originPrice: Number,
        thumbMode: {
            type: String,
            value: 'aspectFit',
        },
        currency: {
            type: String,
            value: '¥',
        },
    },
    methods: {
        updatePrice: function () {
            var price = this.data.price;
            var priceArr = price.toString().split('.');
            this.setData({
                integerStr: priceArr[0],
                decimalStr: priceArr[1] ? ".".concat(priceArr[1]) : '',
            });
        },
        onClickThumb: function () {
            this.jumpLink('thumbLink');
        },
    },
});
