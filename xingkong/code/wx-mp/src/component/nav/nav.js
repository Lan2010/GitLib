// component/nav/nav.js

const util = require('../../utils/util');   // Dependent on Util.getSys

// function getSys(){
  
//   let sys = {};

//   // Get system info
//   try {
//     sys = wx.getSystemInfoSync();

//     let os = sys.system.toLowerCase();

//     // OS
//     sys.os = /ios/i.test(os) && 'ios' || /ios/i.test(os) && 'android' || 'default'; 

//     // Nav bar height
//     sys.navBarHeight = sys.os == 'ios' && 44 || sys.os == 'android' && 48 || 48;

//     // Status bar height
//     if (!sys.statusBarHeight) {
//       sys.statusBarHeight = sys.isIOSX && 44 || sys.os == 'ios' && 20 || 24;
//     }

//     // Total bar height
//     sys.barHeight = sys.statusBarHeight + sys.navBarHeight;                       
//   }
//   catch (e) {
//     sys.os = 'android';
//     sys.statusBarHeight = 24;
//     sys.navBarHeight = 48;
//     sys.barHeight = 72;
//   }
  
//   return sys;
// };

const sys = util.getSys();

Component({
  
  properties: {
    navShow: {
      type: Boolean,
      value: sys.customNav
    },
    navStyle: {
      type: String,
      value: 'black',
      observer: '_styleChange'
    },
    navBg: {
      type: String,
      value: ''
    },
    navBorder: {
      type: Boolean,
      value: false
    },
    title: {
      type: String,
      value: ''
    },
    navBack: {
      type: Boolean,
      value: false
    },
    navBackParams: {
      type: Object,
      value: {}
    }
  },

  data: {
    isIndex: true,
    os: sys.os,
    customNav: sys.customNav,
    statusHeight: sys.statusBarHeight,
    navHeight: sys.navBarHeight
  },

  methods: {

    _styleChange: function(newVal, oldVal){

      // console.log(newVal);
      this._changeNavBarStyle(newVal);
    },

    _changeNavBarStyle: function(style){

      let that =this;
      let color = style == 'white' && '#ffffff' || style == 'black' && '#000000' || '';

      if (color && that.data.customNav){
        wx.setNavigationBarColor({
          frontColor: color,
          backgroundColor: color,
          animation: {
            duration: 400,
            timingFunc: 'easeIn'
          }
        });
      }
    },

    navBack: function(){

      let that = this;

      if (that.data.isIndex){
        wx.redirectTo({
          url: '/pages/index/index',
        })
      }
      else {
        wx.navigateBack(that.navBackParams);
      }

      // this.triggerEvent('back');
    }

  },

  attached: function(){

    let that = this;
    let pages = getCurrentPages();
    let index = pages.length - 1;   // Current page index

    that.setData({
      isIndex: index == 0 ? true : false
    });
  },

  ready: function(){

    let that = this;

    that._changeNavBarStyle(that.data.navStyle);
  }
})
