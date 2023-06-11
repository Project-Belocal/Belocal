package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class ChatRoom {
    private Integer id;
    private Integer travelThemeId;
    private Integer travelerId;
    private Integer guideId;
    private Integer isDeleted;
    private Timestamp requestTime;
    private Timestamp approvalTime;
    private Timestamp rejectionTime;
}
