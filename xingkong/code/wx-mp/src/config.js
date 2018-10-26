/**
 *  小程序配置文件 
 */

var host = 'https://testtzx.100msh.com';
// var host = 'http://192.168.11.104:8000/star';
// var host = 'http://192.168.11.11'; // Host test server
// var host = 'http://192.168.11.103/xingkong'; // Host yiteng 
// var host = 'http://192.168.11.102:8080/xingkong';  // Host rongshuang

var version = 'v1.0.0';

var config = {
    version,
    host,
    path: '',
    qrcodeWidth: 234,
    qrcodeHeight: 234,
    qrcode: '../../images/qrcode-test2.png',     // 小程序 - 天智星测试
    // qrcode: '../../images/qrcode-test.jpg',     // 小程序 - 天智星测试
    // qrcode: '../../images/qrcode-dev.png',      // 小程序 - 天智星开发
    request: {
      header: {
        'content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'RefererApp': 'miniprogram'
      }
    }

};

module.exports = config