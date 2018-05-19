package com.micro.boot.thirdparty.paho;

/**
 * 解析物联网服务器返回的数据
 * Created by Water on 2018/4/19.
 */

public class AnalyticUtil {
    /**
     * 接到消息成功信息：STATUS:55EA61A3AF8BEB4046015EFFFFFFFFFFFFEE
     * Sum:454540
     * Alarm:false
     * BeepCut:false
     * Wifi Status:STA_GOTIP
     * IP:192.168.1.3
     * MASK:255.255.255.0
     * GateWay:192.168.1.1
     * WiFiRSSI:-60
     * WiFiMode:3
     * CmdCallBack:
     */
    public static DeviceStateRep analytic(String str) {
        String startStr = str.substring(0, str.indexOf(":") + 1);
        DeviceStateRep deviceStateRep = new DeviceStateRep();
        if ("STATUS:".equals(startStr)) {
            String[] str1 = str.split("[\n,:]");
            String status = str1[1];
            deviceStateRep.setMac(status.substring(2,14));
            String rssi = status.substring(14,16);
//            Integer.parseInt()
            deviceStateRep.setBluetoothRSSI(Integer.parseInt(status.substring(14,16),16));
            deviceStateRep.setElectricQuantity1(Integer.parseInt(status.substring(16,18),16));
            deviceStateRep.setValve(Integer.parseInt(status.substring(18,20),16));
            deviceStateRep.setElectricQuantity2(Integer.parseInt(status.substring(20,22),16));

            deviceStateRep.setSum(str1[3]);
            deviceStateRep.setAlarm(Boolean.parseBoolean(str1[5]));
            deviceStateRep.setBeepCut(Boolean.parseBoolean(str1[7]));
            deviceStateRep.setWifiStatus(str1[9]);
            deviceStateRep.setIP(str1[11]);
            deviceStateRep.setMASK(str1[13]);
            deviceStateRep.setGateWay(str1[15]);
            deviceStateRep.setWiFiRSSI(Integer.parseInt(str1[17]));
            deviceStateRep.setWiFiMode(str1[19]);
            if (str1.length >= 22)
                deviceStateRep.setCmdCallBack(str1[21]);
        }

        return deviceStateRep;
    }

}
