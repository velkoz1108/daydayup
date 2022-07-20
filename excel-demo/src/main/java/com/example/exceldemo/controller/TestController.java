package com.example.exceldemo.controller;

import com.example.exceldemo.util.ExcelExport;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    /**
     * description: excel 数据导出
     *
     * @return String
     * @throws IOException
     * @version v1.0
     * @author w
     * @date 2020年3月31日 下午2:41:03
     */
    @RequestMapping(value = "/export")
    public String export(HttpServletResponse response) throws IOException {
        System.out.println("------调用export");

        String[] headers = new String[]{"姓名", "年龄", "级别"};
        List<List<Object>> dataList = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            List<Object> data = new ArrayList<Object>();
            data.add("姓名ssssss" + x);
            data.add(18 + x);
            data.add("级别" + x);
            dataList.add(data);
        }
        try {
            ExcelExport.export("用户数据", headers, dataList, "poi导出模板.xlsx", response);
            return "导出成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导出失败 --";
    }


    /**
     * description: excel 数据导出
     *
     * @return String
     * @throws IOException
     * @version v1.0
     * @author w
     * @date 2020年3月31日 下午2:41:03
     */
    @RequestMapping(value = "/export2")
    public Map export2(HttpServletResponse response) throws IOException {
        System.out.println("------调用export2");
        String[] headers = new String[]{"姓名", "年龄", "级别"};
        List<List<Object>> dataList = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            List<Object> data = new ArrayList<Object>();
            data.add("姓名ssssss" + x);
            data.add(18 + x);
            data.add("级别" + x);
            dataList.add(data);
        }
        String fileName = "单台价格";
        System.out.println("------创建临时文件");
        File destFile = File.createTempFile(fileName + System.currentTimeMillis(), ".xlsx");
        try {
            System.out.println("文件目录：" + destFile.getAbsolutePath() + "；文件大小" + destFile.length());
            ExcelExport.export("用户数据", headers, dataList, destFile);
            System.out.println("写完文件大小：" + destFile.length());

            MultipartFile multipartFile = fileToMultipartFile(destFile);

            System.out.println("multipartFile:" + multipartFile.getSize());

            Map<String, Object> map = new HashMap<>();
            map.put("allSize", "100");
            map.put("successSize", "50");
            map.put("errorSize", "50");
            map.put("file", destFile);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            destFile.delete();
        }
        return null;
    }


    private static MultipartFile fileToMultipartFile(File file) throws Exception {
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        return multipartFile;
    }


    /**
     * description: excel 数据导出
     *
     * @return String
     * @throws IOException
     * @version v1.0
     * @author w
     * @date 2020年3月31日 下午2:41:03
     */
    @RequestMapping(value = "/export3")
    public Map export3(HttpServletResponse response) throws IOException {
        System.out.println("------调用export3");
        String[] headers = new String[]{"姓名", "年龄", "级别"};
        List<List<Object>> dataList = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            List<Object> data = new ArrayList<Object>();
            data.add("姓名ssssss" + x);
            data.add(18 + x);
            data.add("级别" + x);
            dataList.add(data);
        }

        try {
            byte[] bytes = ExcelExport.export("用户数据", headers, dataList);


            System.out.println("bytes length:" + bytes.length);
            System.out.println("bytes:" + bytes);

            Map<String, Object> map = new HashMap<>();
            map.put("allSize", "100");
            map.put("successSize", "50");
            map.put("errorSize", "50");
            map.put("file", bytes);

            convertToFile(bytes);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            destFile.delete();
        }
        return null;
    }


    private void convertToFile(byte[] bytes) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            File file = File.createTempFile("单台价格"+System.currentTimeMillis(),".xlsx");
            System.out.println("位置："+file.getAbsolutePath());
            System.out.println("初始大小："+file.length());

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            System.out.println("最终大小："+file.length());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
