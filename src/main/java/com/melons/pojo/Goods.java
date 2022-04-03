package com.melons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private int no;
    private String number;
    private String name;
    private String phone;
    private String info;
    private Timestamp time;
    private String text;

    public Goods(String number,String info,Timestamp time,String text){
        this.number = number;
        this.info = info;
        this.time = time;
        this.text =text;

    }
}
