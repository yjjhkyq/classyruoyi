package com.ruoyi.framework.mybatisplus;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;

/**
 * 通用填充类 适用于mybatis plus
 *
 * @author Caratacus
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 修改时间
     */
    private final String updateTime = "updateTime";
    /**
     * 创建者ID
     */
    private final String createUid = "createBy";

    /**
     * 修改者ID
     */
    private final String updateUid = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName(createTime, new Date(), metaObject);
        String uid = Convert.toStr(getFieldValByName(createUid, metaObject));
        if(StringUtils.isEmpty(uid)) {
        	setInsertFieldValByName(createUid, currentUid(), metaObject);
        }
        setInsertFieldValByName(updateTime, new Date(), metaObject);
        uid = Convert.toStr(getFieldValByName(updateUid, metaObject));
        if(StringUtils.isEmpty(uid)) {
        	setInsertFieldValByName(updateUid, currentUid(), metaObject);
        }
        
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName(updateTime, new Date(), metaObject);
        String uid = Convert.toStr(getFieldValByName(updateUid, metaObject));
        if(StringUtils.isEmpty(uid)) {
        	setInsertFieldValByName(updateUid, currentUid(), metaObject);
        }
    }

    /**
     * 获取当前用户ID
     */
    private String currentUid() {
        String uid = null;
        try {
            SysUser user = ShiroUtils.getSysUser();
            if (null != user) {
				uid = user.getLoginName();
			}
        } catch (Exception ignored) {
        }
        return uid;
    }

}

