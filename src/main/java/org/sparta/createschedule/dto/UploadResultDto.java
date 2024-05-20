package org.sparta.createschedule.dto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UploadResultDto {

  private String fileName;
  private String uuid;
  private String folderPath;

  public String getImageURL() {
    return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, StandardCharsets.UTF_8);
  }
}
