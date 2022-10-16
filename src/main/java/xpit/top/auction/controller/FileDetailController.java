package xpit.top.auction.controller;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "文件")
public class FileDetailController {

    @Autowired
    private FileStorageService fileStorageService;//注入实列

    /**
     * 上传文件到指定存储平台，成功返回文件信息
     */
    @ApiOperation(value = "上传文件", consumes = "multipart/form-data")
    @PostMapping(value = "/upload-platform", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileInfo uploadPlatform(@RequestPart("file") MultipartFile file) {
        return fileStorageService.of(file)
                .setPlatform("qiniu-kodo-1")    //使用指定的存储平台
                .upload();
    }
}
