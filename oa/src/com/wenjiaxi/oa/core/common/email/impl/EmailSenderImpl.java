package com.wenjiaxi.oa.core.common.email.impl;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.wenjiaxi.oa.core.common.email.EmailSender;

/**
* 邮件发送工具
* @author WEN JIAXI
* @date 2016年7月31日 上午9:04:42
* @version 1.0
*/

@Component
public class EmailSenderImpl implements EmailSender {

	@Resource
	private JavaMailSender javaMailSender;
	@Value("fkjava8888@163.com")
	private String from;
	
	/**
	 * 发送邮件方法
	 * @param to 收件人
	 * @param subject 邮件的主题
	 * @param content 邮件消息体
	 * @param isHtml 是否为html格式的邮件: true : html格式   false: 文本格式
	 * @return true : 发送成功   false: 发送失败
	 */
	public boolean send(String to, String subject, String content, boolean isHtml){
		try {
			//创建邮件信息对象
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			//创建邮件信息处理对象
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(content, isHtml);
			javaMailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}
