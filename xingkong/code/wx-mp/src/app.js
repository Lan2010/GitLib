//app.js

const conf = require('./config');
const util = require('./utils/util');

App({
  
  data: {
    times: {
      login: 0,       // 登录次数
      getUser: 0,     // 获取后台用户数据次数
      getWxUser: 0    // 获取微信用户数据次数
    } 
  },

  auth: {},

  sys: util.getSys(),


  // 获取当前授权信息
  getAuth: function(type){

    let that = this;
    
    return new Promise(function(resolve,reject){

      wx.getSetting({
        success: (res) => {

          // console.log('wx.getSetting', res);

          let data = {};

          for (let key in res.authSetting){
            data[key.split('.')[1]] = res.authSetting[key]
          }
          
          that.auth = data;

          // console.log('app - auth', res.authSetting);
          resolve();
          
        },
        fail: (res) => {

          console.log('app - wx.getSetting fail', res);
          resolve();
        }
      })
    })
  },

  // Show modal - 控制页面 Modal 组件
  showModal: function(options){

    let pages = getCurrentPages();
    let page = pages[pages.length - 1];

    page.onConfirm = function(){
      options.onConfirm && options.onConfirm();
    }

    page.onCancel = function () {
      options.onCancel && options.onCancel();
    }

    options.show = true;

    page.setData({modal: options});
  },

  // Set msg - 控制页面 Msg 组件
  msg: function(content, options){

    let opts = options ? options : {};
    let pages = getCurrentPages();
    let page = opts.page ? opts.page : pages[pages.length - 1];

    // Clear OT
    if (page.data.msgOT){
      clearTimeout(page.data.msgOT);
    }

    // Update msg data
    page.setData({
      msg: {
        show: true,
        content: content ? content : 'Message'
      } 
    });

    // Set close
    if (opts.time){
      var ot = setTimeout(function () {
        page.setData({ 'msg.show': false });
      }, opts.time != undefined ? opts.time : 3000);

      page.setData({ msgOT: ot });
    }

  },


  // Check API Json
  checkResJson: function (res, isShowMsg, apiPath) {

    let that = this;
    let statusCode = res.statusCode;

    // Http status non-200
    if (statusCode != 200) {

      that.msg(apiPath + ' - ' + statusCode);

      console.log('[API Request fail] - ' + apiPath + ' - ' + statusCode);
      return false;
    }

    let data = res.data;
    let code = data.code;
    let msg = data.msg;
    let isLogin = code != 10302 && code != 10303;

    // Json code non 0
    if (code != 0){

      if (isShowMsg && isLogin){ that.msg(msg) }
      
      console.log('[API Code fail] -' + apiPath + ' - code ' + code + ' - ' + msg);
      return false;
    }

    return true
  },

  // 是否需要重登录 - 未登录 || 超时， 如果是，清空 token
  isRelogin: function(res){

    let that = this;
    let statusCode = res.statusCode;

    // No need relogin - 非200 跳过
    if (statusCode != 200){ return false; }
    
    let loginTimes = that.data.times.login;
    let isNonLogin = res.data.code === 10302 || res.data.code === 10303;

    // Need relogin - code check && login times < 3，
    if (isNonLogin && loginTimes < 3){

      console.log('App - clear toekn and relogin - login times', loginTimes);

      wx.setStorageSync('token', ''); // Clear token
      return true;
    }

    // No need relogin
    return false;
  },

  // API - 是否领取绿卡
  isGetCard: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/user/isGetGC';

    wx.request({
      url: conf.host + uri,
      // method: 'POST',
      data: { token: token },
      header: conf.request.header,
      success: function (res) {

        // console.log('[API success] - ' + uri, res);

        // Json code 0
        if (that.checkResJson(res, true, uri)) {

          fn && fn(res.data.data);
          return;
        }

        // Is need relogin
        if (that.isRelogin(res)){
          console.log('Clear token and Relogin by ' + uri);
          that.checkLogin().then(function () { that.isGetCard(fn); });
        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // that.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));

      }
    })

  },

  // API - 获取后台用户信息
  getUser: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/user/getUser';

    wx.request({
      url: conf.host + uri,
      data: { token: token },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        // Json code 0
        if (that.checkResJson(res, true, uri)) {

          let data = res.data.data;

          // Set storage
          console.log('Set storage - userInfo from back');
          wx.setStorageSync('nickName', data.nickName);
          wx.setStorageSync('avatarUrl', data.avatarUrl);

          that.data.times.getUser += 1;

          fn && fn(res.data.data);
          return;
        }

        // Is need relogin
        if (that.isRelogin(res)) {
          console.log('Clear token and Relogin by ' + uri);
          that.checkLogin().then(function () { that.getUser(fn); });
        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // that.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));

      }
    })

  },

  // 获取微信用户数据 - 主动
  getWxUser: function (fn) {

    let that = this;

    // 判断授权
    if (!that.auth.userInfo) { 

      console.log('获取微信用户数据 - 主动 - 未授权');

      fn && fn(); 
      return; 
    }

    wx.getUserInfo({
      success: function (res) {

        fn && fn(res);

        console.log('获取微信用户数据 - 主动', res.userInfo);

        // 更新获取微信用户数据的次数
        that.data.times.getWxUser += 1;
      },
      fail: function (res) {

        console.log('获取微信用户数据 - 主动 - fail', res);

        fn && fn();
      }
    })
  },

  // API - 更新用户信息
  updateUser: function (data,fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/user/updateUser';

    // wx.uploadFile({
    //   url: conf.host + uri,
    //   filePath: data.avatarUrl,
    //   name: 'audio',
    //   formData: {
    //     token: token, isAuth: 1, nickName: data.nickName, avatarUrl: data.avatarUrl
    //   },
    //   success: function (res) {

    //     console.log('[API success] - ' + uri, res);

    //     if (that.checkResJson(res, true, uri)) {

    //       // Set storage
    //       console.log('Set storage - userInfo from WX');
    //       wx.setStorageSync('nickName', data.nickName);
    //       wx.setStorageSync('avatarUrl', data.avatarUrl);
    //     };

    //     fn && fn();

    //   },
    //   fail: function (res) {

    //     console.log('[API fail] - ' + uri, res);
        
    //     fn && fn();
    //   }
    // })

    wx.request({
      url: conf.host + uri,
      method: 'POST',
      data: { token: token, isAuth: 1, nickName: data.nickName, avatarUrl: data.avatarUrl },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (that.checkResJson(res, true, uri)){

          // Set storage
          console.log('Set storage - userInfo from WX');
          wx.setStorageSync('nickName', data.nickName);
          wx.setStorageSync('avatarUrl', data.avatarUrl);

          fn && fn(true);

          return;
        };

        fn && fn(false);
      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);

        fn && fn(false);
      }
    })
  },

  // API - 后台登录
  backLogin: function(code,fn){
    
    let that = this;
    let uri = '/api/login';

    wx.request({
      url: conf.host + uri,
      data: { code: code },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        // ------------------ Login success

        if (that.checkResJson(res, true, uri)) {

          // Save token
          wx.setStorageSync('token', res.data.data.token);

          // Update login times
          that.data.times.login += 1;

          // Callback
          fn && fn(true);

          return;
        }

        // ------------------ Login fail  
        
        // Callback
        fn && fn(false);    

      },
      fail: function (res) {
        
        console.log('[API fail] - ' + uri, res);
        // that.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));

        that.showModal({
          title: '登录失败，请重试',
          content: JSON.stringify(res)
        });

        // Callback
        fn && fn(false);
      },
      complete: function(){

        wx.hideLoading(); 
      }
    });
  },

  // WX Login
  wxLogin: function(fn){

    let that = this;

    // wx.showLoading({ title: '加载中', mask: true});

    // WX Login
    wx.login({
      success: function(res){

        console.log('wx.login success',res);

        // ------------------ wx.login success
        if (res.code){
          that.backLogin(res.code,fn);
          return ;
        }
        
        // ------------------ wx.login fail

        // Hide loading
        wx.hideLoading();

        // Show Modal
        that.showModal({
          title: '微信登录失败，请重试',
          content: JSON.stringify(res)
        });

        // Callback
        fn && fn(false);
        
      },
      fail: function(res){
        
        console.log('wx.login fail', res);

        // ------------------ wx.login fail

        // Hide loading
        wx.hideLoading();

        // Show Modal
        that.showModal({
          title: '请求微信登录失败',
          content: JSON.stringify(res)
        });

        // Callback
        fn && fn(false);
      }
    })

  },

  // Check login
  checkLogin: function(){

    let that = this;

    return new Promise(function (resolve, reject){

      let token = wx.getStorageSync('token');
      let isCheckSession = that.data.isCheckSession;

      // Has token && Already checkSession
      if (token && isCheckSession){

        console.log('app - checkLogin - token && Already checkSession');

        resolve();
        return;
      }

      // Has token, check session_key
      if (token && !isCheckSession){

        console.log('app - checkLogin - token && Never checkSession');

        wx.checkSession({
          success: function(){

            // console.log('app - wx.checkSession success');

            resolve();
          },
          fail: function(){

            console.log('app - wx.checkSession fail');

            // session_key fail, login again
            that.wxLogin(function (res) {
              
              if (res){
                resolve();
              }
              else {
                reject();
              }

            });
          }
        })

        that.data.isCheckSession = true;
        
        return;
      }

      // No token
      if (!token){

        console.log('app - checkLogin - No token, login');

        that.wxLogin(function(res){
          
          if (res) {
            resolve();
          }
          else {
            reject();
          }

        });
      }

    });

  },

  onLaunch: function (e) {

    let that = this;

    // console.log('App onLaunch',e);
    // console.log('app - sys', that.sys); 
  },

  onShow: function(e){

    let that = this;
    
    // console.log('App onShow',e);

    that.scene = e.scene;
    that.launchPath = e.path;

    console.log('App onShow',that);
  }
  
})