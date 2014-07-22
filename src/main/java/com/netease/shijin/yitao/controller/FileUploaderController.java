package com.netease.shijin.yitao.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.netease.shijin.yitao.bean.ResponseBean;

@Controller
@RequestMapping("/item/uploadImg")
public class FileUploaderController {
    
    private String imgServer = "http://10.242.65.171:8080";
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseBean processUpload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        ResponseBean response = new ResponseBean();
        String path = request.getSession().getServletContext().getRealPath("image");
        String fileName = new Date().getTime() + ".png";
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);
        String imgURL = imgServer + request.getContextPath() + "/image" + fileName;
        response.setCode(200);
        Map<String, String> data = new HashMap<String, String>();
        data.put("picid", imgURL);
        response.setData(data);
        return response;
    }
}
