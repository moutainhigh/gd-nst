package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * @author yangjj
 */
public class BaiDuDoLogoutResponseDTO implements Serializable{

    private static final long serialVersionUID = 5259939735467057454L;
    
    private int retcode;
    
    private String retmsg;
    
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

    @Override
    public String toString() {
        return "BaiDuDoLogoutResponseDTO [retcode=" + retcode + ", retmsg=" + retmsg + "]";
    }   
}
