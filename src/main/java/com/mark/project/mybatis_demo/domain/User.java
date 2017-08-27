package com.mark.project.mybatis_demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mark on 17/8/26.
 */
@Setter
@Getter
@ToString
public class User {

    private Long id;

    private String name;

    private String password;

}
