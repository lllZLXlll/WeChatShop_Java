package com.wechat.shop.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadService {

	Map<String, Object> uploadImage(MultipartFile imageFile, HttpSession session, String folderPath) throws Exception;


}
