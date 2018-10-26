// pages/wish/wish.js

const conf = require('../../config');
const util = require('../../utils/util');
const ImgLoader = require('../../utils/imgLoader.js');
const app = getApp();

Page({

  data: {

    conf: conf,
    sys: app.sys,

    nav: {
      title: '对星盟誓',
      style: 'black',
      back: true,
      bg: 'white',
      border: false,
      height: app.sys.barHeight
    },

    loading: false,

    page: {
      loading: true,
      ready: false,
      bg: [
        ''
      ],
      imgList: [
        '' + conf.host + '/static/images/page-wish-bg.png'
      ]
    },

    wish: {
      ready: false,
      id: 0,
      status: 0         // 可盟誓
    },

    appt: {
      loading: false,
      status: 0,        // 可预约
      res: null,        // 预约结果
      showRes: false
    },

    record: {
      status: 0,        // 录音步骤  1 显示录音  2 录音中  3 录音结束 
      len: 60,
      odd: 0,
      past: 0,
      file: '',
      showPlay: false
    },

    audio: {
      status: 0
    },

    canIUseRecorder: wx.canIUse('getRecorderManager')
  },


  // API - 预约
  getAppt: function () {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/sub/addSub';

    wx.request({
      url: conf.host + uri,
      method: 'POST',
      data: { token: token },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

          that.setApptRes(res.data.data);

        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));

        // Hide loading
        that.setData({ 'appt.loading': false });
      },
      complete: function(){

        wx.hideLoading();
      }
    })
  },

  // API - 保存录音
  saveRecord: function (res){

    console.log('saveRecord');

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/wish/addAudio';

    // if (!that.data.wish.id){

    //   app.msg('ID Miss');
    //   return;
    // }
    if (that.data.loading){ return; }

    that.setData({loading: true});

    wx.showLoading({
      title: '盟誓保存中',
      mask: true
    })

    that.setData({
      'record.file': res.tempFilePath,
      'record.fileSize': res.fileSize,
      'record.duration': parseInt(res.duration)
    })

    wx.uploadFile({
      url: conf.host + uri,
      filePath: res.tempFilePath,
      name: 'audio',
      formData: {
        'token': token,
        'audioTimeLen': parseInt(res.duration)
        // ,'id': that.data.wish.id
      },
      success: function (res) {
        
        console.log(res);
        res.data = JSON.parse(res.data);

        if (app.checkResJson(res, true, uri)) {

          that.setData({
            'wish.status': 0,      // 不可盟誓 （已用）
            'record.status': 3
          })

          // 2 秒后显示 播放按钮
          setTimeout(function(){

            that.setData({
              'record.showPlay': true
            })
          },2000);
        }

        wx.hideLoading();
        that.setData({ loading: false });
      },
      fail: function(res){

        wx.hideLoading();
        that.setData({ loading: false });
        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })

  },

  // 显示用户授权窗口
  guideToAuth: function () {

    app.showModal({
      title: '温馨提示',
      content: '你已拒绝录音授权，请先进行录音授权。',
      cancel: true,
      confirmText: '去设置',
      openType: 'openSetting'
    })
  },

  // 引导用户授权录音
  // getAuthRecord: function (){

  //   let that = this;
    
  //   console.log('authRecord');

  //   wx.authorize({
  //     scope: 'scope.record',
  //     success: function(res){

  //       console.log('scope.record success',res);

  //       // 保存授权信息
  //       app.auth.record = true;

  //       // 开始录音
  //       that.startRecord();
  //     },
  //     fail: function(res){

  //       console.log('scope.record fail',res);

  //       // 引导授权
  //       that.guideToAuth();
  //     }
  //   })
  // },

  // 检查录音授权
  checkAuth: function (fn) {

    let that = this;

    app.getAuth().then(function () {

      // 已拒绝过授权
      if (app.auth.record === false) {
        that.guideToAuth();
        return;
      }

      // 未拒绝过授权
      if (!app.auth.record) {
        wx.authorize({
          scope: 'scope.record',
          success() {
            fn && fn();
          }
        })
        return;
      }

      fn && fn();
    });
  },

  // 录音实例
  recorder: null,

  // 开始录音
  startRecord: function(){

    let that = this;
    let data = that.data.record;

    // if (!that.data.wish.id) {

    //   app.msg('ID Miss');
    //   return;
    // }

    that.checkAuth(function(){

      // 停止录音
      if (that.data.record.status == 2) {
        that.stopRecord();
        return;
      }

      // 设置录音
      let recorder;
      let i = 1;
      let ot;

      // 保持屏幕常亮
      wx.setKeepScreenOn({
        keepScreenOn: true
      });

      recorder = wx.getRecorderManager();

      // 录音开始回调
      recorder.onStart(() => {
        console.log('recorder start');
      });

      // 录音结束回调
      recorder.onStop((res) => {
        console.log('recorder stop', res);

        ot.stop();

        that.saveRecord(res);
      });

      recorder.onError((res) => {
        console.log('recorder error', res);
      });

      // 更新界面状态
      that.setData({
        'record.status': 2
      });

      // 保存录音实例
      that.recorder = recorder;

      // 开始计时
      ot = util.timer({
        odd: data.len * 1000,
        onCount: function (odd, past) {
          that.setData({
            'record.odd': odd / 1000,
            'record.past': past / 1000
          });
        }
      });

      // 开始录音
      recorder.start({
        duration: data.len * 1000,
        sampleRate: 44100,
        numberOfChannels: 1,
        encodeBitRate: 192000,
        format: 'aac'
      });

    });

  },

  stopRecord: function(){

    let that = this;

    that.recorder.stop();
  },

  playRecord: function(){

    let that = this;
    let file = that.data.record.file;

    if (!file){ return; }

    if (!that.audio){
      that.createAudio();
    }

    // 暂停
    if (that.data.audio.status){

      that.audio.pause();
      that.setData({
        'audio.status': 0
      })
    }

    // 播放
    else {

      that.audio.play();
      that.setData({
        'audio.status': 1
      })
    }


  },

  audio: null,

  // 创建 Audio 实例
  createAudio: function (url, fn) {

    let that = this;

    if (that.audio) {
      that.audio.destroy();
    }

    const innerAudio = wx.createInnerAudioContext();

    innerAudio.src = that.data.record.file;
    innerAudio.obeyMuteSwitch = false;

    // onPlay
    innerAudio.onPlay(() => {
      console.log('Audio play ...........');
    });
    // onStop
    innerAudio.onStop(() => {
      console.log('Audio stop ...........');
    });
    // onStop
    innerAudio.onPause(() => {
      console.log('Audio pause ...........');
    });
    // onEnded
    innerAudio.onEnded(() => {

      that.setData({
        'audio.status': 0
      })
      console.log('Audio ended ...........');
    });

    that.audio = innerAudio;

    console.log('Create audio exp');
  },

  // 销毁播放实例
  destroyAudio: function () {

    let that = this;

    if (that.audio) {
      that.audio.stop();
      that.audio.destroy();
      that.audio = null;
    }
  },


  // 显示预约结果
  setApptRes: function(data){

    let that = this;

    that.setData({
      'appt.loading': false,

      'appt.status': 0,                               // 设置为 不可预约
      'appt.res': data.subSuccess,                     // 预约结果
      'wish.id': data.id,                             // 预约成功返回 预约单编号
      'wish.status': data.subSuccess,                  // 更新 盟誓状态 （1 可盟誓， 0 不可盟誓）

      'wish.ready': data.subSuccess ? true : false,    // 显示盟誓
      'appt.showRes': data.subSuccess ? false : true,  // 显示预约结果 （只显示失败）

      'record.status': data.subSuccess ? 1 : 0         // 显示录音 第一步
    });
  },

  // 预约结果确认
  apptConfirm: function () {

    let that = this;

    if (that.data.appt.status) {

    }
    else {

      wx.navigateBack();
    }
  },


  initPage: function(){

    let that = this;

    // 可盟誓
    if (that.data.wish.status){

      console.log('直接盟誓');
      return;
    }

    // 可预约
    if (that.data.appt.status) {

      console.log('预约中...');
      // that.setData({ 'appt.loading': true });

      wx.showLoading({
        title: '预约中',
        mask: true
      })

      setTimeout(function () {
        that.getAppt();
      }, 1000);

      return;
    }

  },

  hideMsg: function () {

    this.setData({ 'msg.show': false });
  },
  

  onLoad: function (e) {

    console.log(e);

    let that = this;
    let apptStatus = e.apptStatus == '1' ? 1 : 0 ;
    let wishStatus = e.wishStatus == '1' ? 1 : 0;
    // let apptStatus = 1;
    // let wishStatus = 0;

    that.setData({
      'appt.status': apptStatus,
      'wish.status': wishStatus,
      'wish.ready': wishStatus ? true : false,
      'record.status': wishStatus ? 1 : 0   // 显示录音 第一步
    });



    that.imgLoader = new ImgLoader(that);

  },

  onShow: function () {

    let that = this;
    
    app.getAuth().then(function () {

      app.checkLogin().then(function (res) {

        // 加载背景图
        if (!that.data.page.ready) {

          // Nav border
          that.setData({'nav.border': false});

          // Load bg
          that.imgLoader.load(that.data.page.imgList[0], (err, data) => {
            console.log('ImgLoader', err);

            // Set bg
            that.setData({
              'page.bg[0]': data.src
            });

            // Show
            setTimeout(function () {

              that.setData({
                'nav.border': true,
                'page.loading': false,
                'page.ready': true
              });

              that.initPage();

            }, 100);

          });
          
          return ;
        }

        that.initPage();

      });

    });

    console.log('canIUseRecorder',that.data.canIUseRecorder);
  },

  onHide: function () {

    let that = this;
  },

  onUnload: function () {

    this.destroyAudio();
  }

})