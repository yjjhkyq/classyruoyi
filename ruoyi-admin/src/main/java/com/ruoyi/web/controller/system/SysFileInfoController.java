package com.ruoyi.web.controller.system;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.FileUploadUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysFileInfo;
import com.ruoyi.system.service.ISysFileInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件服务
 * @author lsy
 *
 */
@Api("文件服务")
@RestController
@RequestMapping("/system/file")
@Validated
public class SysFileInfoController extends BaseController{

	@Autowired
	ISysFileInfoService fileInfoService;
	
	@ApiOperation("上传文件")
	@RequiresPermissions("system:file:upload")
	@PostMapping("/upload")
	public AjaxResult uploadFile(MultipartFile file) throws IOException
	{
	    // 上传文件路径
	    String filePath = Global.getUploadPath() + LocalDate.now().toString() + File.separator;
	    // 上传并返回新文件名称
	    String fileName = FileUploadUtils.upload(filePath, file);
	    SysFileInfo fileInfo = new SysFileInfo();
	    fileInfo.setFileName(file.getOriginalFilename());
	    fileInfo.setFileDiskName(fileName);
	    fileInfo.setFilePath(filePath);
	    fileInfoService.insertFileInfo(fileInfo);
	    return success(fileInfo.getFileId());
	}
	
	 /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
	 * @throws IOException 
     */
	@ApiOperation("下载文件")
	@RequiresPermissions("system:file:download")
	@GetMapping("/download")
    public void fileDownload(Long fileId, HttpServletResponse response) throws IOException
    {
		SysFileInfo fileInfo = fileInfoService.selectFileInfoById(fileId);
	    String path = fileInfo.getFilePath() + File.separator + fileInfo.getFileDiskName();
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("multipart/form-data");
	    response.setHeader("Content-Disposition", "attachment;fileName=" + fileInfo.getFileName());
	    FileUtils.writeBytes(path, response.getOutputStream());
    }
}
