package org.sparta.createschedule.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.sparta.createschedule.dto.UploadResultDto;
import org.sparta.createschedule.exception.ErrorStatus;
import org.sparta.createschedule.exception.ScheduleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@Log4j2
public class FileController {

  @Value("${file.upload/path}")
  private String uploadPath;

  @PostMapping("/uploadAjax")
  public ResponseEntity<List<UploadResultDto>> uploadFile(MultipartFile[] uploadFiles) {

    List<UploadResultDto> resultDTOList = new ArrayList<>();

    for (MultipartFile uploadFile : uploadFiles) {

      if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
        log.warn("this file is not image type");
        throw new ScheduleException(ErrorStatus.FILE_NOT_IMAGE);
      }

      String orginalName = uploadFile.getOriginalFilename();
      assert orginalName != null;
      String fileName = orginalName.substring(orginalName.lastIndexOf("\\") + 1);

      log.info("fileName: " + fileName);

      // 날짜 폴더 생성
      String folderPath = makeFolder();

      // UUID
      String uuid = UUID.randomUUID().toString();

      // 저장할 파일 이름 중간에 "_"를 이용해서 구현
      String saveName =
          uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

      Path savePath = Paths.get(saveName);

      try {
        uploadFile.transferTo(savePath); // 실제 이미지 저장
        resultDTOList.add(new UploadResultDto(fileName, uuid, folderPath));

      } catch (IOException e) {
        throw new ScheduleException(ErrorStatus.IMAGE_SAVE_FAIL);
      }

    }
    return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
  }

  // 날짜 폴더 생성
  private String makeFolder() {

    String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    String folderPath = str.replace("/", File.separator);

    File uploadPathFolder = new File(uploadPath, folderPath);

    if (!uploadPathFolder.exists()) {
      boolean mkdirs = uploadPathFolder.mkdirs();
      log.info("-------------------makeFolder------------------");
      log.info("uploadPathFolder.exists(): " + uploadPathFolder.exists());
      log.info("mkdirs: " + mkdirs);
    }

    return folderPath;

  }
}
