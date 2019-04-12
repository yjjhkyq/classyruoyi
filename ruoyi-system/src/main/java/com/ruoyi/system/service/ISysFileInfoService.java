package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysFileInfo;

public interface ISysFileInfoService {
	
	/**
	 * 根据文件id查找文件信息
	 * @param fileInfoId
	 * @return
	 */
    public SysFileInfo selectFileInfoById(Long fileInfoId);
    
    /**
     * 删除文件
     * @param ids 文件ids
     * @return
     */
    public int deleteFileInfoByIds(List<Long> ids);
    
    /**
     * 插入文件信息
     * @param fileInfo 文件信息
     * @return
     */
    public int insertFileInfo(SysFileInfo fileInfo);
    
    /**
     * 删除文件信息
     * @param fileInfo 文件信息
     * @return
     */
    public int updateFieInfo(SysFileInfo fileInfo);
}
