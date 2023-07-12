package kr.co.belocal.web.service;




import com.google.cloud.storage.*;
import kr.co.belocal.web.entity.ProfileImage;
import kr.co.belocal.web.repository.MemberRepository;
import kr.co.belocal.web.service.security.MemberDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService{

    @Value("${spring.cloud.gcp.storage.bucket}")// application.yml에 써둔 bucket 이름
    private String bucketName;

    @Autowired
    private MemberRepository memberRepository;

    private Storage storage;


    @Override
    public void profileSave(MultipartFile uploadFile,Integer memberId) throws IOException {

        System.out.println("uploadFile = " + uploadFile.isEmpty());
        String uuid;
        if (uploadFile.isEmpty()) {
            uuid = "/images/icon/user.svg";
        } else {
            Storage storage = StorageOptions.getDefaultInstance().getService();
            uuid = UUID.randomUUID().toString();
            BlobId blobId = BlobId.of(bucketName, uuid);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(uploadFile.getContentType())
                    .build();
            Blob blob = storage.create(blobInfo, uploadFile.getBytes());
        }


        //js에서 cloud로 값을보내고 js로 응답 -> 응답한 값을 서버로 전송 고려하기

        ProfileImage profileImage = ProfileImage
                .builder()
                .memberId(memberId)
                .name(uploadFile.getOriginalFilename())
                .uuid(uuid)
                .path(bucketName)
                .build();

        memberRepository.updateProfileImg(profileImage);

    }

}
