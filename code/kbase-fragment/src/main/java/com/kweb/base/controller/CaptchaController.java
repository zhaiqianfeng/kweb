/**
* @author Zhai　Qianfeng
* @date April 17, 2017
* @blog www.zhaiqianfeng.com
*/

package com.kweb.base.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.kweb.base.tools.Const;

@Controller
@RequestMapping("/assets/captcha")
public class CaptchaController {
	private static Logger logger=LoggerFactory.getLogger(CaptchaController.class);
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static Random random = new Random();
	
	//初始化参数
	static {
		cs.setColorFactory(new ColorFactory() {
			@Override
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCEFGHGKLMNPQRSTUVWXYZ");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
	}

	/**
	 * 通过url获取png格式的验证码
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/png")
	public void png(HttpServletRequest req, HttpServletResponse resp) {
		try {
			switch (random.nextInt(5)) {
			case 0:
				cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
				break;
			case 1:
				cs.setFilterFactory(new MarbleRippleFilterFactory());
				break;
			case 2:
				cs.setFilterFactory(new DoubleRippleFilterFactory());
				break;
			case 3:
				cs.setFilterFactory(new WobbleRippleFilterFactory());
				break;
			case 4:
				cs.setFilterFactory(new DiffuseRippleFilterFactory());
				break;
			}
			HttpSession session = req.getSession();
			setResponseHeaders(resp);
			String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", resp.getOutputStream());
			session.setAttribute(Const.sessionCaptcha, token);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 通过http输出验证码图片
	 * @param response
	 */
	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
}
