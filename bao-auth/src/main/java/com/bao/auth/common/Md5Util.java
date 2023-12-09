/**
 * Copyright (C), 2018-2019, 华瑞
 * FileName: Md5Util
 * Author:   Mh.Fan
 * Date:     2019-01-21 15:59
 * Description: MD5工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.bao.auth.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 〈一句话功能简述〉<br>
 * 〈MD5工具类〉
 *
 * @author Mh.Fan
 * @date 2019-01-21
 * @since 1.0.0
 */
@Slf4j
public class Md5Util {
    /**
     * 计算字符串的MD5值
     *
     * @param str 被计算的字符串
     * @return MD5值，32位大写字符串
     */
    public static String getMd5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }
}
