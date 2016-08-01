package com.wenjiaxi.oa.core.common.sms;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
* 发送短信验证码工具
* @author WEN JIAXI
* @date 2016年8月1日 下午2:00:53
* @version 1.0
*/

public class SmsUtils {

	//请求地址
	private static final String URL = "http://gw.api.taobao.com/router/rest";
	//APP Key
	private static final String APPKEY = "23419501";
	//App Secret
	private static final String SECRET = "b695a13f4a771b6c92144e6981a5e9c9";
	//短信签名
	private static final String SMS_FREE_SIGN_NAME = "家熙Java";
	//模板ID
	private static final String SMS_TEMPLATE_CODE = "SMS_12775333";
	
	/**
	 * 发送短信验证码
	 * @param phone 短信接受号码
	 * @param number 短信验证码
	 * @return 是否发送成功
	 */
	public static boolean send(String phone, String number){
		try {
			TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			//设置请求参数
			//短信类型，传入值请填写normal
			req.setSmsType("normal");
			//短信签名
			req.setSmsFreeSignName(SMS_FREE_SIGN_NAME);
			//短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。
			req.setSmsParamString("{\"number\":\"" + number + "\"}");
			/* 短信接收号码
			 * 支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。
			 * 群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
			 */
			req.setRecNum(phone);
			//短信模板ID,传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板
			req.setSmsTemplateCode(SMS_TEMPLATE_CODE);
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			String responseData = rsp.getBody();
			System.out.println("response:" + responseData);
			
			/**
			 * 返回信息：
			 * {"alibaba_aliqin_fc_sms_num_send_response":
			 * 		{
			 * 			"result":{"model":"102263281820^0","success":true},
			 * 			"request_id":"14pd75nsgbcct"
			 * 		}
			 * }
			 */
			//新建Gson对象解析responseData中的success信息
			Gson gson = new Gson();
			return gson.fromJson(responseData, JsonObject.class)
					.getAsJsonObject("alibaba_aliqin_fc_sms_num_send_response")
					.getAsJsonObject("result")
					.get("success")
					.getAsBoolean();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
