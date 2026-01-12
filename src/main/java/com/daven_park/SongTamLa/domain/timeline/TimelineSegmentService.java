package com.daven_park.SongTamLa.domain.timeline;

import com.daven_park.SongTamLa.domain.video.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineSegmentService {
    private final TimelineSegmentRepository timelineSegmentRepository;

    @Transactional
    public TimelineSegment createSegment(Video video, Integer startSeconds, Integer endSeconds,
                                         String trackTitle, String artistName, String albumName,
                                         String isrcCode, Double confidence){
        TimelineSegment segment = TimelineSegment.builder()
                                    .video(video)
                                    .startSeconds(startSeconds)
                                    .endSeconds(endSeconds)
                                    .trackTitle(trackTitle)
                                    .artistName(artistName)
                                    .albumName(albumName)
                                    .isrcCode(isrcCode)
                                    .confidence(confidence)
                                    .build();

        return timelineSegmentRepository.save(segment);
    }

    @Transactional
    public List<TimelineSegment> createSegments(Video video, List<TimelineSegment> segments){
        for (TimelineSegment seg : segments){
//            seg.setVideo(video);
        }
        return timelineSegmentRepository.saveAll(segments);
    }

    @Transactional(readOnly = true)
    public List<TimelineSegment> getTimelineByVideo(Video video){
        return timelineSegmentRepository.findByVideoOrderByStartSecondsAsc(video);
    }

    @Transactional
    public void deleteTImelineByVideo(Video video){
        List<TimelineSegment> segments = timelineSegmentRepository.findByVideo(video);
        timelineSegmentRepository.deleteAll(segments);
    }
}
