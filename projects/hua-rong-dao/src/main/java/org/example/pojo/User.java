package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String email;
    private String password;//使用md5+盐 加密
    private String confirmCode;//确认码
    private LocalDateTime activationTime;//激活失效时间
    private Byte isValid;//是否可用
}
