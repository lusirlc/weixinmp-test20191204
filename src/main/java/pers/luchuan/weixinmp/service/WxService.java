package pers.luchuan.weixinmp.service;

import javax.servlet.ServletInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Created By Lu Chuan On 2019/11/30
 */
public interface WxService {
	/**
	 * 验证是否是微信服务器发来的消息
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	boolean check(String token, String timestamp, String nonce);
	
	/**
	 * 解析微信消息数据
	 * @param inputStream
	 * @return
	 */
	Map<String,String> parseRequest(InputStream inputStream);
}
