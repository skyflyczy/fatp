package com.fatp.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fatp.interceptor.WithoutLogin;
import com.huajin.baymax.session.SessionFactory;
import com.huajin.baymax.support.ConfigProperties;

/**
 * 验证码
 * 
 * @author zhiya.chai 2015年8月5日 上午10:09:54
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController{

	private int width = 90;// 定义图片的width
	private int height = 20;// 定义图片的height
	private int codeCount = 4;// 定义图片上显示验证码的个数
	private int xx = 15;
	private int fontHeight = 18;
	private int codeY = 16;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
			'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', '3', '4', '5', '6', '7', '8', '9' };
	private final Color[] ColorOptions = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, 
			Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
			new Color(165,42,42), new Color(210,105,30), new Color(220,20,60), new Color(255,99,71),
			new Color(128,0,128), new Color(0,0,128)};

	@RequestMapping("/getcode")
	@WithoutLogin
	public void getCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// 随机产生20条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		//int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length-1)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
//			red = random.nextInt(255);
//			green = random.nextInt(255);
//			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
//			gd.setColor(new Color(red, green, blue));
			gd.setColor(ColorOptions[random.nextInt(ColorOptions.length)]);
			gd.drawString(code, (i + 1) * xx, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		
		String memcacheKey = genMemKey(request, response);
		//存放于memcache
		super.memcachedCache.set(memcacheKey, randomCode.toString(), new Date(5*60*1000));
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}

	/**
	 * 验证图片验证码
	 * @param request
	 * @param code
	 * @return
	 * @return Map<String,Object>
	 * @author zhiya.chai
	 */
	protected boolean validCode(HttpServletRequest request, HttpServletResponse response, String code){
		String isTest = ConfigProperties.getProperty("isTest");
		if(StringUtils.isNotBlank(isTest) && isTest.equals("true")) {
			return true;
		}
		String memcacheKey = genMemKey(request, response);
		String saveCode = (String) super.memcachedCache.get(memcacheKey);
		//删除
		memcachedCache.remove(memcacheKey);
		if(StringUtils.isBlank(saveCode) 
				|| StringUtils.isBlank(code) 
				|| !saveCode.equalsIgnoreCase(code)){
			return false;
		}
		return true;
	}

	private String genMemKey(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = SessionFactory.getCurrentSessionId(request, response);
		String source = request.getParameter("s");
		if(source == null)
			source = "";
		//memkey规则：系统 固定key sessionId 来源页面 
		return ConfigProperties.getProperty("productName") + "_CaptchaCode_" + sessionId + "_" + source;
	}
}
