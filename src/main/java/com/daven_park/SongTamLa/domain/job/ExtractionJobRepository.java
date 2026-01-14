package com.daven_park.SongTamLa.domain.job;

import com.daven_park.SongTamLa.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtractionJobRepository extends JpaRepository<ExtractionJob, Long> {
    List<ExtractionJob> findByVideo(Video video);
}
