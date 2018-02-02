package com.wechat.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wechat.shop.common.BJUI;
import com.wechat.shop.service.FileUploadService;

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
		try {
			return fileUploadService.uploadImage(imageFile, session, "resources/admin/upload/home/");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- uploadHomeBannerImg 上传首页banner图片 异常" + e.toString());
		}
		return BJUI.ajaxDoneInfo("300", "上传图片失败", "", "");
	}
	
	/**
	 * 上传商品图片
	 */
	@RequestMapping("/uploadProductInfoImg")
	@ResponseBody
	public Map<String, Object> uploadProductInfoImg(MultipartFile imageFile, HttpSession session) {
		try {
			return fileUploadService.uploadImage(imageFile, session, "resources/admin/upload/product/");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- uploadProductInfoImg 上传商品图片 异常" + e.toString());
		}
		return BJUI.ajaxDoneInfo("300", "上传图片失败", "", "");
	}
	
	/**
	 * 上传商品分类图片
	 */
	@RequestMapping("/uploadProductClassImg")
	@ResponseBody
	public Map<String, Object> uploadProductClassImg(MultipartFile imageFile, HttpSession session) {
		try {
			return fileUploadService.uploadImage(imageFile, session, "resources/admin/upload/product/class/");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- uploadProductClassImg 上传商品分类图片 异常" + e.toString());
		}
		return BJUI.ajaxDoneInfo("300", "上传图片失败", "", "");
	}
	
	/**
	 * 上传商品图文详情图片
	 */
	@RequestMapping("/uploadProductImageTextImg")
	@ResponseBody
	public Map<String, Object> uploadProductImageTextImg(MultipartFile imageFile, HttpSession session) {
		try {
			return fileUploadService.uploadImage(imageFile, session, "resources/admin/upload/product/imageText/");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- uploadProductImageTextImg 上传商品图文详情图片 异常" + e.toString());
		}
		return BJUI.ajaxDoneInfo("300", "上传图片失败", "", "");
	}

}
