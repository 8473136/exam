function request(params){
	let request_url = 'http://127.0.0.1:9091/'
	let url = request_url + params.model
	let method = params.method
	let header = params.header || {}
	let data = params.data || {}
	let callBack = params.callBack
	
	if (method) {
		method = method.toUpperCase()
		if (method == 'POST') {
			header = {
				// 'content-type': 'application/x-www-form-urlencoded'
				'content-type': "application/json"
			}
		} else {
			header = {
				'content-type': "application/json"
			};
		}
	} else {
		method = "GET";
		header = {
			'content-type': "application/json"
		};
	}
	let loading = uni.showLoading({
		title: '加载中...',
		mask: true
	})
	uni.request({
		url: url,
		data: data,
		method: method,
		header: header,
		success: res => {
			uni.hideLoading()
			if (res.data.code == 200) {
				callBack(res.data.data);
			}
			
		},
		fail: (e) => {
			uni.hideLoading()
			console.log("网络请求fail:" + JSON.stringify(e));
			uni.showModal({
				content: "" + e.errMsg
			});
			// typeof param.fail == "function" && param.fail(e.data);
			
		},
		complete: () => {
			//console.log("网络请求complete");
			// uni.hideLoading();
			// typeof param.complete == "function" && param.complete();
			//return;
		}
	})
}
function get(model,data,callBack){
	request({
		model: model,
		data: data,
		callBack: callBack,
		method: 'GET'
	})
}
function post(model,data,callBack){
	request({
		model: model,
		data: data,
		callBack: callBack,
		method: 'POST'
	})
}
module.exports = {
		get: get,
		post: post
}
