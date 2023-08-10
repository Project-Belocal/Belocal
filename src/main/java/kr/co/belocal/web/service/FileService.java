package kr.co.belocal.web.service;

import kr.co.belocal.web.service.security.MemberDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void profileSave(MultipartFile uploadFile, Integer memberId) throws IOException;


}
