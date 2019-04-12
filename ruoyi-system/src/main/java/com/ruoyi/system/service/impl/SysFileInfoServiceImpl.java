package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.SysFileInfo;
import com.ruoyi.system.mapper.SysFileInfoMapper;
import com.ruoyi.system.service.ISysFileInfoService;
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService{

	@Autowired
	private SysFileInfoMapper fileInfoMapper;
	@Override
	public SysFileInfo selectFileInfoById(Long fileInfoId) {
		return fileInfoMapper.selectById(fileInfoId);
	}

	@Override
	public int deleteFileInfoByIds(List<Long> ids) {
		return fileInfoMapper.deleteBatchIds(ids);
	}

	@Override
	public int insertFileInfo(SysFileInfo fileInfo) {
		return fileInfoMapper.insert(fileInfo);
	}

	@Override
	public int updateFieInfo(SysFileInfo fileInfo) {
		return fileInfoMapper.updateById(fileInfo);
	}

}
