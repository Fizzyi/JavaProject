package com.fizzyi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParams {

    public String username;
    public String password;
}
