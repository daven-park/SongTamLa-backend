package com.daven_park.SongTamLa.domain.timeline;

import com.daven_park.SongTamLa.domain.video.Video;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "timeline_segment")
public class TimelineSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "segment_id")
    private Long segmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(name = "start_seconds", nullable = false)
    private Integer startSeconds;

    @Column(name = "end_seconds", nullable = false)
    private Integer endSeconds;

    @Column(name = "track_title", nullable = false, length = 300)
    private String trackTitle;

    @Column(name = "artist_name", nullable = false, length = 200)
    private String artistName;

    @Column(name = "album_name", length = 200)
    private String albumName;

    //각 음원의 레코딩 자체를 식별하는 국제 표준 코드
    @Column(name = "isrc_code", length = 50)
    private String isrcCode;

    @Column(name = "confidence")
    private Double confidence;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public TimelineSegment(
            Video video,
            Integer startSeconds,
            Integer endSeconds,
            String trackTitle,
            String artistName,
            String albumName,
            String isrcCode,
            Double confidence
    ) {
        this.video = video;
        this.startSeconds = startSeconds;
        this.endSeconds = endSeconds;
        this.trackTitle = trackTitle;
        this.artistName = artistName;
        this.albumName = albumName;
        this.isrcCode = isrcCode;
        this.confidence = confidence;
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

}
