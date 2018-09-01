package com.chang.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class MemoGroup {
    private int id;
    private String name;
    private Date createdTime;
    private Date modifyTime;
}
