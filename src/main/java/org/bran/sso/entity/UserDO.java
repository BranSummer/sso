package org.bran.sso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Auther: BranSummer
 * @Date: 2018-9-24 21:32
 * @Description:
 */
@Getter
@Setter
@ToString
public class UserDO {

    private Long id;

    private String name;

    private String password;

    private String salt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
