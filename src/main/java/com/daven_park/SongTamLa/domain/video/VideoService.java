package com.daven_park.SongTamLa.domain.video;

import com.daven_park.SongTamLa.domain.job.ExtractionJob;
import com.daven_park.SongTamLa.domain.job.ExtractionJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final ExtractionJobService extractionJobService;

    @Transactional
    public Video createUploadVideo(MultipartFile file) {

        // TODO : 파일 실제 저장 설정하기(S3, MinIO)
        String videoFilePath = "/tmp/" + file.getOriginalFilename();

        Video video = Video.builder()
                .sourceType(Video.SourceType.UPLOAD)
                .originalVideoPath(videoFilePath)
                .audioFilePath(null)
                .youtubeUrl(null)
                .durationSeconds(null)
                .status(Video.VideoStatus.PENDING)
                .errorMessage(null)
                .build();

        Video savedVideo = videoRepository.save(video);

        extractionJobService.createJob(savedVideo);
        return savedVideo;
    }

    @Transactional
    public Video createYoutubeVideo(String youtubeUrl) {
        Video video = Video.builder()
                .sourceType(Video.SourceType.YOUTUBE)
                .originalVideoPath(null)
                .audioFilePath(null)
                .youtubeUrl(youtubeUrl)
                .durationSeconds(null)
                .status(Video.VideoStatus.PENDING)
                .errorMessage(null)
                .build();

        Video savedVideo = videoRepository.save(video);

        extractionJobService.createJob(savedVideo);
        return savedVideo;
    }

    @Transactional
    public Video getVideo(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found" + videoId));
    }

}
