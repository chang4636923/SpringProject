package com.chang.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Data;

import java.util.Date;


@Data
public class MemoInfo {
    private Integer id;
    private Integer gId;
    private String title;
    private String content;
    private Character isProtected;
    private BackGround backGround;
    private Character isRemind;
    private Date remindTime;
    private Date createdTime;
    private Date modifyTime;
}
