import axios from '../libs/wxAxios.min';

const page = Page
const com = Component
const app = App

axios.defaults.baseURL = "http://localhost:8085";
axios.defaults.header = {
	'content-type': 'application/x-www-form-urlencoded'
}
axios.interceptors.request.use(function (config) {
	wx.showLoading({
		title: '加载中',
	})
	console.log('监听1：' + wx.getStorageSync('Authorization'))
	if (wx.getStorageSync('Authorization')) {
		config.header['Authorization'] = wx.getStorageSync('Authorization')
	} else {
		delete config.header.Authorization
	}
	return config
})

axios.interceptors.response.use(success => {
	if(success.header['Authorization']){
		wx.setStorageSync('Authorization',success.header['Authorization'])
	}
	wx.hideLoading()
	if (success.statusCode == 500) {
		let status = success.data.status;
		if (status == 40301) {
			wx.removeStorageSync('Authorization');//将token置空
			wx.removeStorageSync('userinfo');
			wx.reLaunch({
				url: '/pages/user/index',
				complete: resp => {
					wx.showToast({
						title: '账户异常',
						icon: 'error',
						duration: 2000
					})
				}
			})
		} else if (status == 40001) {
			wx.showToast({
				title: '请求错误',
				icon: 'error',
				duration: 2000
			})
		} else if (status == 40302 || status == 40304 || status == 40305) {
			wx.removeStorageSync('Authorization');//将token置空
			wx.removeStorageSync('userinfo');
			wx.reLaunch({

				url: '/pages/user/index',
				complete: resp => {
					wx.showToast({
						title: '账户异常',
						icon: 'error',
						duration: 2000
					})
				}
			})
		} else if (status == 50000 || status == 50002 || status == 50003 || status == 50003) {
			wx.showToast({
				title: '服务器异常',
				icon: 'error',
				duration: 2000
			})
		}else{
			wx.showToast({
				title: '服务器异常',
				icon: 'error',
				duration: 2000
			})
		}
	} else if (success.statusCode == 200) {
		success.data = success.data.data
	}

	return success
}, error => {
	wx.hideLoading()
	wx.showToast({
		title: '网络异常',
		icon: 'error',
		duration: 2000
	})
	return Promise.reject(error)
})
export const postKeyValueRequest = (url, params = {}) => {
	return axios({
		method: 'post',
		url: `${url}`,
		data: params,
		transformRequest: [function (data) {
			// console.log(data)
			let ret = '';
			for (let i in data) {
				ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
			}
			return ret;
		}],
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		}
	})
}
const postRequest = (url, params = {}) => {
	return axios({
		method: 'post',
		url: `${url}`,
		data: params,
	})
}

const putRequest = (url, params = {}) => {
	return axios({
		method: 'put',
		url: `${url}`,
		data: params,
	})
}

const getRequest = (url, params = {}) => {
	return axios({
		method: 'get',
		url: `${url}`,
		data: params
	})
}
const deleteRequest = (url, params = {}) => {
	return axios({
		method: 'delete',
		url: `${url}`,
		data: params
	})
}

const API = {
	login(data) {
		console.log(data)
		return postKeyValueRequest('/login', data)
	},
	getImage(data) {
		return getRequest('/File/downloadImage', data)
	},
	getCategory(data) {
		return getRequest('/category/', data)
	},
	getRoughlyCar(data) {
		return getRequest('/roughlyCar/', data)
	},
	deleteOrder(data) {
		return deleteRequest('', data)
	},
	getCar(data) {
		return getRequest('/Car/', data)
	},
	setOrder(data) {
		return putRequest('/order/', data)
	},
	getUseDay(data) {
		return getRequest('/order/useDay', data)
	},
	getOrder(data) {
		return getRequest('/order/', data)
	},
	cancelOrder(data) {
		return postRequest('/order/cancel', data)
	}
}
module.exports = {
	postRequest,
	putRequest,
	deleteRequest,
	getRequest,
	API
}
App = function (_option) {
	_option.$API = API
	_option.$putRequest = putRequest
	_option.$deleteRequest = deleteRequest
	_option.$getRequest = getRequest
	app(_option)
}
Page = function (_option) {
	_option.$API = API
	_option.$putRequest = putRequest
	_option.$deleteRequest = deleteRequest
	_option.$getRequest = getRequest
	page(_option)
}
Component = function (_option) {
	_option.$API = API
	_option.$putRequest = putRequest
	_option.$deleteRequest = deleteRequest
	_option.$getRequest = getRequest
	com(_option)
}