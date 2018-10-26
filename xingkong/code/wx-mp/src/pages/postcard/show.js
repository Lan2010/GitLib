// pages/postcard/show.js

const conf = require('../../config');
const ImgLoader = require('../../utils/imgLoader.js');
const app = getApp();

Page({

  data: {

    conf: conf,
    sys: app.sys,

    nav: {
      title: '明信片',
      style: 'black',
      bg: 'white',
      back: true,
      border: true,
      height: app.sys.barHeight
    },

    page: {
      loading: true,
      ready: false
    },

    imgLoading: false,

    postcard: {
      idx: -1,
      id: 0,
      spotName: '',
      musicSrc: '',
      musicStatus: 0,
      nickName: '',
      avatarUrl: ''
    }

  },


  // API - 获取明信片详情
  getPoscardDetail: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let pcId = that.data.pcId;
    let uri = '/api/postcard/getPostcardById';

    // if (!pcId) {

    //   app.msg('Id miss');
    //   return;
    // }

    wx.request({
      url: conf.host + uri,
      data: { token: token, pcId: pcId },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          let data = res.data.data;

          fn && fn(data);

          return;
        }

        // Is need relogin
        if (app.isRelogin(res)) {
          console.log('Clear token and Relogin by ' + uri);
          app.checkLogin().then(function () { that.getPoscardDetail(fn); });
        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })
  },

  showPostcard: function(data){

    let that =this;

    let musicSrc = conf.host + data.destMusic;
    let img = data.destImgUrl;

    // 更新数据
    that.setData({
      'page.loading': false,
      'page.ready': true,
      'postcard.text': data.pcText,
      'postcard.spotName': data.destName,
      'postcard.musicSrc': musicSrc ? musicSrc : '',
      'postcard.img': '',
      'postcard.musicStatus': 0,
      'postcard.nickName': data.nickName,
      'postcard.avatarUrl': conf.host + data.pcHeadImg,
      'imgLoading': true
    });

    // 播放音乐
    that.startAudio(musicSrc);

    // 加载背景
    if (img) {

      that.imgLoader.load(conf.host + img, (err, data) => {

        if (err) { console.log('imgLoader error', err); }

        that.setData({
          'postcard.img': data.src,
          'imgLoading': false
        });
      });
    }

  },


  // 页面点击 - 播放、暂停
  operateAudio: function () {

    let that = this;
    let status = that.data.postcard.musicStatus;

    // 暂停
    if (status == 1) {
      that.audio.pause();
    }

    // 播放
    else {
      that.audio.play();
    }

    // 更新状态
    that.setData({
      'postcard.musicStatus': status == 1 ? 0 : 1
    });
  },

  // 播放 新音频
  startAudio: function (url) {

    if (!url) { return; }

    let that = this;

    that.audio.stop();
    that.audio.src = url;
    that.audio.play();

    that.setData({'postcard.musicStatus': 1});

    console.log('new audio play');
  },

  // 创建音频实例
  createAudio: function () {

    let that = this;

    if (that.audio) {that.audio.destroy();}

    const innerAudio = wx.createInnerAudioContext();

    innerAudio.obeyMuteSwitch = false;
    innerAudio.loop = true;
    innerAudio.onPlay(() => { console.log('Audio play ...........'); });
    innerAudio.onPause(() => { console.log('Audio pause ...........'); });
    innerAudio.onError((res) => { console.log('Audio error ...........', res); });
    innerAudio.onEnded(() => { console.log('Audio ended ...........'); });

    that.audio = innerAudio;

    console.log('Create audio exp');
  },

  // 销毁音频实例
  destroyAudio: function () {

    let that = this;

    if (that.audio) {
      that.audio.stop();
      that.audio.destroy();
      that.audio = null;
    }
  },

  modalConfirm: function(){
    
    wx.redirectTo({
      url: '/pages/index/index'
    })
  },

  initPage: function () {

    let that = this;

    that.getPoscardDetail(function (data) {

      if (!data.pcId){

        app.showModal({
          title: '温馨提示',
          align: 'center',
          content: '该明信片已被作者删除。',
          confirmText: '确定'
        });

        return;
      }

      that.showPostcard(data);

    });
  },

  onLoad: function (e) {
    
    // console.log('Postcard/show onLoad', e);

    let that = this;
    let pcId = e.id;

    that.imgLoader = new ImgLoader(that);

    // 更新 ID
    if (pcId){ that.setData({pcId: pcId}); }

    // 创建音频实例
    that.createAudio();

    // 检查登录 ， 初始化页面
    app.checkLogin().then(function (res) {

      that.initPage();
    });
  },

  onShow: function (e) {

    // console.log('Postcard/show onShow',e);

    let that = this;

    // 继续播放
    if (that.audio && that.audio.src) { 
      that.audio.play(); 
      that.setData({
        'postcard.musicStatus': 1
      });
    }

  },

  onUnload: function () {

    this.destroyAudio();
  }
  
})