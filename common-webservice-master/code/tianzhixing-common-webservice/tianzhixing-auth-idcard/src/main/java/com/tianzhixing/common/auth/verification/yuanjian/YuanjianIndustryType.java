package com.tianzhixing.common.auth.verification.yuanjian;

/**
 * Created by routine.k on 2018/5/22.
 */
public enum YuanjianIndustryType {

    A(0, "互联 ，游戏，软件"),
    A1(1, "通讯、社交、社区类产品"),
    A2(2, "电商产品"),
    A3(3, "本地服务、O2O类产品"),
    A4(4, "信息、资讯产品"),
    A5(5, "文化、娱乐产品"),
    A6(6, "安全、工具类产品"),
    A7(7, "互联+"),
    A8(8, "游戏"),
    A9(9, "To B 产品"),
    A10(10, "智能硬件"),
    A11(11, "VR/AR 产品"),
    A12(12, "IT服务，系统集成"),
    A13(13, "计算机软件"),
    A14(14, "其他件"),

    B(15, "互联网金融"),
    B1(16, "配资"),
    B2(17, "理财"),
    B3(18, "黄金白银交易"),
    B4(19, "众筹"),
    B5(20, "有偿资讯"),
    B6(21, "基金交易"),
    B7(22, "外汇"),
    B8(23, "期货"),
    B9(24, "信托"),
    B10(25, "拍卖公司"),
    B11(26, "证券交易"),
    B12(27, "二元期权"),
    B13(28, "证券/基金公司推出的服务类产品"),
    B14(29, "金融软件"),
    B15(30, "积分通道"),
    B16(31, "票据"),
    B17(32, "货币代兑"),
    B18(33, "新金融"),
    B19(34, "P2P网贷"),
    B20(35, "第三方支付"),
    B21(36, "数字货币"),
    B22(37, "大数据金融"),
    B23(38, "信息化金融机构"),
    B24(39, "金融门户"),
    B25(40, "其他互联网金融类"),

    C(41, "消费金融"),
    C1(42, "综合消费金融"),
    C2(43, "旅游消费金融"),
    C3(44, "电商消费金融"),
    C4(45, "医疗消费金融"),
    C5(46, "教育消费金融"),
    C6(47, "农村消费金融"),
    C7(48, "房产消费金融"),
    C8(49, "汽车消费金融"),
    C9(50, "互联网小贷"),
    C10(51, "大学生分期"),
    C11(52, "医疗分期"),
    C12(53, "租房分期"),
    C13(54, "教育分期"),
    C14(55, "其他消费金融类"),

    D(56, "其他"),
    D1(57, "电子，通信，硬件"),
    D2(58, "汽车、机械、制造"),
    D3(59, "交通、贸易、物流"),
    D4(60, "制药、医疗"),
    D5(61, "能源化工环保"),
    D6(62, "农林牧渔"),
    D7(63, "消费品"),
    D8(64, "广告、传媒、教育、文化"),
    D9(65, "房地产、建筑、物业"),
    D10(66, "电子，通信，硬件"),
    D11(67, "服务、中介、外包"),
    D12(68, "金融"),
    D13(69, "其他");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    YuanjianIndustryType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
