// pages/mine/award.js

const conf = require('../../config');
const app = getApp();

Page({

  data: {

    conf: conf,
    sys: app.sys,

    nav: {
      title: '我的奖品',
      style: 'black',
      back: true,
      // backParams: { tab: 2 },
      bg: 'white',
      border: true,
      height: app.sys.barHeight
    },

    page: {
      loading: true
    }

  },

  getWinnerList: function () {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/prize/individualPrizes';

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

        that.setData({'page.loading':false})
      }
    });

  },


  onLoad: function (options) {

  },

  onReady: function () {

  },

  onShow: function () {

    let that = this;

    app.checkLogin().then(function () {

      that.getWinnerList();
    });
  }

})