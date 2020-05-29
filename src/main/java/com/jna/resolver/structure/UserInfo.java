package com.jna.resolver.structure;

import com.sun.jna.Structure;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends Structure {
    public static class ByReference extends DataRecord implements Structure.ByReference {
    }

    public static class ByValue extends DataRecord implements Structure.ByValue {
    }

    public String m_userid;
    public int m_sin_addr;
    public int m_sin_port;
    public int m_local_addr;
    public int m_local_port;
    public String m_logon_date;
    public String m_update_time;
    public int m_status;

    @Override
    protected List<String> getFieldOrder() {
        List<String> Field = new ArrayList<String>();
        Field.add("m_userid");
        Field.add("m_sin_addr");
        Field.add("m_sin_port");
        Field.add("m_local_addr");
        Field.add("m_local_port");
        Field.add("m_logon_date");
        Field.add("m_update_time");
        Field.add("m_status");
        return Field;
    }
}
