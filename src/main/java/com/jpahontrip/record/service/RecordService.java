package com.jpahontrip.record.service;

import com.jpahontrip.record.domain.RecordBoard;
import com.jpahontrip.record.repository.RecordBoardRepository;
import com.jpahontrip.record.repository.RecordCommentRepository;
import com.jpahontrip.record.repository.RecordLikeRepository;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final ServletContext servletContext;

    private final RecordBoardRepository recordBoardRepository;
    private RecordCommentRepository recordCommentRepository;
    private RecordLikeRepository recordLikeRepository;

    private String relativePath = "resources/img/"; // 파일 저장 루트

    public long upLoadPost(MultipartFile file, RecordBoard recordBoard) {
        String originalFileName = file.getOriginalFilename();

        // 파일 저장 루트와 UUID를 사용하여 파일 이름 고유성 보장
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String savedName = uuid + extension;

        String uploadPath = servletContext.getRealPath("/")+relativePath+savedName;
        System.out.println(uploadPath);
        File target = new File(uploadPath); //해당 주소에 이미지 저장
        try {
            file.transferTo(target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recordBoard.setThumbnail(relativePath+savedName);
        recordBoardRepository.save(recordBoard);
        return recordBoard.getId();
    }
}
