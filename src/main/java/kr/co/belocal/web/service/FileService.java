package kr.co.belocal.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void fileSave(MultipartFile uploadFile);
}
