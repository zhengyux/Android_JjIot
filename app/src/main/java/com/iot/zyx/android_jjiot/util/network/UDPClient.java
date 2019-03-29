package com.iot.zyx.android_jjiot.util.network;

import android.util.Log;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseApplication;
import com.iot.zyx.android_jjiot.switchover_hostactivity.SwitchoverHostActivity;
import com.iot.zyx.android_jjiot.switchover_hostactivity.SwitchoverHostBean;
import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/1/22 10:30
 * 修改人：xuan
 * 修改时间：2019/1/22 10:30
 * 修改备注：
 */
public class UDPClient {

    private String sendStr = "jujiang.iot.udp.link"; // 暗号
    private String netAddress = "255.255.255.255";
    private final int PORT_NUM = 12580;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    public static List<SwitchoverHostBean> lanlist = new ArrayList<>();
    public UDPClient() {
        try {
            lanlist.clear();
            datagramSocket = new DatagramSocket();
            byte[] buf = sendStr.getBytes();
            InetAddress address = InetAddress.getByName(netAddress);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            datagramSocket.send(datagramPacket);
            while (true) {
                /*** 接收数据 ***/
                byte[] receBuf = new byte[1024];
                DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
                datagramSocket.receive(recePacket);
                String receStr = new String(recePacket.getData(), 0, recePacket.getLength());
                SwitchoverHostBean switchoverHostBean = GsonUtil.GsonToBean(receStr, SwitchoverHostBean.class);
                lanlist.add(switchoverHostBean);
                Log.e("ip", "Lanip: "+receStr );
            }
        } catch (Exception e) {
            Log.e("ip", "Exception: "+e.getMessage() );
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

    }
}
