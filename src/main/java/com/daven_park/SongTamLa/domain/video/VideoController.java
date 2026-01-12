package com.daven_park.SongTamLa.domain.video;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videos")
@CrossOrigin("http://localhost:5173")
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<Video> uploadVideo(@RequestParam("file") MultipartFile file){
        System.out.println("video upload" + file);
        Video video = videoService.createUploadVideo(file);
        return ResponseEntity.ok(video);
    }

    @PostMapping("/youtube")
    public ResponseEntity<Video> createYoutubeVideo(@RequestParam("url") String youtubeUrl){
        Video video = videoService.createYoutubeVideo(youtubeUrl);
        return ResponseEntity.ok(video);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<Video> getVideo(@PathVariable Long videoId){
        Video video = videoService.getVideo(videoId);
        return ResponseEntity.ok(video);
    }
}
