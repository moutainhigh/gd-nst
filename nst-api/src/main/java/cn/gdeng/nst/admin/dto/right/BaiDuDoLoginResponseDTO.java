package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangjj
 */
public class BaiDuDoLoginResponseDTO implements Serializable{

	private static final long serialVersionUID = -8110031807209405355L;

	//0：成功，1：该用户被锁定，2：该用户需要回答密保问题，3：登陆IP被封禁，4：用户不存在，5：密码错误 6：参数错误 7：验证码错误
    private int retcode;
    //错误信息
    private String retmsg;
    //用户ucid
    private long ucid;
    //会话ID
    private String st;
    //是否是token登陆用户
    private int istoken;
    //是否需要设置Pin码
    private int setpin;
    //安全问题列表
    private List<BaiDuQuestionDTO> questions;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public long getUcid() {
        return ucid;
    }

    public void setUcid(long ucid) {
        this.ucid = ucid;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public int getIstoken() {
        return istoken;
    }

    public void setIstoken(int istoken) {
        this.istoken = istoken;
    }

    public int getSetpin() {
        return setpin;
    }

    public void setSetpin(int setpin) {
        this.setpin = setpin;
    }

    public List<BaiDuQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<BaiDuQuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "BaiDuDoLoginResponseDTO [retcode=" + retcode + ", retmsg=" + retmsg + ", ucid=" + ucid + ", st=" + st
               + ", istoken=" + istoken + ", setpin=" + setpin + ", questions=" + questions + "]";
    }   
}
