package com.jna.resolver.structure;

import com.sun.jna.Structure;

import java.util.ArrayList;
import java.util.List;

public class DataRecord extends Structure{

    public static class ByReference extends DataRecord implements Structure.ByReference {
    }

    public static class ByValue extends DataRecord implements Structure.ByValue {
    }

    public String m_userid;
    public String m_recv_date;
    public String m_data_buf;
    public int m_data_len;
    public int m_data_type;

    @Override
    protected List<String> getFieldOrder() {
        List<String> Field = new ArrayList<String>();
        Field.add("m_userid");
        Field.add("m_recv_date");
        Field.add("m_data_buf");
        Field.add("m_data_len");
        Field.add("m_data_type");
        return Field;
    }
}
