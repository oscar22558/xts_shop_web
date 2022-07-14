package com.xtsshop.app.controller.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("http.service.Util")
public class Util {
    public String getExtension(MultipartFile file){
        try{
            String[] splittedStr = file.getOriginalFilename().split("[.]");
            if (splittedStr.length > 0){
                return splittedStr[splittedStr.length - 1];
            }else{
                return null;
            }
        }catch (Exception ex){
            return null;
        }

    }
}
