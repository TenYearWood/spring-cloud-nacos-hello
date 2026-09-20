package com.csii.common.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.csii.common.vo.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ResponseUtil {

    /**
     *  使用response输出JSON
     * @param response
     */
    public static <T> void out(HttpServletResponse response, Result<T> result){

        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            out = response.getOutputStream();
            out.write(JSONUtil.toJsonStr(result).getBytes());
        } catch (Exception e) {
            log.error("输出JSON出错", e);
        } finally{
            IoUtil.close(out);
        }
    }
}
