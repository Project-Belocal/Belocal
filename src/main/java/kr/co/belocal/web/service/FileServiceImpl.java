package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.ProfileImage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public void fileSave(MultipartFile uploadFile) {
        System.out.println("uploadFile = " + uploadFile.getOriginalFilename());
        System.out.println("uploadFile.getSize() = " + uploadFile.getSize());
        System.out.println("uploadFile.getContentType() = " + uploadFile.getContentType());

        String uploadFolder = "D:\\Project\\Spring-Project\\src\\main\\resources\\public\\profileImg";

        //폴더 날짜 경로
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        String datePath = str.replace("-", File.separator);

        /* 폴더 생성 */
        File uploadPath = new File(uploadFolder, datePath);

        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }


        /* 파일 이름 */
        String uploadFileName = uploadFile.getOriginalFilename();

        /* uuid 적용 파일 이름 */
        String uuid = UUID.randomUUID().toString();

        uploadFileName = uuid + "_" + uploadFileName;

        ProfileImage profileImage = ProfileImage
                .builder()
                .name(uploadFileName)
                .path(datePath)
                .uuid(uuid)
                .build();

        /* 파일 위치, 파일 이름을 합친 File 객체 */
        File saveFile = new File(uploadPath, uploadFileName);

        /* 파일 저장 */
        try {
            uploadFile.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
