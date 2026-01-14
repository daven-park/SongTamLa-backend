package com.daven_park.SongTamLa.domain.video;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "source_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(name = "original_video_path", length = 500)
    private String originalVideoPath;

    @Column(name = "audio_file_path", length = 500)
    private String audioFilePath;

    @Column(name = "youtube_url", length = 500)
    private String youtubeUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Video(SourceType sourceType,
                 String originalVideoPath,
                 String audioFilePath,
                 String youtubeUrl,
                 Integer durationSeconds,
                 VideoStatus status,
                 String errorMessage) {
        this.sourceType = sourceType;
        this.originalVideoPath = originalVideoPath;
        this.audioFilePath = audioFilePath;
        this.youtubeUrl = youtubeUrl;
        this.durationSeconds = durationSeconds;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public enum SourceType { UPLOAD, YOUTUBE }
    public enum VideoStatus { PENDING, AUDIO_EXTRACTED, FAILED }
}
