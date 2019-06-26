package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yujiaqi
 * @date 2019-04-15 16:27
 * @description:文件下载工具类
 */
public class DownLoadUtil {

    /**
     *
     * @param downloadName
     * @param response
     * @param request
     */
    public static void setResponseHeader(String downloadName,HttpServletResponse response,HttpServletRequest request){
        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String agent = request.getHeader("USER-AGENT");
        try {
            if (agent.contains("MSIE")||agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"),"ISO-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

    }

    /**
     * 单个文件下载
     * @param fileUrl
     * @param fileName 文件名称
     * @param response
     * @throws IOException
     */
    public static void urlDownload(String fileUrl,String fileName,HttpServletResponse response,HttpServletRequest request) throws IOException {
        setResponseHeader(fileName,response,request);
        URL url = new URL(fileUrl);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        int length = conn.getContentLength();
        response.setHeader("Content-Length", ""+length);
        BufferedInputStream output = new BufferedInputStream(inputStream);
        OutputStream outputStream = response.getOutputStream();
        osWrite(outputStream,inputStream);
        //释放资源
        output.close();
        output.close();
    }

    /**
     * 打包ZIP下载
     * @param fileNameList
     * @param fileUrlList
     * @param response
     * @throws Exception
     */
    public static void urlDownloadZip(String zipName,List<String> fileNameList, List<String> fileUrlList,
                                      HttpServletResponse response, HttpServletRequest request) throws Exception {
        setResponseHeader(zipName,response,request);
        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos;
        zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
        //设置压缩方法
        zipos.setMethod(ZipOutputStream.DEFLATED);
        DataOutputStream os = null;
        try {
            //循环将文件写入压缩流
            for (int i = 0; i < fileUrlList.size(); i++) {
                URL url = new URL(fileUrlList.get(i));
                URLConnection conn = url.openConnection();
                InputStream inputStream = conn.getInputStream();
                //添加ZipEntry，并ZipEntry中写入文件流
                zipos.putNextEntry(new ZipEntry(fileNameList.get(i)));
                os = new DataOutputStream(zipos);
                osWrite(os,inputStream);
                //关闭此文件流
                inputStream.close();
                //关闭当前ZIP项，并将流放置到写入的位置
                zipos.closeEntry();
            }
            //释放资源
            os.flush();
            os.close();
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //释放资源
            os.close();
            zipos.close();
        }
    }

    public static void osWrite(OutputStream os,InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024*10];
        int len;
        //循环读写
        while ((len=inputStream.read(buff))>-1) {
            os.write(buff,0,len);
        }
    }

}
