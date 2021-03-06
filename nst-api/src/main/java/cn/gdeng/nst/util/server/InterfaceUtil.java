package cn.gdeng.nst.util.server;

import java.util.Map;

import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GdProperties;

/**
 * 调用谷登接口公共工具类
 * 
 * @author xiaojun
 */
public class InterfaceUtil {

	private GdProperties gdProperties;

	public GdProperties getGdProperties() {
		return gdProperties;
	}

	public void setGdProperties(GdProperties gdProperties) {
		this.gdProperties = gdProperties;
	}

	/**
	 * 获取验证码
	 * 
	 * @param param
	 * @return
	 * @throws BizException 
	 */
	public String getVerifyCode(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getVerifyUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_21000,MsgCons.M_21000);
		}
		return str;
	}

	/**
	 * 检验验证码 下一步
	 * 
	 * @param param
	 * @return
	 * @throws BizException 
	 */
	public String registerNext(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getRegisterNextUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_21001,MsgCons.M_21001);
		}
		return str;
	}

	/**
	 * 输入密码注册
	 * 
	 * @param param
	 * @return
	 */
	public String register(Map<String, Object> param) throws BizException{
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getRegisterUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}

	/**
	 * 登录
	 * 
	 * @param param
	 * @return
	 */
	public String login(Map<String, Object> param) throws BizException{
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getLoginUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_21002,MsgCons.M_21002);
		}
		return str;
	}
	
	/**
	 * 忘记密码获取验证码下一步
	 * 
	 * @param param
	 * @return
	 */
	public String findPasswordNext(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getfindPasswordNextUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}
	
	/**
	 * 修改手机号码
	 * @param param
	 * @return
	 * @throws BizException
	 */
	public String updateMobile(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getUpdateMobileUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21004,MsgCons.M_21004);
        }
        return str;
    }
	
	/**
	 * 更新联系人
	 * @param param
	 * @return
	 * @throws BizException
	 */
	public String updateUserName(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getUpdateUserNameUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21005,MsgCons.M_21005);
        }
        return str;
    }
	
	/**
	 * 会员禁用/启用
	 * @param param
	 * @return
	 * @throws BizException
	 */
	public String updateStatus(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getUpdateStatusUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21006,MsgCons.M_21006);
        }
        return str;
    }
	
	/**
	 * 重置密码
	 * @param param
	 * @return
	 * @throws BizException
	 */
	public String updatePassword(Map<String, Object> param) throws BizException {
        String str = null;
        try {
        	str = HttpClientUtil.doGet(gdProperties.getUpdatePasswordUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21007,MsgCons.M_21007);
        }
        return str;
    }
	
	
	/**
	 * 更新会员信息
	 * @param param
	 * @return /memberapi/updateMember
	 * @throws BizException
	 */
	public String updateMemberInfo(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getUpdateMemberInfoUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21000,MsgCons.M_21000);
        }
        return str;
    }
	
	/**
     * 绑定银行卡
     * @param param
     * @return
     * @throws BizException
     */
    public String saveBankCardInfo(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getAddBankCardUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21005,MsgCons.M_21005);
        }
        return str;
    }
    
    /**
     * 修改银行卡
     * @param param
     * @return
     * @throws BizException
     */
    public String updateBankCardInfo(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getUpdateBankCardUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21005,MsgCons.M_21005);
        }
        return str;
    }
    
    /**
     * 查看银行卡
     * @param param
     * @return
     * @throws BizException
     */
    public String bankCardInfo(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getBankCardInfoUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21005,MsgCons.M_21005);
        }
        return str;
    }
    
    /**
     * 银行卡类型
     * @param param
     * @return
     * @throws BizException
     */
    public String bankCardType(Map<String, Object> param) throws BizException {
        String str = null;
        try {
            str = HttpClientUtil.doGet(gdProperties.getBankCardTypeUrl(), param);
        } catch (Exception e) {
            throw new BizException(MsgCons.C_21005,MsgCons.M_21005);
        }
        return str;
    }
    
    /**
	 * app登陆统计
	 * 
	 * @param param
	 * @return
	 */
	public String appStart(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getAppStartUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}
	
	/**
	 * 平台配送货源分配失败，通知谷登平台
	 * 
	 * @param param
	 * @return
	 */
	public String notifyAssignFailure(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getNotifyAssignFailureUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}
	
	
	/**
	 * 获取采购单信息
	 * @param param key sourceId
	 * @return
	 */
	public String queryPurchaseOrderBySourceId(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getPurchaseOrderUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}
	
	/**
	 * 通知谷登平台验货通过不通过
	 * @param param
	 * @return
	 */
	public String sendSourceExaminePassStatus(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getsourceExaminePassStatusUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}
	/**
	 * 通知谷登平台物流公司已经接受货源   生成了货运订单
	 * @param param
	 * @return
	 */
	public String sendCallbackOrder(Map<String, Object> param) throws BizException {
		String str = null;
		try {
			str = HttpClientUtil.doGet(gdProperties.getCallbackOrderUrl(), param);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000,MsgCons.M_50000);
		}
		return str;
	}
	
}
