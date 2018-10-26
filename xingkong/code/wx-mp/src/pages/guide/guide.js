// pages/guide/guide.js

const conf = require('../../config');
const ImgLoader = require('../../utils/imgLoader.js');
const app = getApp();

Page({

  data: {

    conf: conf,
    sys: app.sys,

    nav: {
      title: '天智星',
      style: 'black',
      bg: '',
      border: false,
      height: app.sys.barHeight
    },

    loading: false,   // Data loading , lock screen

    page: {
      loading: true,
      ready: false,
      mask: true
    },

    guide: {
      show: false,
      finish: false,
      changing: false,
      active: 0,
      bg: ['','',''],
      bgList: [
        '' + conf.host + '/static/images/guide/' + (app.sys.isIOSX ? 'guide-iosX-1.png' : 'guide-1.png')+'',
        '' + conf.host + '/static/images/guide/' + (app.sys.isIOSX ? 'guide-iosX-2.png' : 'guide-2.png') +'',
        '' + conf.host + '/static/images/guide/' + (app.sys.isIOSX ? 'guide-iosX-3.png' : 'guide-3.png') +''
      ]
    },

    card: {
      show: false,
      bg: ['' + conf.host + '/static/images/page-card-bg.png']
    }

  },


  // API - 领取绿卡
  getECard: function(data,fn){

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/greencard/getGreencard';

    wx.request({
      url: conf.host + uri,
      data: { token: token, gcName: data.nickName, gcHeadImg: data.avatarUrl },
      header: conf.request.header,
      success: function (res) {

        // console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          let data = res.data.data;

          fn && fn(data);
        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      },
      complete: function(){
        wx.hideLoading();
        that.setData({ loading: false });
      }
    }) 
  },

  onGuideChange: function(e){
    
    let that = this;
    let current = e.detail.current;
    let changing = that.data.guide.changing;

    console.log('onGuideChange', changing);

    // 兼容 - 1.9.0 以下不支持 bindanimationfinish
    if (that.data.sys.SDKVer <= 10900){

      that.setData({
        'guide.active': current
      })
      return;
    }

    if (changing){ return; }

    that.setData({
      'guide.changing': true,
      'guide.active': current
    })
  },

  onGuideFinishChange: function(e){
    
    let that = this;

    console.log('onGuideFinishChange');

    that.setData({
      'guide.changing': false
    })
  },

  onTapGuide: function(e){

    let that = this;
    let current = that.data.guide.active;
    let changing = that.data.guide.changing;

    if (!changing && current < 2 ){
      that.setData({
        'guide.active': current + 1
      })
    }
  },

  onGetUserInfo: function (e) {

    let that = this;
    let res = e.detail;

    console.log('获取微信用户信息 - 被动', res);

    if (res.errMsg == 'getUserInfo:ok') {

      if (that.data.loading){ return; }

      that.setData({loading: true});

      // 更新授权到全局
      app.auth.userInfo = true;

      wx.showLoading({
        title: '领取中',
        mask: true
      });

      app.updateUser(res.userInfo, function(r){

        if (!r){
          wx.hideLoading();
          that.setData({ loading: false });
          return;
        }

        that.getECard(res.userInfo, function(data){

            wx.redirectTo({
              url: '/pages/index/index',
            })
        });
      });

    }
  },

  showPage: function(){

    let that = this;

    // 关闭白底
    that.setData({
      'page.mask': false
    });

    setTimeout(function(){

      // 隐藏 Guide, 显示领卡
      that.setData({
        'nav.title': '天智星',
        'nav.style': 'black', 
        'guide.show': false,
        'guide.move': true,
        'page.ready': true,
        'nav.border': true
      });

      setTimeout(function(){
        that.setData({
          'guide.finish': true
        })
      },200);

    },200);

  },

  loadGuideImgs: function(fn){

    let that = this;
    let bgList = that.data.guide.bgList;

    that.imgLoader.load(bgList[0], (err, data) => {
      console.log('guide img 1',err);
      that.setData({'guide.bg[0]': data.src});
      that.imgLoader.load(bgList[1], (err, data) => {
        console.log('guide img 2', err);
        that.setData({ 'guide.bg[1]': data.src });
        that.imgLoader.load(bgList[2], (err, data) => {
          console.log('guide img 3', err);
          that.setData({ 'guide.bg[2]': data.src });

          fn && fn();
        });
      });
    });
  },

  // 隐藏 Msg - 通用
  hideMsg: function () {

    this.setData({ 'msg.show': false });
  },

  onLoad: function (options) {

    let that = this;

    that.imgLoader = new ImgLoader(that);

    app.checkLogin().then(function (res) {

      that.loadGuideImgs(function(){

        // 显示 Guide
        setTimeout(function(){
          that.setData({
            'nav.title': '',
            'nav.style': 'white',
            'guide.show': true,
            'page.loading': false,
            'page.ready': true,
          })
        },300)

      });

    });

  },

  onShow: function () {

    let that = this;

  }
  
})