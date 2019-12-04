package pers.luchuan.weixinmp.entity;

import lombok.Data;

import java.util.Map;

/**
 * Created By Lu Chuan On 2019/12/1
 */
@Data
public class BaseMessage {
	/**
	 * 开发者微信号
	 */
	private String toUserName;
	/**
	 * 发送方帐号
	 */
	private String fromUserName;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 消息id
	 */
	private String msgId;
	/**
	 * 消息创建时间
	 */
	private String createTime;
	
	public BaseMessage(Map<String, String> requestMap) {
		this.toUserName = requestMap.get("FromUserName");
		this.fromUserName = requestMap.get("ToUserName");
		this.createTime = System.currentTimeMillis() / 1000 + "";
	}
}
