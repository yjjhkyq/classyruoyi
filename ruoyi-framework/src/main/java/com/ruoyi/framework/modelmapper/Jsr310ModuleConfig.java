/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.ruoyi.framework.modelmapper;

import java.time.ZoneId;

/**
 * Config for {@link Jsr310Module}
 *
 * @author Chun Han Hsiao
 */
public class Jsr310ModuleConfig {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    private String datePattern = DEFAULT_DATE_PATTERN;
    private String dateTimePattern = DEFAULT_DATE_TIME_PATTERN;
    private String timePattern = DEFAULT_TIME_PATTERN;
    private ZoneId zoneId = ZoneId.systemDefault();
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	public String getDateTimePattern() {
		return dateTimePattern;
	}
	public void setDateTimePattern(String dateTimePattern) {
		this.dateTimePattern = dateTimePattern;
	}
	public String getTimePattern() {
		return timePattern;
	}
	public void setTimePattern(String timePattern) {
		this.timePattern = timePattern;
	}
	public ZoneId getZoneId() {
		return zoneId;
	}
	public void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}
	public static String getDefaultDatePattern() {
		return DEFAULT_DATE_PATTERN;
	}
	public static String getDefaultDateTimePattern() {
		return DEFAULT_DATE_TIME_PATTERN;
	}
	public static String getDefaultTimePattern() {
		return DEFAULT_TIME_PATTERN;
	}
    
}
