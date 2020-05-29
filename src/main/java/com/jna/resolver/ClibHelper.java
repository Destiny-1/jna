package com.jna.resolver;

import com.jna.resolver.Utils.FormatUtils;
import com.jna.resolver.structure.DataRecord;
import com.jna.resolver.structure.UserInfo;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class ClibHelper {

    /**
     * 获取最大用户的方法
     **/
    public void getMaxUserAmount() {
        CLibrary cLibrary = CLibrary.INSTANCE;
        System.out.println(cLibrary.get_max_user_amount());
    }

    /**
     * @param protocol 设置协议的方法
     */
    public int SelectProtocol(int protocol) {
        CLibrary cLibrary = CLibrary.INSTANCE;
        return cLibrary.SelectProtocol(protocol);
    }

    /**
     * @param nWorkMode 设置工作模式的方法
     * @return int
     */
    public int SetWorkMode(int nWorkMode) {
        CLibrary cLibrary = CLibrary.INSTANCE;
        return cLibrary.SetWorkMode(nWorkMode);
    }

    public int stopNetService() {
        Pointer pointer = new Memory(512);
        CLibrary cLibrary = CLibrary.INSTANCE;
        int index = cLibrary.stop_net_service(pointer);
        System.out.println(FormatUtils.returnStrFunc(pointer));
        return index;
    }

    public int doSendUserData(String dutId, String data) {
        CLibrary cLibrary = CLibrary.INSTANCE;
        Pointer pointer = new Memory(512);
        int len = data.getBytes().length;
        int index = cLibrary.do_send_user_data(dutId.toCharArray(), data.toCharArray(), len, pointer);
        return index;
    }

    public void setCustomIP(String... ip) {
        CLibrary cLibrary = CLibrary.INSTANCE;
        String _ip = "0.0.0.0";
        if(ip.length >= 1){
            _ip = ip[0];
        }
        long addr = FormatUtils.Ip2Int(_ip);
        cLibrary.SetCustomIP(addr);
    }

    public void doReadProc(){
        CLibrary cLibrary = CLibrary.INSTANCE;
        DataRecord.ByReference dataRecord = new DataRecord.ByReference();
        Pointer pointer = new Memory(2048);
        int index = cLibrary.do_read_proc(dataRecord, pointer, false);
        System.out.println(index);
        System.out.println(FormatUtils.returnStrFunc(pointer));
        System.out.println(dataRecord.m_data_buf);
    }

    public void getUserInfo(){
        CLibrary cLibrary = CLibrary.INSTANCE;
        UserInfo.ByReference userInfo = new UserInfo.ByReference();
        String dtuId = "test123456";
        int index = cLibrary.get_user_info(dtuId, userInfo);
        System.out.println(index);
        System.out.println(userInfo.m_userid);
    }


    public int startGprsServer(int nServerPort) {
        CLibrary cLibrary = CLibrary.INSTANCE;
        Pointer pointer = new Memory(2048);
        int index = cLibrary.start_gprs_server(nServerPort, pointer);
        System.out.println(FormatUtils.returnStrFunc(pointer));
        return index;
    }

    public static void main(String[] args) {
        ClibHelper clibHelper = new ClibHelper();
        System.out.println(clibHelper.stopNetService());
//        clibHelper.doReadProc();
//        clibHelper.setCustomIP("127.0.0.1");
//        String data = "sdasadsadsadas";
//        String dutId = "test123456";
//        System.out.println(clibHelper.doSendUserData(dutId, data));
//        clibHelper.getUserInfo();
//        System.out.println(clibHelper.doSendUserData(50));
//        System.out.println(clibHelper.startGprsServer(6666));
//        clibHelper.getMaxUserAmount();
//        System.out.println(clibHelper.SelectProtocol(1));
//        System.out.println(clibHelper.SetWorkMode(1));
//        System.out.println(Ip2Int("172.16.22.131"));

    }

}
