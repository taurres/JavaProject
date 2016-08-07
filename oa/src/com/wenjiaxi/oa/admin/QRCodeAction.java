package com.wenjiaxi.oa.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 根据链接url生成二维码
 * @author WEN JIAXI
 * @date 2016年7月30日 上午8:23:51
 * @version 1.0
 */

public class QRCodeAction extends ActionSupport {

	private static final long serialVersionUID = -8748329598244571654L;
	private String url;
	private final static int WIDTH = 270;
	private final static int HEIGHT = 270;
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		//服务器返回图片格式
		response.setContentType("image/png");
		//设置默认访问的url
		if (url == null || url.equals("")) {
			url = "http://www.baidu.com";
		}
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, 1);
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		BitMatrix  bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
		//输出二维码到页面
		MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
		return NONE;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
