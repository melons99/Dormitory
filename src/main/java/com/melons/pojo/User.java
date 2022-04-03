package com.melons.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @Id
    private String userName;
    private String password;

}
