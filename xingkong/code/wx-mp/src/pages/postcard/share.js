// pages/postcard/share.js

const conf = require('../../config');
const util = require('../../utils/util');
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

    pcId: 0,
    pcTitle: '',
    postcard: {
      thum: '',
      img: '',
      spotName: '',
      text: '',
      nickName: '',
      avatarUrl: ''
    },
    poster: {
      img: {},
      head: {},
      qrcode: {},
      path: '',
      width: app.sys.screenWidth,
      height: app.sys.screenHeight
    }

  },

  // API - 获取明信片详情
  getPoscardDetail: function (fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let pcId = that.data.pcId;
    let uri = '/api/postcard/getPostcardById';

    if (!pcId){ app.msg('Id miss'); return; }

    wx.request({
      url: conf.host + uri,
      data: { token: token, pcId: pcId },
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


  // API - 埋点 - 分享明信片，推送消息到运营平台
  sharePostcard: function (pcId, fn) {

    let that = this;
    let token = wx.getStorageSync('token');
    let uri = '/api/postcard/share';

    wx.request({
      url: conf.host + uri,
      data: { token: token, pcId: pcId },
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

  // API - 保存标题
  savePostcardTitle: function () {

    let that = this;
    let token = wx.getStorageSync('token');
    let pcId = that.data.pcId;
    let pcTitle = that.data.pcTitle;
    let uri = '/api/postcard/updatePCTitle';

    if (!pcId){ app.msg('Id miss'); return; }

    if (!pcTitle){ return; }

    wx.request({
      url: conf.host + uri,
      data: { token: token, pcId: pcId, pcTitle: pcTitle },
      header: conf.request.header,
      success: function (res) {

        console.log('[API success] - ' + uri, res);

        if (app.checkResJson(res, true, uri)) {

        }

      },
      fail: function (res) {

        console.log('[API fail] - ' + uri, res);
        // app.msg('API fail - ' + uri + ' - ' + JSON.stringify(res));
      }
    })
  },

  // 保存标题到 data
  inputTitle: function(e){

    let that = this;
    let val = e.detail.value;

    if (!val){ return; }    

    that.setData({
      pcTitle: util.trim(val)
    })
    
  },

  // 
  navtoMyPostcard: function(){

    let that = this;

    wx.navigateTo({
      url: '/pages/mine/postcard',
    })
  },

  // 显示用户授权窗口
  guideToAuth: function () {

    app.showModal({
      title: '温馨提示',
      content: '你已拒绝保存图片授权，朋友圈海报需要先授权。',
      cancel: true,
      confirmText: '去授权',
      openType: 'openSetting'
    })
  },
  
  // 检查保存图片授权
  checkAuth: function(fn){

    let that = this;

    app.getAuth().then(function () {

      // 已拒绝过授权
      if (app.auth.writePhotosAlbum === false) {
        that.guideToAuth();
        return;
      }

      // 未拒绝过授权
      if (!app.auth.writePhotosAlbum) {
        wx.authorize({
          scope: 'scope.writePhotosAlbum',
          success() {
            fn && fn();
          }
        })
        return;
      }

      fn && fn();
    });
  },

  // 点击朋友圈海报
  onSavePoster: function(){

    let that = this;

    if (!that.data.postcard.img){ return; }

    if (that.data.poster.path){
      that.saveImgToAlbum(function () {

        app.showModal({
          title: '温馨提示',
          content: '海报已保存到你的本地相册',
          align: 'center',
          confirmText: '确认'
        });

        // app.msg('海报已保存到你的本地相册', { time: 3000 });
      });
      return;
    }

    that.checkAuth(function(){

      wx.showLoading({
        title: '海报生成中',
        mask: true
      })

      that.savePoster(function(path){

        that.setData({
          'poster.path': path
        });

        that.saveImgToAlbum(function(){

          app.showModal({
            title: '温馨提示',
            content: '海报已保存到你的本地相册',
            align: 'center',
            confirmText: '确认'
          });

          // app.msg('海报已保存到你的系统相册', { time: 3000 });
        });
      });

    });

  },

  // 保存朋友圈海报
  savePoster: function(fn){

    let that = this;
    let postcard = that.data.postcard;

    wx.getImageInfo({
      src: postcard.img,
      success: function(res){

        that.setData({
          'poster.img.width': res.width,
          'poster.img.height': res.height,
          'poster.img.path': res.path
        });

        wx.getImageInfo({
          src: conf.host + '/static/resource/qrcode.png',
          success: function(res){

            that.setData({
              'poster.qrcode.width': 530,
              'poster.qrcode.height': 530,
              'poster.qrcode.path': res.path
            });

            if (!postcard.avatarUrl){

              that.setData({
                'poster.head.width': 252,
                'poster.head.height': 252,
                'poster.head.path': '/images/icon-user-head.png'
              });

              that.buildCanvasImg(fn);

              return;
            }

            wx.getImageInfo({
              src: postcard.avatarUrl,
              success: function(res){

                that.setData({
                  'poster.head.width': res.width,
                  'poster.head.height': res.height,
                  'poster.head.path': res.path
                });

                that.buildCanvasImg(fn);
              },
              fail: function(res){
                
                console.log('wx.getImageInfo 3 fail', res);
                wx.hideLoading();
                app.msg('获取资源失败，可能断网了...',{time: 3000});
              }
            });

          },
          fail: function(){

            console.log('wx.getImageInfo 2 fail', res);
            wx.hideLoading();
            app.msg('获取资源失败，可能断网了..', { time: 3000 });
          }
        });

      },
      fail: function(res){

        console.log('wx.getImageInfo 1 fail', res);
        wx.hideLoading();
        app.msg('获取资源失败，可能断网了.', { time: 3000 });
      }
    })

  },

  // 创建Canvas图片
  buildCanvasImg: function(fn){

    let that = this;
    let postcard = that.data.postcard;
    let poster = that.data.poster;
    let sys = app.sys;
    let r = app.sys.toPx;               // rpx -> px （按 750 UI）

    // let pageWidth = sys.pageWidth;      // 页面宽度
    // let pageHeight = sys.pageHeight;    // 页面高度（除去Bar）
    let pageWidth = sys.screenWidth;      // 页面宽度
    let pageHeight = sys.screenHeight;    // 页面高度

    let textHeight = (410 + sys.barHeight + (app.sys.isIOSX ? 86 : 0)) * r;           // 文本高度 （按 750 UI）

    // Canvas context
    const ctx = wx.createCanvasContext('poster');

    // 背景图
    let imgHeight = (750 * poster.img.height / poster.img.width) * r;   // 计算图片高度 （按 750 UI）
    let imgTop = (imgHeight - (pageHeight - textHeight))/2;

    ctx.drawImage(poster.img.path, 0, -imgTop, 750 * r, imgHeight);
    ctx.draw();

    // 盖章
    // ctx.drawImage('/images/stamp.png', -11*r, 0,  212*r, 166*r);
    // ctx.draw(true);

    // 景点名称 阴影
    // ctx.font = 'normal bold 16px sans-serif';
    // ctx.setFillStyle('rgba(0,0,0,.5)');
    // ctx.setTextAlign('center');
    // ctx.fillText('' + postcard.spotName + '', 94 * r, 190 * r);
    // ctx.draw(true);

    // 景点名称
    // ctx.font = 'normal bold 16px sans-serif';
    // ctx.setFillStyle('#ffffff');
    // ctx.setTextAlign('center');
    // ctx.fillText('' + postcard.spotName + '', 92*r, 188*r);
    // ctx.draw(true);

    // 白底
    ctx.setFillStyle('#ffffff');
    ctx.fillStyle = '#ffffff';
    ctx.rect(0, (pageHeight - textHeight), 750 * r, textHeight);
    ctx.fill();
    ctx.draw(true);

    
    // 分割文本 写入设置

    let textArr = postcard.text.split('');                  // 文本数组

    let baseLeft = 33 * r;                                  // 初始 Left
    let baseTop = pageHeight - textHeight + 132 * r;        // 初始 Top

    let textMaxWidth = 750 * r - 66 * r;                    // 文本最大宽度
    let fontWidth = 16.2;                                   // 每字占宽度
    let rowTextLen = parseInt( textMaxWidth / fontWidth );  // 每行字数
    let rows = [];

    // 计算行数
    let temp = '';
    let tempRowNumber = 1;                                  // 第几行

    for (var i = 0, len = textArr.length; i < len; i++) {
      temp += textArr[i];
      if ((i + 1) == tempRowNumber * rowTextLen || (i + 1) == len) {
        rows.push(temp);
        temp = '';
        tempRowNumber += 1;
      }
    }

    console.log('rows',rows);

    // 逐字写入
 
    let rowsLen = rows.length; 
    let tempTop = rowsLen == 1 && (baseTop + 20)  || rowsLen == 2 && (baseTop + 8) || baseTop; // 起始 Top
    let lineHeight = rowsLen == 2 && 26 || 22;    // 行高

    for (let i = 0, len = rowsLen; i<len; i++){
      
      let tempRow = rows[i].split('');
      let tempRowLen = tempRow.length;
      let tempLeft = (pageWidth - (tempRowLen * fontWidth))/2;

      for (let j = 0, len2 = tempRowLen; j<len2; j++){
        
        ctx.font = 'normal bold 14px sans-serif';
        ctx.setFillStyle('#303030');
        ctx.setTextAlign('left');
        ctx.fillText(tempRow[j], tempLeft + j * fontWidth, tempTop);
        ctx.draw(true);
      }

      tempTop += lineHeight;
    }
  
    // 头像
    let circleX = pageWidth/2;                      // 圆坐标 X
    let circleY = pageHeight - textHeight;          // 圆坐标 Y
    let headX = pageHeight - textHeight - 80 * r;   // 头像图坐标 X
    let headY = pageWidth / 2 - 80*r;               // 头像图坐标 Y
    ctx.save();
    
    ctx.fillStyle = '#ffffff';
    ctx.arc(circleX, circleY, 80*r, 0, Math.PI * 2, false);
    ctx.fill();
    ctx.clip();
    ctx.drawImage(poster.head.path, headY, headX, 160*r, 160*r);
    ctx.strokeStyle = '#ebebeb';
    ctx.lineWidth = 1;
    ctx.stroke();
    ctx.restore();
    ctx.draw(true);

    // 二维码
    let qrW = 234 * r;
    let qrH = 234 * r;
    let qrL = (pageWidth - qrW)/2;
    let qrT = pageHeight - qrH - (rowsLen ? 10 * r : 80 * r) - (app.sys.isIOSX ? 86 : 0) * r;

    ctx.drawImage(poster.qrcode.path, qrL, qrT, qrW, qrH);
    ctx.draw(true);

    console.log('创建图片完成');

    setTimeout(function(){

      that.saveCanvasFile(pageWidth, pageHeight, fn);

    }, 500);
  },

  // 保存图片文件
  saveCanvasFile: function (pageWidth, pageHeight,fn){

    let that = this;

    // 保存为图片
    wx.canvasToTempFilePath({
      // x: 0,
      // y: 0,
      // width: 414 * app.sys.pixelRatio,
      // height: 672 * app.sys.pixelRatio,
      // destWidth: 414 * app.sys.pixelRatio,
      // destHeight: 672 * app.sys.pixelRatio,
      canvasId: 'poster',
      fileType: 'jpg',
      success: function (res) {

        console.log('wx.canvasToTempFilePath', res.tempFilePath);

        fn && fn(res.tempFilePath)

      },
      fail: function (res) {

        console.log('wx.canvasToTempFilePath fail', res);
        wx.hideLoading();
        app.msg('海报生成失败，请点击"保存海报"尝试重新生成');

      }
    })

  },

  // 保存到本地相册
  saveImgToAlbum: function(fn){

    let that = this;

    if (!that.data.poster.path){ 
      console.log('saveImgToAlbum fail - path empty');
      return; 
    }

    wx.saveImageToPhotosAlbum({
      filePath: that.data.poster.path,
      success(res) {

        wx.hideLoading();
        fn && fn();
      },
      fail(res) {

        wx.hideLoading();

        console.log('wx.saveImageToPhotosAlbum', res);
        // app.msg('保存图片失败 - ' + JSON.stringify(res));
        app.msg('海报保存失败，请点击"保存海报"尝试重新保存');

      }
    })
  },

  // 隐藏 Msg - 通用
  hideMsg: function () {

    this.setData({ 'msg.show': false });
  },

  initUserInfo: function(){
    
    let that = this;

    let nickName = wx.getStorageSync('nickName');
    let avatarUrl = wx.getStorageSync('avatarUrl');

    that.setData({
      userInfo: {
        nickName: nickName,
        avatarUrl: avatarUrl
      }
    })
  },

  initPage: function (){

    let that = this;

    that.getPoscardDetail(function(data){

      that.setData({
        'page.loading': false,
        'page.ready': true,
        postcard: {
          thum: conf.host + data.destBtImgUrl,
          img: conf.host + data.destImgUrl,
          spotName: data.destName,
          text: data.pcText,
          nickName: data.nickName,
          avatarUrl: data.pcHeadImg ? conf.host + data.pcHeadImg : ''
        }
      });

      // that.onSavePoster();
    });
  },
  

  onLoad: function (e) {
    
    let that = this;
    let pcId = e.pcId;
 
    console.log('postcard share onLoad',e);

    that.imgLoader = new ImgLoader(that);

    // 保存 ID
    if (pcId) { that.setData({pcId: pcId}) }

    // 初始化用户信息
    that.initUserInfo();

    // 检查登录 ， 初始化页面
    app.checkLogin().then(function (res) {

      that.initPage();

    });

  },

  onShow: function (e) {
    
    let that = this;

    console.log('postcard share onShow',e);

  },

  onShareAppMessage: function (res) {

    let that = this;
    let pcId = that.data.pcId;
    let title = that.data.pcTitle ? that.data.pcTitle : '来自 “'+that.data.userInfo.nickName+'” 的明信片';

    if (pcId){
      that.sharePostcard(pcId);
    }

    return {
      title: title,
      path: '/pages/postcard/show?id=' + pcId
    }
  }

})