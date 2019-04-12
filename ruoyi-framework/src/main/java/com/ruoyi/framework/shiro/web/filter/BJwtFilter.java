package com.ruoyi.framework.shiro.web.filter;

 

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.framework.shiro.token.JwtToken;
import com.ruoyi.framework.util.ServletUtils;

/* *
 * @Author lsy
 * @Description 支持restful url 的过滤链  JWT json web token 过滤器，无状态验证
 */
public class BJwtFilter extends BPathMatchingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BJwtFilter.class);
    private StringRedisTemplate redisTemplate;

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        Subject subject = getSubject(servletRequest,servletResponse);

        //记录调用api日志到数据库

        // 判断是否为JWT认证请求
        if ((null != subject && !subject.isAuthenticated()) && isJwtSubmission((HttpServletRequest)servletRequest)) {
            AuthenticationToken token = createJwtToken((HttpServletRequest)servletRequest);
            try {
            	LOGGER.debug("before login");
                subject.login(token);
                LOGGER.debug("end before login");
                return true;
                //return this.checkRoles(subject,mappedValue);
            }catch (AuthenticationException e) {
                // 其他的判断为JWT错误无效
                ServletUtils.responseWrite(AjaxResult.error(ErrorCode.TokenError), servletResponse);
                return false;
            }catch (Exception e) {
                // 其他错误
                //LOGGER.error(IpUtil.getIpFromRequest(WebUtils.toHttp(servletRequest))+"--JWT认证失败"+e.getMessage(),e);
                // 告知客户端JWT错误1005,需重新登录申请jwt
            	ServletUtils.responseWrite(AjaxResult.error(ErrorCode.TokenError), servletResponse);
                return false;
            }
        }else {
            // 请求未携带jwt 判断为无效请求
        	ServletUtils.responseWrite(AjaxResult.error(ErrorCode.TokenError), servletResponse);
            return false;
        }
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest,servletResponse);

        // 未认证的情况
        if (null == subject || !subject.isAuthenticated()) {
            // 告知客户端JWT认证失败需跳转到登录页面
        	ServletUtils.responseWrite(AjaxResult.error(ErrorCode.TokenError), servletResponse);
        }else {
            //  已经认证但未授权的情况
            // 告知客户端JWT没有权限访问此资源
        	ServletUtils.responseWrite(AjaxResult.error(ErrorCode.TokenError), servletResponse);
        }
        // 过滤链终止
        return false;
    }

    private boolean isJwtSubmission(HttpServletRequest request) {

        String jwt = getToken(request);
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt);
    }
    
    private String getToken(HttpServletRequest request) {
    	 String jwt = request.getHeader("Authorization");
         if(!StringUtils.isEmpty(jwt)) {
         	jwt = jwt.replaceFirst("Bearer ", "");
         	LOGGER.info("jwt:" + jwt);
         }
         else {
         	jwt = request.getParameter("Authorization");
         	LOGGER.info("jwt:" + jwt);
         }
         return jwt;
    }
    private AuthenticationToken createJwtToken(HttpServletRequest request) {
        String jwt = getToken(request);
        return new JwtToken(jwt);
    }
    
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
