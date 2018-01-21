package com.wechat.shop.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.wechat.shop.common.BJUI;
import com.wechat.shop.service.FileUploadService;
import com.wechat.shop.utils.DateUtil;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private Logger logger = Logger.getLogger(FileUploadServiceImpl.class);

	@Override
	public Map<String, Object> uploadHomeBannerImg(MultipartFile imageFile, HttpSession session) {
		if (imageFile.isEmpty()) {
			return BJUI.ajaxDoneInfo("300", "上传图片不能为空", "", "");
		}
		// 获取文件名
		String fileName = imageFile.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);

		// 文件夹相对路径
		String folderPath = "resources/admin/upload/home/";
		// 文件上传后的路径
		String filePath = session.getServletContext().getRealPath("/") + folderPath;
		String saveFileName = DateUtil.getDateTimeStr() + suffixName;
		File dest = new File(filePath + saveFileName);
		logger.info("保存之后的文件名为：" + saveFileName);

		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			// 将图片保存写出到文件夹
			imageFile.transferTo(dest);
			// 将图片相对地址返回到前台
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "200");
			resultMap.put("message", "上传图片成功");
			resultMap.put("fileName", folderPath + saveFileName);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- uploadHomeBannerImg 上传首页banner图片 异常" + e.toString());
		}
		return BJUI.ajaxDoneInfo("300", "上传图片失败", "", "");
	}

}
