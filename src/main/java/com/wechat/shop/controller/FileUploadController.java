package com.wechat.shop.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wechat.shop.common.BJUI;
import com.wechat.shop.service.FileUploadService;
import com.wechat.shop.utils.DateUtil;

/**
 * 上传文件
 * 
 * @author ZLX
 *
 */

@Controller
@RequestMapping(path = "/upload")
public class FileUploadController {

	private Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * 上传首页banner图片
	 */
	@RequestMapping("/uploadHomeBannerImg")
	@ResponseBody
	public Map<String, Object> uploadHomeBannerImg(MultipartFile imageFile, HttpSession session) {
		return fileUploadService.uploadHomeBannerImg(imageFile, session);
	}

}
