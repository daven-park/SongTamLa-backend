package com.daven_park.SongTamLa.domain.timeline;

import com.daven_park.SongTamLa.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineSegmentRepository extends JpaRepository<TimelineSegment, Long> {
    List<TimelineSegment> findByVideo(Video video);
}
