package com.jna.resolver;

import com.jna.resolver.structure.DataRecord;
import com.jna.resolver.structure.UserInfo;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public interface CLibrary extends Library {
    CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "wcomm" : "libwcomm"), CLibrary.class);

    int get_max_user_amount();

    int SelectProtocol(int protocol);

    int SetWorkMode(int nWorkMode);

    int start_gprs_server(int nServerPort, Pointer pointer);

    int stop_net_service(Pointer pointer);

    int do_send_user_data(char[] userId, char[] data, int len, Pointer mess);

    void SetCustomIP(long addr);

    int do_read_proc(DataRecord.ByReference dataRecord, Pointer mess, Boolean flag);

    int get_user_info(String userId, UserInfo.ByReference userInfo );

}