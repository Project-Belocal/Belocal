package kr.co.belocal.web.entity;

public class NoticeType {
    private Integer id;
    private enum Type {
        newMessage,requestOfChat,approvalOfChat
    }
}
