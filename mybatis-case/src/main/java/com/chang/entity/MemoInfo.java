package com.chang.entity;

import com.chang.common.BackGround;
import lombok.Data;

import java.util.Date;

@Data
public class MemoInfo {
    private Integer id;
    private Integer groupId;
    private String title;
    private String content;
    private Character privacy;
    private BackGround backGround;
    private Character remind;
    private Date remindTime;
    private Date createdTime;
    private Date modifyTime;


}
