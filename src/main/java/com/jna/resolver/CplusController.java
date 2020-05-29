package com.jna.resolver;

import com.sun.jna.Memory;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping
@Slf4j
public class CplusController {

    private String libUrl = System.getProperty("user.dir") + File.separator + (Platform.isWindows() ? "wcomm.dll" : "libwcomm.so");

    @GetMapping("/get_max_user_amount")
    public int getMaxUserAmount() {
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        int index = instance.getFunction("get_max_user_amount").invokeInt(new Object[]{});
        instance.dispose();
        return index;
    }

    @GetMapping("/SelectProtocol")
    public int selectProtocol(@RequestParam(value = "nProtocol") int nProtocol) {
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        int index = instance.getFunction("SelectProtocol").invokeInt(new Object[]{nProtocol});
        instance.dispose();
        return index;
    }

    @GetMapping("/SetWorkMode")
    public int setWorkMode(@RequestParam(value = "nWorkMode") int nWorkMode) {
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        int index = instance.getFunction("SetWorkMode").invokeInt(new Object[]{nWorkMode});
        instance.dispose();
        return index;
    }

    @GetMapping("/start_gprs_server")
    public int startGprsServer(@RequestParam(value = "nServerPort") int nServerPort) throws UnsupportedEncodingException {
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        Pointer mess = new Memory(512);
        int index = instance.getFunction("start_gprs_server").invokeInt(new Object[]{nServerPort, mess});
        byte[] byteArray = mess.getByteArray(0, 512);
        System.out.println("----------------");
        System.out.println(new String(byteArray,"GB2312"));
        System.out.println("----------------");
        instance.dispose();
        return index;
    }

    @GetMapping("/stop_net_service")
    public int stopNetService(@RequestParam(value = "mess") String mess) {
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        int index = instance.getFunction("stop_net_service").invokeInt(new Object[]{mess});
        instance.dispose();
        return index;
    }

    @GetMapping("/SetCustomIP")
    public void setCustomIP(@RequestParam(value = "ulIPAddr") int ulIPAddr) {
        Long ipAddr = getUlong(ulIPAddr);
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        instance.getFunction("SetCustomIP").invoke(new Object[]{ipAddr});
        instance.dispose();
    }

    @GetMapping("/do_send_user_data")
    public int doSendUserData(byte userid, byte data, short len, String mess) {
        int userId = getUint8(userid);
        int _data = getUint8(data);
        int _len = getUint16(len);
        NativeLibrary instance = NativeLibrary.getInstance(libUrl);
        int index = instance.getFunction("do_send_user_data").invokeInt(new Object[]{userId, _data, _len, mess});
        instance.dispose();
        return index;
    }

    private int getUint8(byte s) {
        return s & 0x00ff;
    }

    private int getUint16(short i) {
        return i & 0x0000ffff;
    }

    private long getUlong(long i) {
        return i & Long.MAX_VALUE;
    }

}
