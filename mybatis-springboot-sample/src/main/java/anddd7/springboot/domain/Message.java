package anddd7.springboot.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Message {
    private BigDecimal msgId;

    private String userCode;

    private String userName;

    private String profileBgImg;

    private Date createTime;

    public BigDecimal getMsgId() {
        return msgId;
    }

    public void setMsgId(BigDecimal msgId) {
        this.msgId = msgId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getProfileBgImg() {
        return profileBgImg;
    }

    public void setProfileBgImg(String profileBgImg) {
        this.profileBgImg = profileBgImg == null ? null : profileBgImg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}