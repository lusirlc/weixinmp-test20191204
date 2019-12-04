package pers.luchuan.weixinmp.service.impl;

import javafx.scene.transform.Rotate;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import pers.luchuan.weixinmp.service.WxService;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created By Lu Chuan On 2019/11/30
 */
@Service
public class WxServiceImpl implements WxService {
	public static final String TOKEN = "lc";
	
	@Override
	public Map<String, String> parseRequest(InputStream inputStream) {
		HashMap<String, String> map = new HashMap<>();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for (Element element : elements) {
				map.put(element.getName(), element.getStringValue());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public boolean check(String signature, String timestamp, String nonce) {
		String[] array = new String[]{TOKEN,timestamp,nonce};
		System.out.println(array);
		Arrays.sort(array);
		System.out.println(array);
		String sortStr = array[0] + array[1] + array[2];
		String result = sha1(sortStr);
		System.out.println(result);
		return Objects.equals(signature,result);
	}
	
	private String sha1(String src) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("sha1");
			byte[] digest = sha1.digest(src.getBytes());
			char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
