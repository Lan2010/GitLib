const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function trim(str){
  return str.replace(/^\s+|\s+$/g, '');
}

// Convert version string into int
function convertVer(str) {

  let ver = ('.' + str + '.').replace(/\./g, '..').replace(/\.(\d{1})\./g, '.0$1.').split('..').join('');

  // console.log(str + ' ' + parseInt(ver));

  return parseInt(ver);
}

function getSys(){

  let sys = {};

  // Get system info
  try {

    sys = wx.getSystemInfoSync();

    console.log('SDKVersion',sys.SDKVersion);

    // Version 
    let ver = convertVer(sys.version);
    let SDKVer = convertVer(sys.SDKVersion);

    sys.ver = ver;
    sys.SDKVer = SDKVer;

    // Can use nav custom style
    sys.customNav = SDKVer >= 10700 ? true : false;
    // sys.customNav = false;

    // OS
    let os = sys.system.toLowerCase();
    sys.os = /ios/i.test(os) && 'ios' || /ios/i.test(os) && 'android' || 'default';

    // is iPhone X
    sys.isIOSX = /iPhone X/i.test(sys.model);
    
    // Nav bar height
    sys.navBarHeight = sys.os == 'ios' && 44 || sys.os == 'android' && 48 || 48;

    // Status bar height
    if (!sys.statusBarHeight){
      sys.statusBarHeight = sys.isIOSX && 44 || sys.os == 'ios' && 20 || 24;
    }

    // Total bar height
    sys.barHeight = sys.customNav ? sys.statusBarHeight + sys.navBarHeight : 0;

    // 按 UI 750 设计稿定义转换率 （Canvas 转换）
    const UIDesignWidth = 750;
    sys.toPx = sys.screenWidth / UIDesignWidth;   // rxp 转换为 px 的比率
    sys.toRpx = UIDesignWidth / sys.screenWidth;  // px 转换为 rpx 的比率

    // Page height
    sys.pageWidth = sys.screenWidth;
    sys.pageHeight = sys.screenHeight - sys.barHeight;

    let baseScale = 375/603;
    let currentScale = sys.pageWidth / sys.pageHeight;

    // Page minheight
    sys.pageLowHeight = currentScale > baseScale ? true : false;
    sys.pageMinHeight = parseInt(sys.pageWidth * 1205 / 750);

    // console.log('Current sys',sys);
  }
  catch (e) {
    sys.os = 'android';
    sys.statusBarHeight = 24;
    sys.navBarHeight = 48;
    sys.barHeight = 0;
    sys.toPx = 0.5;
    sys.toRpx = 2;
    sys.customNav = false;
  }

  return sys;
}


function timer(options) {

  var opts = Object.assign({
    odd: 0,
    past: 0,
    interval: 1000,
    onStartCount: null,
    onCount: null,
    setBreak: null,
    onFinish: null,
    onComplete: null
  },
    options);

  var sto = null;

  var stop = function () {

    opts.odd = 0;
    opts.past = 0;
    if (sto) { clearTimeout(sto); }
  };

  var countDown = function () {

    opts.onStartCount && opts.onStartCount(opts.odd, opts.past);

    sto = setTimeout(function () {

      // Check break
      if (typeof opts.setBreak == 'function') { console.log('Check timer break'); if (opts.setBreak()) { stop(); return; } }

      // Count
      opts.odd -= 1000;
      opts.past += 1000;
      
      // onCount
      if (typeof opts.onCount == 'function') { opts.onCount(opts.odd, opts.past); }

      // Continue timer
      if (opts.odd > 0) {
        countDown();
        return;
      }

      // Timer finish
      if (typeof opts.onFinish == 'function') { opts.onFinish(opts.odd); }

      opts.onComplete && opts.onComplete();

      stop();

    }, opts.interval);
  };

  // Init
  if (opts.odd > 0) {
    countDown();
  }

  return {
    stop: stop
  }
}


module.exports = {
  formatTime: formatTime,
  trim: trim,
  timer: timer,
  convertVer: convertVer,
  getSys: getSys
}
