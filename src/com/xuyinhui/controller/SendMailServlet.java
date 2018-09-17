package com.xuyinhui.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuyinhui.utils.EmailUtil;
import com.xuyinhui.utils.EmailUtil.ContentType;
import com.xuyinhui.utils.EmailUtil.Host;

/**
 * 访客发送消息到指定的邮箱
 * 
 * @author 胥尹辉
 *
 */
public class SendMailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置request编码，解决post提交中文乱码
		request.setCharacterEncoding("UTF-8");

		// 访客 名字
		String name = request.getParameter("name");
		// 访客 邮箱
		String email = request.getParameter("email");
		// 访客 主题
		String subject = request.getParameter("msg_subject");
		// 访客 消息体
		String message = request.getParameter("message");

		// 把访客名字和邮箱名添加到消息体中
		message = new StringBuffer("   ======= 访  客 =======   ").append("\r\n***  访客名字：").append(name)
				.append("\r\n***  访客邮箱：").append(email).append("\r\n***  访客消息：").append(message).toString();

		// 发件人
		String from = "xxxxxxxx@xxx.com";
		// 收件人
		String to = "xxxxxxxx@xxx.com";
		// 邮件服务器
		String host = Host.qq;
		// 邮箱授权码
		String password = "xxxxxxxxxxx";
		// 消息类型
		ContentType contentType = ContentType.GENERAL;

		System.out.println(name);
		System.out.println(email);
		System.out.println(subject);
		System.out.println(message);

		// 允许所有来源访问，解决ajax跨域访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");

		try {
			EmailUtil.sendEmailWithSSL(from, to, host, password, subject, message, contentType);
			response.getWriter().write("success");
		} catch (GeneralSecurityException e) {
			response.getWriter().write("发送失败！");
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}