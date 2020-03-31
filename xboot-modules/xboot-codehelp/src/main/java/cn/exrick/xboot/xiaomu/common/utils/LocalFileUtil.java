/**
 * Copyright (C), 2019, 小木
 * FileName: FileUtil
 * Author:   xiaomu
 * Date:     2019/12/23 20:38
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class LocalFileUtil {

    private static Logger log = LoggerFactory.getLogger(LocalFileUtil.class);


    public static void download(String srcPath, HttpServletResponse response){
        OutputStream os = null;
        InputStream inputStream = null;

        File file = ZipUtil.zip(srcPath);
        String fileName = file.getName();
        try {
            inputStream = new FileInputStream(file);
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));

            os = response.getOutputStream();
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("下载异常", e);
        } finally {
            FileUtil.del(srcPath);
//            FileUtil.del(file);
            file.delete();
            try {

                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭异常", e);
            }
        }
    }





}
