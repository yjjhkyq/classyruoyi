package com.ruoyi.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;
import com.ruoyi.web.controller.system.models.SysNoticeModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公告 信息操作处理
 * 
 * @author lsy
 *
 */
@Api("公告 信息操作处理")
@RestController
@Validated
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {
	@Autowired
	private ISysNoticeService noticeService;

	/**
	 * 查询公告列表
	 */
	@RequiresPermissions("system:notice:list")
	@PostMapping("/list")
	@ApiOperation("查询公告列表")
	public AjaxResult list(@RequestBody SysNoticeModel model) {
		return success(noticeService.selectNoticeList(this.<SysNotice>getPage(), BeanConverter.convert(SysNotice.class, model)));
	}

	/**
	 * 新增保存公告
	 */
	@ApiOperation("新增公告")
	@RequiresPermissions("system:notice:create")
	@Log(title = "通知公告", businessType = BusinessType.INSERT)
	@PostMapping("/create")
	public AjaxResult create(@RequestBody @Validated(SysNoticeModel.Create.class) SysNoticeModel model) {
		return success(noticeService.insertNotice(BeanConverter.convert(SysNotice.class, model)));
	}
 

	/**
	 * 修改保存公告
	 */
	@ApiOperation("修改公告")
	@RequiresPermissions("system:notice:update")
	@Log(title = "通知公告", businessType = BusinessType.UPDATE)
	@PostMapping("/update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Validated(SysNoticeModel.Update.class)SysNoticeModel model) {
		return success(noticeService.updateNotice(BeanConverter.convert(SysNotice.class, model)));
	}
	
	 /**
     * 公告精确查询
     */
    @ApiOperation("公告精确查询")
    @RequiresPermissions("system:notice:list")
    @PostMapping("/getby")
    public AjaxResult getby(@RequestBody SysNoticeModel model)
    {
        return success(noticeService.selectNotice(BeanConverter.convert(SysNotice.class, model)));
    }

	/**
	 * 删除公告
	 */
	@ApiOperation("删除公告")
	@RequiresPermissions("system:notice:delete")
	@Log(title = "通知公告", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
	@ResponseBody
	public AjaxResult delete(String ids) {
		return success(noticeService.deleteNoticeByIds(ids));
	}
}
