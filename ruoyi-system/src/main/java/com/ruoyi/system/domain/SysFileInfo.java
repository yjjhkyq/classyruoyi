package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.base.BaseEntity;

/**
 * 文件信息
 * @author lsy
 *
 */
public class SysFileInfo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/**主键*/
	@TableId
	private long fileId;
	/**原始文件名*/
	private String fileName;
	/**文件路径*/
	private String filePath;
	/**文件保存名*/
	private String fileDiskName;
	
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileDiskName() {
		return fileDiskName;
	}
	public void setFileDiskName(String fileSaveName) {
		this.fileDiskName = fileSaveName;
	}
	
}
