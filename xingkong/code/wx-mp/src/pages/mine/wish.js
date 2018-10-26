// pages/mywish/mywish.js

const conf = require('../../config');
const app = getApp();

Page({

  data: {

    conf: conf,
    sys: app.sys,

    nav: {
      title: '盟誓',
      style: 'black',
      back: true,
      // backParams: { tab: 2 },
      bg: 'white',
      border: true,
      height: app.sys.barHeight
    },

    page: {
      loading: true
    },
  
    audio: {
      idx: null,
      time: 0,
      status: 0
    }

  },

  audio: null,

  getMyWishList: function () {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/wish/wishList';

    wx.request({
      url: conf.host + uri,
      data: { token: token, page: 1, pageSize: 50 },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          let data = res.data.data.map(function(value){
            value.audioTimeLen = parseInt(value.audioTimeLen / 1000);
            return value;
          });

          that.setData({
            list: data
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

  // 播放
  playAudio: function(e){
    
    let that = this;
    let data = e.currentTarget.dataset;
    let current = that.data.audio;
    
    console.log('data.idx', data.idx);
    console.log('current.idx', current.idx);
    console.log('that.audio.paused', that.audio.paused);

    // 当前音频
    if (data.idx == current.idx){

      if (that.data.audio.status == 2){
        console.log('current audio play agian');
        that.audio.play();
        that.setData({
          'audio.status': 1
        })
      }
      else {
        console.log('current audio pause');
        that.audio.pause();
        that.setData({
          'audio.status': 2
        })
      }

      return;
    }

    // 新音频

    setTimeout(function(){

      that.setData({
        audio: {
          idx: data.idx,
          time: 0,
          status: 1,
          
        }
      });

      that.audio.src = data.url;
      that.audio.play();

    },300);

    console.log('new audio play');
  },


  // 创建 Audio 实例
  createAudio: function(url,fn){

    let that = this;

    if (that.audio){
      that.audio.destroy();
    }

    const innerAudio = wx.createInnerAudioContext();

    innerAudio.obeyMuteSwitch = false;

    // onPlay
    innerAudio.onPlay(() => {
      console.log('Audio play ..............');
    });

    // onPause
    innerAudio.onPause(() => {
      console.log('Audio pause ..............');
    });

    // onStop
    innerAudio.onStop(() => {
      that.resetAudioData();
    });

    // onEnded
    innerAudio.onEnded(() => {
      that.resetAudioData();
      console.log('Audo ended ...........');
    });

    innerAudio.onTimeUpdate(() => {
      that.updateAudioTime();   
    });

    that.audio = innerAudio;

    console.log('Create audio exp');
  },

  // 播放时间更新
  updateAudioTime: function(){

    let that = this;
    let t = parseInt(that.audio.currentTime);

    that.setData({
      'audio.time': t
    });

  },

  // 重置数据
  resetAudioData: function(){

    let that = this;

    setTimeout(function(){

      that.setData({
        audio: {
          idx: null,
          time: 0,
          status: 0
        }
      })

    },300);
  },

  // 销毁播放实例
  destroyAudio: function(){

    let that = this;

    if (that.audio){
      that.audio.stop();
      that.audio.destroy();
      that.audio = null;
    }
  },

  onLoad: function (options) {

    let that = this;

  },

  onShow: function () {

    let that = this;

    console.log(that.audio);

    // 创建音频播放实例
    that.createAudio();

    app.checkLogin().then(function () {

      that.getMyWishList();
    });
  },

  onHide: function(){

    let that = this;
    that.destroyAudio();
  },

  onUnload: function(){

    let that = this;
    that.destroyAudio();
  }

})