// pages/mine/postcard.js

const conf = require('../../config');
const app = getApp();

Page({

  data: {

    conf: conf,
    sys: app.sys,
    
    nav: {
      title: '我的明信片',
      style: 'black',
      back: true,
      // backParams: {tab: 2},
      bg: 'white',
      border: true,
      height: app.sys.barHeight
    },

    page: {
      loading: true
    }
  },

  getPostCard: function(){

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/postcard/list';

    wx.request({
      url: conf.host + uri,
      data: { token: token, page: 1, pageSize: 50 },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          that.setData({
            list: res.data.data
          });
        }
        else {
          fn && fn({ login: false });
        }
      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);

        // app.msg('请求失败');
      },
      complete: function () {

        that.setData({ 'page.loading': false })
      }
    });

  },

  delPostCard: function(e){

    let that = this;
    let id = e.currentTarget.dataset.id;
    let token = wx.getStorageSync('token');
    let uri = '/api/postcard/deletepc';

    console.log(id);

    wx.showLoading({
      title: '提交中',
    })

    wx.request({
      url: conf.host + uri,
      data: { token: token, pcId: id },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {
          app.msg('删除成功',{time: 2000});

          // Refresh list
          that.getPostCard();
        }
      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);

        // app.msg('请求失败');
      },
      complete: function () {
        wx.hideLoading();
      }
    });

  },

  navtoPostcardDetl: function(e){

    let that = this;
    let pcId = e.currentTarget.dataset.id;

    if (!pcId){ return; }

    wx.navigateTo({
      url: '/pages/postcard/show?id=' + pcId,
    })
  },

  onLoad: function (options) {
  
  },

  onReady: function () {
  
  },

  onShow: function () {

    let that = this;

    app.checkLogin().then(function(){

      that.getPostCard();
    });
  }

})