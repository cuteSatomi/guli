package com.zzx.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzx
 * @date 2021-04-13 11:48:48
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
