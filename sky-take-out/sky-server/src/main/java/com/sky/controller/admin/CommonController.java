package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 通用接口
 *
 * @author Zhaohangyi
 * @time 2023/9/4
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);
        try {
            // 获取文件的原始名称
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            // 指定文件保存的相对目录
            String uploadDir = "sky-server/src/main/resources/static/";
            // 获取当前工作目录的绝对路径
            String currentWorkingDir = System.getProperty("user.dir");
            // 创建保存文件的目标路径
            String filePath = currentWorkingDir + "/" + uploadDir + originalFilename;
            // 将文件保存到指定路径
            file.transferTo(new File(filePath));
            log.info("文件的目标路径:{}",filePath);
            return Result.success(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

    }
}
