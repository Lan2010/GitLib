var config = (function (_window) {
    "use strict";

    const projects = ["tianzhixing-oms-platform"],
        versionKeys = [
            {key: "version", url: "/statics/data/json/data.version.json", name: "VERSION"}
        ];

    /**
     *  返回对象
     * @type {{api: {default: null, loanLog: string, sso: string, socket: string}, domains: string[],
     *      attachment: {uploader: string, fileStatus: string, docViewPath: string, pptViewPath: string, xlsViewPath: string, pdfViewPath: null, imageViewPath: string},
     *      permission: string, token: string, version: string, userId : string, name: string, channelId: number, sysId: number, protocol: string, port: number, projectname: string, hostname: string,
     *      setSessionStorage: Function, getSessionStorage: Function, resetSessionStorage: Function, setLocalStorage: Function, getLocalStorage: Function, resetLocalStorage: Function,
     *      setJqXHR: Function, getJqXHR: Function, route: Function, sessionTimeout: Function, setCookie: Function, getCookie: Function, resetCookie: Function}}
     */
    let result = {
        api: {
            default: "/", // 默认请求地址
            upload: "/file/upload",
            videoUpload: "/file/videoUpload",
            imageUpload: "/file/imageUpload"
        },
        permission: "permission", // 权限标识
        version: "version", // 版本
        name: "name", //
        userId: "userId", //
        versionKeys: versionKeys,
        protocol: null,
        port: null,
        hostname: null,
        setSessionStorage: null,
        getSessionStorage: null,
        resetSessionStorage: null,
        setLocalStorage: null,
        getLocalStorage: null,
        resetLocalStorage: null,
        route: null, // 路由
        sessionTimeout: null, // 会话失效
        setCookie: null, // 设置cookie
        getCookie: null, // 获取cookie
        resetCookie: null // 重置cookie
    };

    /**
     * 设置sessionStorage的值 note : null会转换为"null"
     * @param key
     * @param value
     */
    result.setSessionStorage = function (key, value) {
        key && sessionStorage.setItem(key, value);
    };

    /**
     * 获取sessionStorage的值
     * @param key
     * @returns {*}
     */
    result.getSessionStorage = function (key) {
        let _v = null;
        key && (_v = sessionStorage.getItem(key));
        return _v;
    };

    /**
     * 重置sessionStorage数据
     */
    result.resetSessionStorage = function () {
        sessionStorage.clear();
    };

    /**
     * 设置localStorage的值 note : null会转换为"null"
     * @param key
     * @param value
     */
    result.setLocalStorage = function (key, value) {
        key && localStorage.setItem(key, value);
    };

    /**
     * 获取localStorage的值
     * @param key
     * @return {*}
     */
    result.getLocalStorage = function (key) {
        let _v = null;
        key && (_v = localStorage.getItem(key));
        return _v;
    };

    /**
     * 重置localStorage数据
     */
    result.resetLocalStorage = function () {
        localStorage.clear();
    };

    // 动态获取api.default的值
    let topLocation = _window.top.document.location,
        _root = null,
        topHref = topLocation.href, // http://127.0.0.1/ || http://www.baidu.com/
        pathName = topLocation.pathname, // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        page = null;
    if (pathName === "/") {
        _root = topHref;
        page = pathName;
    } else {
        let pnIndex = topHref.indexOf(pathName),
            hostName = topHref.substring(0, pnIndex), // 获取主机地址，如： http://localhost:8083
            projectName = pathName.substring(1, pathName.substr(1).indexOf('/') + 1); // 获取带"/"的项目名，如：/xxx

        pathName = _window.document.location.pathname;
        page = pathName.slice(pathName.lastIndexOf("/") + 1); // 获取当前页面名称

        !projects.includes(projectName) && (projectName = "/");
        _root = hostName + "/";
        projectName !== "/" && (_root = _root + projectName + "/");
        result.hostname = hostName;
        result.projectname = projectName;
    }
    result.api.default = _root;
    result.port = topLocation.port;
    result.protocol = topLocation.protocol;

    /**
     * 路由
     * @param router
     */
    result.route = function (router) {
        if (router) {
            _window.top.location.href = _root + router;
        }
    };

    /**
     *
     * @param key
     * @param value
     * @param options
     */
    result.setCookie = function (key, value, options) {
        let cookies = [
            options.expires ? options.expires === -1 ? '; expires=' + -1 : '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
            options.path ? '; path=' + options.path : '',
            options.domain ? '; domain=' + options.domain : '',
            options.secure ? '; secure' : ''
        ].join('');
        document.cookie = key + '=' + value + cookies;
    };

    /**
     * 获取cookie
     * @param key
     * @return {*}
     */
    result.getCookie = function (key) {
        let result = null;
        if (key) {
            let cookies = _window.document.cookie ? _window.document.cookie.split('; ') : [],
                i = 0,
                l = cookies.length;

            for (; i < l; i++) {
                let parts = cookies[i].split('='),
                    name = parts.shift(),
                    cookie = parts.join('=');

                if (key === name) {
                    result = cookie;
                    break;
                }
            }
        }
        return result;
    };

    /**
     * 重置所有api域中cookie
     */
    result.resetCookie = function (domain) {
        result.setCookie(result.token, "", {
            expires: -1,
            path: "/",
            domain: domain
        });
    };

    return result;

})(window);
