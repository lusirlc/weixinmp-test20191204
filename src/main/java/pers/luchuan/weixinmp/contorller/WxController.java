package pers.luchuan.weixinmp.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.luchuan.weixinmp.service.WxService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created By Lu Chuan On 2019/11/30
 */
@RequestMapping("/wx")
@RestController
public class WxController {
	@Autowired
	private WxService wxService;
	@GetMapping("/test")
	public void testGet() {
		System.out.println("get");
	}
	
	/**
	 * 微信服务器接入
	 * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param echostr 随机字符串
	 */
//	@GetMapping("/accessWx")
	public String accessWk(String signature, String timestamp, String nonce, String echostr) {
		if (wxService.check(signature, timestamp, nonce)) {
			System.out.println("接入成功");
			return echostr;
		}else {
			System.out.println("接入失败");
			return null;
		}
	}
	
	@PostMapping("/accessWx")
	public void accessWx() {
//		System.out.println("post");
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		InputStream is = null;
		try {
			is = request.getInputStream();
			byte[] bytes = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(bytes)) != -1) {
				sb.append(new String(bytes, 0, len));
			}
			System.out.println(sb.toString());
			Map<String,String> map = wxService.parseRequest(is);
			System.out.println(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
