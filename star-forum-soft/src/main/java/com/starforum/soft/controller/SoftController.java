package com.starforum.soft.controller;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import com.starforum.soft.entity.Soft;
import com.starforum.soft.service.SoftService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/soft")
@CrossOrigin
public class SoftController {

    @Autowired
    private SoftService softService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        try {
            return new Result(true, StateCode.SUCCESS, "查询成功", softService.findAll());
        }catch (Exception e){
            return new Result(false, StateCode.ERROR, "查询失败", e.getMessage());
        }
    }

    @RequestMapping(value = "/{softId}", method = RequestMethod.GET)
    public Result findSoftById(@PathVariable String softId){
        return new Result(true, StateCode.SUCCESS, "查询成功", softService.findSoftById(softId));
    }


        //在文件操作中，不用/或者\最好，推荐使用File.separator
        private final static String  fileDir = "files";
        private final static String rootPath = System.getProperty("user.home")+ File.separator + fileDir + File.separator;
        @RequestMapping("/upload")
        public Result uploadFile(@RequestParam("files") MultipartFile[] multipartFiles, @RequestParam("name") String name, @RequestParam("labelId") String labelId,
                                 @RequestParam("describe") String describe, @RequestParam("userId") String userId, @RequestParam("nickname") String nickname,
                                 final HttpServletResponse response, final HttpServletRequest request){
            Soft soft = new Soft();
            soft.setName(name);
            soft.setLabelId(labelId);
            soft.setDescribe(describe);
            soft.setUserId(userId);
            soft.setNickname(nickname);
            String path = rootPath + name + File.separator;
            File fileDir = new File(path);
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                fileDir.mkdirs();
            }
            try {
                if (multipartFiles != null && multipartFiles.length > 0) {
                    for(int i = 0;i<multipartFiles.length;i++){
                        try {
                            //以原来的名称命名,覆盖掉旧的
                            String storagePath = path + multipartFiles[i].getOriginalFilename();
                            Streams.copy(multipartFiles[i].getInputStream(), new FileOutputStream(storagePath), true);
                            soft.setPath(storagePath);
                            softService.addSoft(soft);
                            //或者下面的
                            // Path path = Paths.get(storagePath);
                            //Files.write(path,multipartFiles[i].getBytes());
                        } catch (IOException e) {
                            return new Result(false, StateCode.ERROR, "上传失败", e.getMessage());
                        }
                    }
                }

            } catch (Exception e) {
                return new Result(false, StateCode.ERROR, "上传失败", e.getMessage());
            }
            return new Result(true, StateCode.SUCCESS, "上传成功");
        }

    @RequestMapping("/download/{name}")
    public Result downloadFile(@PathVariable("name") String fileName, final HttpServletResponse response, final HttpServletRequest request){
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(rootPath + fileName + ".zip","utf-8"));
            //读取流
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(rootPath + fileName + ".zip"));
                compress(new File(rootPath + fileName), new ZipOutputStream(fos), fileName, true);
            } catch (Exception e) {
                return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
            }finally {
                try {
                    fos.close();
                }catch (Exception e){
                    return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
                }
            }
            File f = new File(rootPath+ fileName + ".zip");
            is = new FileInputStream(f);
            if (is == null) {
                return new Result(false, StateCode.ERROR, "下载附件失败，请检查文件“" + fileName + "”是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
            }
        }
        return null;
    }
//    @RequestMapping("/download/{name}")
//    public Result downloadFile(@PathVariable("name") String fileName, final HttpServletResponse response, final HttpServletRequest request){
//        OutputStream os = null;
//        InputStream is= null;
//        try {
//            // 取得输出流
//            os = response.getOutputStream();
//            // 清空输出流
//            response.reset();
//            response.setContentType("application/zip");
//            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(rootPath + fileName + ".zip","utf-8"));
//            //读取流
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(new File(rootPath + fileName + ".zip"));
//                compress(new File(rootPath + fileName), new ZipOutputStream(fos), fileName, true);
//            } catch (Exception e) {
//                return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
//            }finally {
//                try {
//                    fos.close();
//                }catch (Exception e){
//                    return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
//                }
//            }
//            File f = new File(rootPath+ fileName + ".zip");
//            is = new FileInputStream(f);
//            if (is == null) {
//                return new Result(false, StateCode.ERROR, "下载附件失败，请检查文件“" + fileName + "”是否存在");
//            }
//            //复制
//            IOUtils.copy(is, response.getOutputStream());
//            response.getOutputStream().flush();
//        } catch (IOException e) {
//            return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
//            }
//            try {
//                if (os != null) {
//                    os.close();
//                }
//            } catch (IOException e) {
//                return new Result(false, StateCode.ERROR, "下载失败", e.getMessage());
//            }
//        }
//        return null;
//    }

    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure)
            throws Exception {

        byte[] buf = new byte[2048];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }
}
