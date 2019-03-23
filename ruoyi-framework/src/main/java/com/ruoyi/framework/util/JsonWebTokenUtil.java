package com.ruoyi.framework.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/* *
 * @Author lsy
 * @Description 
 */
public class JsonWebTokenUtil {
	public static final String UID = "uid";
	private static final String SECRET = "WgtqaT1HNTZPZNMDJu3k";
	public static final long EXPIRE = 60 * 1000;

	/**
	 * 生成token
	 *
	 * @param uid
	 * @return
	 */
	public static String generate(long uid) {
		Date nowDate = new Date();
		// 过期时间
		Date expireDate = new Date(nowDate.getTime() + EXPIRE * 1000);
		Map<String, Object> claims = new HashMap<>(1);
		claims.put(UID, uid);
		return Jwts.builder().setClaims(claims).setIssuedAt(nowDate).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	/**
	 * 解析Claims
	 *
	 * @param token
	 * @return
	 */
	public static Claims getClaim(String token) {
		Claims claims = null;
		claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return claims;
	}

	/**
	 * 获取jwt发布时间
	 */
	public static Date getIssuedAt(String token) {
		return getClaim(token).getIssuedAt();
	}

	/**
	 * 获取UID
	 */
	public static Integer getUid(String token) {
		return Integer.parseInt(getClaim(token).get(UID).toString());
	}

	/**
	 * 获取jwt失效时间
	 */
	public static Date getExpiration(String token) {
		return getClaim(token).getExpiration();
	}

	/**
	 * 验证token是否失效
	 *
	 * @param token
	 * @return true:过期 false:没过期
	 */
	public static boolean isExpired(String token) {
		try {
			final Date expiration = getExpiration(token);
			return expiration.before(new Date());
		} catch (ExpiredJwtException expiredJwtException) {
			return true;
		}
	}

}
