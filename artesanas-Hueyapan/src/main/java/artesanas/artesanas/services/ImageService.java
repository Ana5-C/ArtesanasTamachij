// package artesanas.artesanas.services;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.net.URLEncoder;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.util.UUID;

// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.google.auth.Credentials;
// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.cloud.storage.BlobId;
// import com.google.cloud.storage.BlobInfo;
// import com.google.cloud.storage.Storage;
// import com.google.cloud.storage.StorageOptions;

// @Service
// public class ImageService {

//     public String upload(MultipartFile multipartFile) {
//         try {
//             String fileName = multipartFile.getOriginalFilename();
//             fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
//             File file = this.convertToFile(multipartFile, fileName);
//             String URL = this.uploadFile(file, fileName);
//             file.delete();
//             return URL;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "The image could not be uploaded";
//         }
//     }

//     private String getExtension(String fileName) {
//         return fileName.substring(fileName.lastIndexOf("."));
//     }

//     private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
//         File tempFile = new File(fileName);
//         try (FileOutputStream fos = new FileOutputStream(tempFile)) {
//             fos.write(multipartFile.getBytes());
//             fos.close();
//         }
//         return tempFile;
//     }

//     private String uploadFile(File file, String fileName) throws IOException {
//         BlobId blobId = BlobId.of("artesaniash-f205f.appspot.com", fileName);
//         BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
//         InputStream inputStream = ImageService.class.getClassLoader().getResourceAsStream("artesaniash-f205f-firebase");
//         Credentials credentials = GoogleCredentials.fromStream(inputStream);
//         Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//         storage.create(blobInfo, Files.readAllBytes(file.toPath()));

//         String downloadURL = "https://firebasestorage.googleapis.com/v0/b/<artesaniash-f205f.appspot.com>/o/%s?alt=media";
//         return String.format(downloadURL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
//     }

// }