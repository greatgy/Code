package com.supergenius.web.account.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.utils.BarCodeUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;

/**
 * 图片生成
 * @author architect.bian
 */
@Controller
public class ImageController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ImageController.class);
	
	/**
	 * 显示二维码
	 * @param model
	 * @param width
	 * @param height
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/api/image/qrcode/{width:\\d+}/{height:\\d+}" }, method = RequestMethod.GET)
	public @ResponseBody String result(Map<String, Object> model, @PathVariable Integer width, @PathVariable Integer height, String data, HttpServletRequest request, HttpServletResponse response) {
		if (StrUtil.isNotEmpty(data)) {
			if (width < 2000 && height < 2000) {
				try {
					BarCodeUtil.createQRCodeToStream(data, width, height, response.getOutputStream());
				} catch (IOException e) {
					logException(log, e);
				}
			}
		}
		return response404(response);
	}
	
	
}
