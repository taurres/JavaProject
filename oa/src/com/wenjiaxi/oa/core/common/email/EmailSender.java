package com.wenjiaxi.oa.core.common.email;

/**
* 
* @author WEN JIAXI
* @date 2016年7月31日 上午9:04:42
* @version 1.0
*/
public interface EmailSender {

	/**
	 * 发送邮件方法
	 * @param to 收件人
	 * @param subject 邮件的主题
	 * @param content 邮件消息体
	 * @param isHtml 是否为html格式的邮件: true : html格式   false: 文本格式
	 * @return true : 发送成功   false: 发送失败
	 */
	boolean send(String to, String subject, String content, boolean isHtml);
}
