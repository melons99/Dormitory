package com.melons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outsiders {
    private int no;
    private String name;
    private String phone;
    private String reason;
    private Timestamp entrytime;
    private Timestamp leavetime;
    private String text;

    public Outsiders(String name, String phone, String reason, Timestamp entrytime, String text) {
        this.name = name;
        this.phone = phone;
        this.reason = reason;
        this.entrytime = entrytime;
        this.text = text;
    }
}
//    public String getEntrytime() {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
//        String time = dateFormat.format(entrytime);
//        return time;
//    }
//}
