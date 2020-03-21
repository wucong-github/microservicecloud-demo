package com.atguigu.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {

    private Long    deptno;  //主键
    private String  dname;   //部门名称
    private String  db_source; //来自那个数据库

    public Dept(String dname) {
        this.dname = dname;
    }
}
