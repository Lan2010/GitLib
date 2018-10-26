// pages/ecard/show.js

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
      bg: 'white',
      border: false,
      height: app.sys.barHeight
    },

    page: {
      loading: true,
      ready: false,
      bg: [
        ''
      ],
      imgList: [
        '' + conf.host + '/static/images/page-card-bg.png?_=1'
      ]
    },

    ecard: {},
    headLoaded: false

  },

  // API - 获取绿卡
  getUserCard: function(fn){

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/greencard/getGCById';
    
    if (!that.data.cardId){

      app.msg('Card ID miss');

      return;
    }

    wx.request({
      url: conf.host + uri,
      data: { token: token, gcId: that.data.cardId },
      header: conf.request.header,
      success: function (res) {

        // console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          let data = res.data.data;

          fn && fn(data);

          return;
        }

        // Is need relogin
        if (app.isRelogin(res)) {
          console.log('Clear token and Relogin by ' + uri);
          app.checkLogin().then(function () { that.getUserCard(fn); });
        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })
  
  },


  // User head onload
  headOnLoad: function (e) {

    // console.log('headOnload',e);

    let that = this;

    that.setData({
      headLoaded: true
    });
  },

  navtoIndex: function(){

    let that = this;

    wx.redirectTo({
      url: '/pages/index/index',
    })
  },

  initPage: function(){

    let that = this;

    that.getUserCard(function(data){

      that.setData({
        ecard: {
          gcName: data.gcName ? data.gcName : '',
          signDate: data.signDate ? data.signDate.split('-').join('/') : '',
          gcNumber: data.gcNumber ? data.gcNumber : '',
          gcHeadImg: data.gcHeadImg ? conf.host + data.gcHeadImg : ''
          // gcHeadImg: data.gcHeadImg ? data.gcHeadImg : ''
        }
      });
    });
  },

  onLoad: function (e) {

    // console.log('ecard/show onLoad',e);

    let that = this;
    let cardId = e.id ? e.id : ''; 
    
    if (!cardId){ console.log('card id miss'); return; }

    // Save id
    that.setData({cardId: cardId});

    that.imgLoader = new ImgLoader(that);
    
    app.checkLogin().then(function (res) {

      // Load bg
      that.imgLoader.load(that.data.page.imgList[0], (err, data) => {

        // Set bg
        that.setData({'page.bg[0]': data.src});

        // Show
        setTimeout(function () {

          that.setData({
            'nav.border': true,
            'page.loading': false,
            'page.ready': true
          });

          that.initPage();

        }, 200);

      });


    });
  },


  onShow: function () {

    let that = this;

  }

})