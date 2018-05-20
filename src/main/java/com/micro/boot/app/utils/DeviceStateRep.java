package com.micro.boot.app.utils;

/**
 * 物联网服务器订阅的数据
 * Created by Water on 2018/4/19.
 */

public class DeviceStateRep {
    private String mac; // EA61A3AF8BEB（变量，长度12个字符）
    private int bluetoothRSSI; // 显示在APP状态上，十六进制小于等于40则显示“OK”，大于40显示“LOW”）
    private int electricQuantity1; // 电池1电量 64表示100%，00表示0%，按照十位×16+个位的结果计算百分比。（显示在APP状态上，按百分比显示）
    private int electricQuantity2; // 表示电池2电量，64表示100%，00表示0%，按照十位×16+个位的结果计算百分比。
    private int valve; // 01表示阀门打开、00表示阀门关闭。（显示在APP状态上，01显示“OPEN”，00显示“CLOSE”）

    private String Sum; //表示报文序号
    private boolean Alarm; //报警指示（显示在APP状态上，false显示“防护中”，true显示“燃气泄漏报警”红色字体）
    private String WifiStatus; //Wifi连接状态
    private boolean BeepCut; //蜂鸣器是否静音（显示在APP状态上，false喇叭图标为绿色，true喇叭图标为灰色）
    private String IP; //报警器IP地址
    private String MASK; //报警器子网掩码
    private String GateWay; //报警器默认网关
    private int WiFiRSSI; //报警器Wifi信号强度（显示在APP状态上，小于-72显示WiFi图标为灰色，大于等于-72小于-65显示1格信号，大于等于-65小于-60，显示2格信号，大于-60显示三格信号。）
    private String WiFiMode; //报警器Wifi工作模式
    private String CmdCallBack; //报警器接收到的最后一次远程指令。

    @Override
    public String toString() {
        return "DeviceStateRep{" +
                "mac='" + mac + '\'' +
                ", bluetoothRSSI=" + bluetoothRSSI +
                ", electricQuantity1=" + electricQuantity1 +
                ", electricQuantity2=" + electricQuantity2 +
                ", valve=" + valve +
                ", Sum='" + Sum + '\'' +
                ", Alarm=" + Alarm +
                ", WifiStatus='" + WifiStatus + '\'' +
                ", BeepCut=" + BeepCut +
                ", IP='" + IP + '\'' +
                ", MASK='" + MASK + '\'' +
                ", GateWay='" + GateWay + '\'' +
                ", WiFiRSSI=" + WiFiRSSI +
                ", WiFiMode='" + WiFiMode + '\'' +
                ", CmdCallBack='" + CmdCallBack + '\'' +
                '}';
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getBluetoothRSSI() {
        return bluetoothRSSI;
    }

    public void setBluetoothRSSI(int bluetoothRSSI) {
        this.bluetoothRSSI = bluetoothRSSI;
    }

    public int getElectricQuantity1() {
        return electricQuantity1;
    }

    public void setElectricQuantity1(int electricQuantity1) {
        this.electricQuantity1 = electricQuantity1;
    }

    public int getElectricQuantity2() {
        return electricQuantity2;
    }

    public void setElectricQuantity2(int electricQuantity2) {
        this.electricQuantity2 = electricQuantity2;
    }

    public int getValve() {
        return valve;
    }

    public void setValve(int valve) {
        this.valve = valve;
    }

    public String getSum() {
        return Sum;
    }

    public void setSum(String sum) {
        Sum = sum;
    }

    public boolean isAlarm() {
        return Alarm;
    }

    public void setAlarm(boolean alarm) {
        Alarm = alarm;
    }

    public String getWifiStatus() {
        return WifiStatus;
    }

    public void setWifiStatus(String wifiStatus) {
        WifiStatus = wifiStatus;
    }

    public boolean isBeepCut() {
        return BeepCut;
    }

    public void setBeepCut(boolean beepCut) {
        BeepCut = beepCut;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getMASK() {
        return MASK;
    }

    public void setMASK(String MASK) {
        this.MASK = MASK;
    }

    public String getGateWay() {
        return GateWay;
    }

    public void setGateWay(String gateWay) {
        GateWay = gateWay;
    }

    public int getWiFiRSSI() {
        return WiFiRSSI;
    }

    public void setWiFiRSSI(int wiFiRSSI) {
        WiFiRSSI = wiFiRSSI;
    }

    public String getWiFiMode() {
        return WiFiMode;
    }

    public void setWiFiMode(String wiFiMode) {
        WiFiMode = wiFiMode;
    }

    public String getCmdCallBack() {
        return CmdCallBack;
    }

    public void setCmdCallBack(String cmdCallBack) {
        CmdCallBack = cmdCallBack;
    }
}
