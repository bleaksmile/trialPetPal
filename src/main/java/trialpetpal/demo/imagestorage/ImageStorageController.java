/*
package trialpetpal.demo.imagestorage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import trialpetpal.demo.exception.FileStorageException;

@RestController
public class ImageStorageController {
  private ImageStorageService imageStorageService;

  public ImageStorageController(ImageStorageService imageStorageService) {
    this.imageStorageService = imageStorageService;
  }

  @PostMapping("/uploadFile")
  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws FileStorageException {
    String fileName = imageStorageService.storeFile(file);
    return ResponseEntity.ok().body(String.format("%s successfully uploaded ", fileName));
  }
}
*/
