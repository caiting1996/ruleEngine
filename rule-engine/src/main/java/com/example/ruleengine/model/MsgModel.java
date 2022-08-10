package com.example.ruleengine.model;

import com.example.ruleengine.type.MsgType;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class MsgModel {
    private MsgType type;
    private Map msg;

}
