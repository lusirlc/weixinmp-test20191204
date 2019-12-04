package pers.luchuan.weixinmp.entity;

import lombok.Data;

import java.util.Map;

/**
 * Created By Lu Chuan On 2019/12/1
 */
@Data
public class TextMessage extends BaseMessage {
	private String content;
	
	@Override
	public void setMsgType(String msgType) {
		super.setMsgType("text");
	}
	
	public TextMessage(Map<String, String> requestMap, String content) {
		super(requestMap);
		this.content = content;
	}
}
