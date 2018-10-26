//index.js

const conf = require('../../config');
const util = require('../../utils/util');
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
        '' + conf.host + '/static/images/page-card-bg.png?_=1',
        '' + conf.host + '/static/images/page-wish-index-1.png?_=1'
      ]
    },

    tab: {
      active: 0,
      style: 'black',
      loading: false,
      list: [
        {
          ready: false,
          init: false,
          bg: '',
          text: '绿卡',
          icon: {
            black: '/images/icon-tab-card.png',
            white: '/images/icon-tab-card-white.png',
            active: '/images/icon-tab-card-active.png'
          },
          style: 'white',
          nav: 'black',
          navBg: 'white'
        },
        {
          ready: false,
          init: false,
          bg: '',
          text: '对星盟誓',
          icon: {
            black: '/images/icon-tab-star.png',
            white: '/images/icon-tab-star-white.png',
            active: '/images/icon-tab-star-active.png',
          },
          style: 'white',
          nav: 'black',
          navBg: 'white'
        },
        {
          ready: true,
          init: false,
          text: '我的',
          icon: {
            black: '/images/icon-tab-mine.png',
            white: '/images/icon-tab-mine-white.png',
            active: '/images/icon-tab-mine-active.png',
          },
          style: 'black',
          nav: 'white'
        }
      ]
    },

    times: {
      getUser: 0,     // 当前页面 获取后台用户数据次数
      getWxUser: 0    // 当前页面 获取微信用户数据次数
    },

    userInfo: {},

    ecard: {
      date: '',
      id: ''
    },

    winner: {
      effect: true,
      bottom: '0px',
      showLen: 3,       // 列表显示数 
      interval: 1500,   // 滚动间隔 毫秒
      data: [],
      list: []
    },

    wish: {
      ready: false,
      apptStatus: 0,
      wishStatus: 0
    },

    rules: false,

    award: {
      current: 0,
      list: [
        {
          id: 0,
          title: '去酒泉看卫星发射',
          src: '/images/award-ticket.png'
        },
        {
          id: 1,
          title: '现场操控卫星拍摄',
          src: '/images/award-ticket-2.png'
        }
      ]
    },

    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },


  /**
   *  API Common
   */

  // API - 获取绿卡详情
  getECard: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/greencard/gcDetail';

    wx.request({
      url: conf.host + uri,
      data: { token: token },
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
      }
    })
  },

  // API - 获取是否可预约
  getIsAppt: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/sub/getSub';

    wx.request({
      url: conf.host + uri,
      data: { token: token },
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
      }
    })
  },

  // API - 获取中奖名单
  getWinnerList: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/prize/allPrizes';

    wx.request({
      url: conf.host + uri,
      data: { token: token },
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
      }
    })
  },


  // API - 埋点 - 分享绿卡，推送消息到运营平台
  shareGreenCard: function (gcId, fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/greencard/share';

    wx.request({
      url: conf.host + uri,
      data: { token: token, gcId: gcId },
      header: conf.request.header,
      success: function (res) {

        // console.log('[API success] - ' + uri, res);

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })
  },


  /*
   *  Page green card
   */

  // Init page data
  initPageCard: function(){

    let that = this;

    that.getECard(function (data) {

      that.setData({
        'tab.list[0].init': true,
        ecard: {
          id: data.gcId,
          date: data.signDate ? data.signDate.split('-').join('/') : '',
          gcNumber: data.gcNumber ? data.gcNumber : ''
        }
      })
    });

  },

  gotoPostcardCreate: function(){

    let that = this;

    // 判断授权 -> 引导授权
    if (!app.auth.userInfo) { that.guideToAuth(); return; }

    wx.navigateTo({
      url: '/pages/postcard/create',
    })
  },


  /*
   *  Page wish
   */

  // Init page data
  initPageWish: function (){

    let that = this;

    if (!that.data.tab.list[1].init) {
      that.setWinnerList();
    }

    that.getIsAppt(function (data) {

      that.setData({
        'wish.ready': true,
        'wish.apptStatus': data.subStatus ? 1 : 0,
        'wish.wishStatus': data.wishStatus ? 1 : 0
      });
    });

    that.setData({
      'tab.list[1].init': true
    })

  },

  onTicketChange: function(e){

    let that = this;
    let current = e.detail.current;

    that.setData({
      'award.current': current
    })
  },

  showRules: function(){

    this.setData({
      rules: true
    })
  },

  hideRules: function(){

    this.setData({
      rules: false
    })
  },

  gotoWish: function(){

    let that = this;

    // 判断授权 -> 引导授权
    if (!app.auth.userInfo) { that.guideToAuth(); return; }

    wx.navigateTo({
      url: '/pages/wish/wish?apptStatus='+ that.data.wish.apptStatus +'&wishStatus=' + that.data.wish.wishStatus,
    })
  },

  gotoWinnerList: function(){

    let that = this;

    // 判断授权 -> 引导授权
    // if (!app.auth.userInfo) { that.guideToAuth(); return; }

    wx.navigateTo({
      url: '/pages/winner/winner',
    })
  },

  setWinnerList: function(){

    let that = this;

    // 清除 Timer
    if (that.winnerTimer) {
      that.winnerTimer.stop();
      that.winnerTimer = null;
    }

    // 重置效果
    that.setData({
      'winner.effect': false     
    });

    that.getWinnerList(function(data){

      // 最新数据排最上
      data.reverse();

      that.setData({
        'winner.data': data
      });

      // Rebuild data
      let len = data.length;
      let list = [];


      if (len > 3){
        list = data.map(function(v, index){
          let cond = index + 1 > (len - that.data.winner.showLen);
          return cond ? {id: v.id + 100000000, avatarUrl: v.avatarUrl, nickName: v.nickName, gcNumber: v.gcNumber, desc: v.desc} : '';
        });
        list = list.filter(function(x){return x != '' ? true : false });
        list = list.concat(data);
      }
      else {
        list = data;
      }

      // Set data
      that.setData({
        'winner.bottom': '0px',
        'winner.list': list
      });

      if (len > 3){
        that.setWinnerScroll();
      }
    });
  },

  winnerTimer: null,

  setWinnerScroll: function(){

    let that = this;
    let data = that.data.winner.list;
    let len = data.length;

    let timer = function(){
      
      that.setData({ 'winner.effect': true });

      that.winnerTimer = util.timer({
        odd: (len - that.data.winner.showLen) * 1000,
        interval: that.data.winner.interval,
        onStartCount: function(odd, past){

          let n = past/1000 + 1;
          let move = n * (parseInt(80 * app.sys.toPx));

          // console.log('move',move);

          // 滚动
          that.setData({
            'winner.bottom': '-' + move + 'px'
          });

          // 最后一个，300毫秒后 去掉效果
          if (odd / 1000 <= 1) {
            setTimeout(function () {
              that.setData({ 'winner.effect': false })
            }, 300);
          }
        },
        onComplete: function(){

          // 重置位置
          that.setData({'winner.bottom': '0px'})

          that.winnerTimer = null;

          // 继续滚动
          timer();
        }
      })
    };

    // 按间隔时间之后 开始滚动
    setTimeout(function(){
      timer();
    }, that.data.winner.interval);
  },


  /**
   *  Page mine
   */

  // Init page data 
  initPageMine: function(){

    let that = this;

    that.setData({
      'tab.list[2].init': true
    })
  },

  // User head onload
  headOnLoad: function(e){

    // console.log('headOnload',e);

    let that = this;
    
    that.setData({
      'userInfo.loaded': true
    });
  },

  // Nav to post card
  navToMyPostCard: function () {

    let that = this;

    // 判断授权 -> 引导授权
    if (!app.auth.userInfo) { that.guideToAuth(); return; }

    wx.navigateTo({
      url: '/pages/mine/postcard',
    })
  },

  // Nav to my wish
  navToMywish: function () {

    let that = this;

    // 判断授权 -> 引导授权
    if (!app.auth.userInfo) { that.guideToAuth(); return; }

    wx.navigateTo({
      url: '/pages/mine/wish',
    })
  },

  // Nav to my award
  navToMyAward: function () {

    let that = this;

    // 判断授权 -> 引导授权
    if (!app.auth.userInfo) { that.guideToAuth(); return; }

    wx.navigateTo({
      url: '/pages/mine/award',
    })
  },


  /**
   *  User Common
   */


  // 显示用户授权窗口
  guideToAuth: function () {

    app.showModal({
      title: '温馨提示',
      content: '为了使用更多功能，请先进行用户授权，谢谢。',
      confirmText: '授权',
      openType: 'getUserInfo'
    })
  },
  
  // 获取微信用户信息 - 被动
  onGetUserInfo: function (e) {

    let that = this;
    let res = e.detail;

    console.log('获取微信用户信息 - 被动',res);

    if (res.errMsg == 'getUserInfo:ok') {

      // 更新授权到全局
      app.auth.userInfo = true;

      // 刷新当前 Tab
      that.setTabData(that.data.tab.active);

      // Check user update
      that.checkUserUpdate(res.userInfo);
    }
  },

  // Check user update
  checkUserUpdate: function (wxData) {

    let that = this;
    let current = that.data.userInfo;

    if (current.nickName != wxData.nickName || current.avatarUrl != wxData.avatarUrl) {

      // Set userinfo to page
      that.setUserInfo(wxData);

      // Save new userinfo
      app.updateUser(wxData);
    }
  },

  // Set userinfo to page
  setUserInfo: function (data) {

    let that = this;
    let oldSrc = that.data.userInfo.avatarUrl;

    that.setData({
      userInfo: {
        nickName: data.nickName ? data.nickName : '未注册',
        avatarUrl: data.avatarUrl ? data.avatarUrl : '../../images/icon-user-head.png',
        loaded: oldSrc != data.avatarUrl ? false : true
      }
    });
  },




  /**
   *  Tab Common
   */


  // onTab
  onTab: function (e) {

    let that = this;
    let idx = e.currentTarget.dataset.index;

    that.setTab(idx);
  },

  // Set tab
  setTab: function (idx) {

    let that = this;
    let tabItem = that.data.tab.list[idx];

    // Set tab view
    that.setData({
      'tab.active': idx,
      'tab.style': tabItem.ready ? tabItem.style : that.data.tab.style,
      'nav.style': tabItem.nav,
      'nav.bg': tabItem.navBg ? tabItem.navBg : ''
    });

    // 加载背景图
    if (!tabItem.ready) {

      // Tab loading
      that.setData({ 
        'nav.border': false,
        'tab.loading': true,
        'tab.style': 'black'
      });           

      // Load bg
      that.imgLoader.load(that.data.page.bg[idx], (err, data) => {

        // Set bg
        that.setData({ ['tab.list[' + idx + '].bg']: data.src });

        // Show
        setTimeout(function () {
          that.setData({
            'nav.border': true,
            'tab.loading': false,
            ['tab.list[' + idx + '].ready']: true,
            'tab.style': tabItem.style
          });
        }, 200);

      })
    }

    // 刷新 Tab 数据
    that.setTabData(idx);

    // 判断授权 -> 引导授权
    // if (!app.auth.userInfo) { that.guideToAuth(); return; }

  },

  setTabData: function (idx) {

    let that = this;

    // 绿卡
    if (idx == 0) { that.initPageCard(); }

    // 对星盟誓
    if (idx == 1) { that.initPageWish(); }

    // 我的
    if (idx == 2) { that.initPageMine(); }
  },

  // 初始化为默认数据
  setDefaultData: function () {

    let that = this;

    that.setData({
      // 'tab.list[1].init': false,
      'wish.ready': false
    })
  },


  /**
   *  Index Common
   */


  // 隐藏 Msg - 通用
  hideMsg: function(){

    this.setData({'msg.show': false});
  },

  // Init page
  initPage: function(){

    let that = this;

    that.setData({                      // Page ready
      'page.loading': false,
      'page.ready': true,
      'nav.border': true
    })

    that.setTab(that.data.tab.active);  // Set tab

  },

  // Init user data
  initUser: function(){

    let that = this;
    let getUserTimes = that.data.times.getUser;
    let getWXUserTimes = that.data.times.getWxUser;

    // 获取后台用户数据
    // if (getUserTimes < 3) {

      app.getUser(function (data) {

        that.setData({ 'times.getUser': getUserTimes + 1 });
        that.setUserInfo(data);     // Set userinfo to page

        // 获取过微信用户数据
        // if (getWXUserTimes < 3) {

          app.getWxUser(function (res) {

            that.setData({ 'times.getWXUser': getWXUserTimes + 1 });

            if (res) {
              that.checkUserUpdate(res.userInfo); // Check user update
            }
          })
        // }
      });
    // }

  },

  // onLoad
  onLoad: function (e) {

    console.log('index - onLoad',e);
    
    let that = this;
    let tabIdx = e.tab ? e.tab : that.data.tab.active; 
    
    that.setData({
      'tab.active': tabIdx
    })

    that.imgLoader = new ImgLoader(that);
  },
  
  // onShow
  onShow: function(e){

    console.log('index - onShow',e);
    console.log('App scene', app.scene);

    let that = this;

    // Set default data
    // that.setDefaultData();

    // Update wx auth data
    app.getAuth().then(function(){

      // Check login
      app.checkLogin().then(function () {

        app.isGetCard(function(data){

          // ECard Got - go to Index 
          if (data && data.isGetGC) {

            that.initUser();            // Init user data
            that.initPage();            // Init page
          }

          // ECard none - go to guide
          if (data && !data.isGetGC) {
            wx.redirectTo({
              url: '/pages/guide/guide'
            })
          }

        }); 

      });

    });

  },

  onHide: function(){
    
    let that = this;

    that.setDefaultData();

  },

  onUnload: function(){

  },

  onShareAppMessage: function(res){

    let that = this;
    let id = that.data.ecard.id;

    id = id ? id : '';

    let ecardid = that.data.ecard.id;

    if (ecardid){
      that.shareGreenCard(ecardid);
    }

    return {
      title: '您好，这是我的天智星绿卡',
      path: '/pages/ecard/show?id=' + id
    }
  }
  
})
