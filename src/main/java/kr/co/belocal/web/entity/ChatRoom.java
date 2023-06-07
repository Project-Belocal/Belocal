package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class ChatRoom {
    private int id;
    private int travelThemeId;
    private int travelerId;
    private int guideId;
    private Integer isDeleted;
    private Timestamp requestTime;
    private Timestamp approvalTime;
    private Timestamp rejectionTime;
}
