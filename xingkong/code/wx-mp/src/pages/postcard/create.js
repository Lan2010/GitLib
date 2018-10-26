// pages/postcard/create.js

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

    loading: false,

    page: {
      loading: true,
      ready: false,
      btnSend: false
    },

    imgLoading: false,

    userInfo: {
      nickName: '',
      avatarUrl: '',
      loaded: false
    },
    spotList: [],
    spotImgs: [],
    postcard: {
      idx: -1,
      id: 0,
      spotName: '',
      musicSrc: '',
      musicStatus: 0
    }
  },

  
  // API - 获取景点列表
  getSpotList: function(fn){

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/postcard/destlist';

    wx.request({
      url: conf.host + uri,
      data: { token: token, page: 1, pageSize: 100 },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          let data = res.data.data;

          fn && fn(data);
        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })
  },

  // API - 保存明信片
  savePostcard: function(data, fn){

    let that = this;
    let spotId = that.data.postcard.id;
    let token = wx.getStorageSync('token');
    let uri = '/api/postcard/uploadPostcard';

    if (!spotId){ return; }

    if (that.data.loading){ return; }

    that.setData({loading: true});

    wx.showLoading({
      title: '制作中',
      mask: true
    })

    wx.request({
      url: conf.host + uri,
      method: 'POST',
      data: { token: token, destId: spotId, pcText: data.pcText },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        wx.hideLoading();
        that.setData({ loading: false });

        if (app.checkResJson(res, true, uri)) {

          fn && fn(res.data.data);
        }

      },
      fail: function (res) {

        wx.hideLoading();
        that.setData({ loading: false });
        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })

  },

  // 点击发送
  sendPostcard: function (e) {

    let that = this;
    let data = e.detail.value;

    that.savePostcard(data, function (res) {

      if (!res.pcId) {
        app.msg('Success, but pcId miss');
        return;
      }

      wx.redirectTo({
        url: '/pages/postcard/share?pcId=' + res.pcId,
      })
    });
  },

  // 显示发送
  showSendBtn: function () {

    let that = this;

    if (!that.data.page.btnSend) {
      that.setData({ 'page.btnSend': true });
    }
  },

  // 隐藏发送
  hideSendBtn: function () {

    let that = this;

    if (that.data.page.btnSend) {
      that.setData({ 'page.btnSend': false });
    }
  },

  // 滚动列表
  onSpotScroll: function (e) {

    let that = this;
    let d = e.detail;

    that.hideSendBtn();
  },

  // 选择景点
  onSelectSpot: function(e){
    
    let that = this;
    let idx = e.currentTarget.dataset.idx;

    if (idx != undefined){
      that.selectSpot(idx);
    }
  },

  // 切换景点
  selectSpot: function(idx){

    if (idx === undefined){ return; }

    let that = this;
    let item = that.data.spotList[idx];

    // 点击当前景点 return
    if (idx === that.data.postcard.idx){ return; }
    
    let spotImg = item.destImgUrl;
    let spotMusic = conf.host + item.destMusic;

    // 更新数据
    that.setData({
      'postcard.idx': idx,
      'postcard.id': item.destId,
      'postcard.spotName': item.destName,
      'postcard.musicSrc': '',
      'postcard.musicStatus': 0
    });

    // 加载背景
    if (spotImg && !that.data.spotImgs[idx].img){

      that.setData({ imgLoading: true});

      that.imgLoader.load(conf.host + spotImg, (err, data) => {

        if (err) { console.log('imgLoader error', err); }
        
        that.setData({
          ['spotImgs['+idx+'].img']: data.src,
          imgLoading: false
        });
      });
    }

    // 显示发送
    that.showSendBtn();

    // 播放音乐
    that.startAudio(spotMusic, that.data.postcard.id);

  },

  // 页面点击 - 播放、暂停
  operateAudio: function () {

    let that = this;
    let status = that.data.postcard.musicStatus;

    // 暂停
    if (status == 1){
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
  startAudio: function (url, id) {

    let that = this;

    if (that.audio) { that.audio.stop(); }
    if (!url){ return; }

    that.audio.src = url + '?_=' + id;
    that.audio.play();

    that.setData({
      'postcard.musicSrc': url + '?_=' + id ,
      'postcard.musicStatus': 1
    });

    console.log('new audio play');
  },

  // 创建音频实例
  createAudio: function () {

    let that = this;

    if (that.audio) { that.audio.destroy(); }

    const innerAudio = wx.createInnerAudioContext();
    innerAudio.obeyMuteSwitch = false;
    innerAudio.loop = true;
    innerAudio.onPlay(() => { console.log('Audio play ...........');});
    innerAudio.onPause(() => { console.log('Audio pause ...........'); });
    innerAudio.onError((res) => { console.log('Audio error ...........',res); });
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


  // User head onload
  headOnLoad: function (e) {

    // console.log('headOnload',e);

    let that = this;

    that.setData({
      'userInfo.loaded': true
    });
  },

  // 更新用户信息
  initUserInfo: function(){

    let that = this;

    let nickName = wx.getStorageSync('nickName');
    let avatarUrl = wx.getStorageSync('avatarUrl');

    that.setData({
      userInfo: {
        nickName: nickName,
        avatarUrl: avatarUrl
      }
    });
  },

  initPage: function(){

    let that = this;

    that.initUserInfo();

    that.getSpotList(function(data){

      // 创建大图列表
      let spotImgs = data.map(function(item, idx){
        return {
          id: item.destId,
          img: ''
        }
      });

      // 更新列表数据
      that.setData({
        'page.loading': false,
        'page.ready': true,
        spotImgs: spotImgs,
        spotList: data
      });

      // 默认选择第一条
      if (data.length){
        that.selectSpot(0);
      }

    });
  },

  onLoad: function (options) {

    let that = this;

    that.imgLoader = new ImgLoader(that);
    
    // 创建音频实例
    that.createAudio();

    // 检查登录 ， 初始化页面
    app.checkLogin().then(function (res) {

      that.initPage();

    });
  },

  onShow: function () {

    let that = this;

    // 继续播放
    if (that.audio && that.audio.src){ 
      
      that.audio.play(); 
      that.setData({
        'postcard.musicStatus': 1
      });
    }
    
  },

  onHide: function () {

  },

  onUnload: function () {

    this.destroyAudio();
  }

})