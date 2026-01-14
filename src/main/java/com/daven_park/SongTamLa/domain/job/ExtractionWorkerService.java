package com.daven_park.SongTamLa.domain.job;

import com.daven_park.SongTamLa.domain.timeline.TimelineSegment;
import com.daven_park.SongTamLa.domain.timeline.TimelineSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;

@Service
@RequiredArgsConstructor
public class ExtractionWorkerService {
    private final ExtractionJobRepository extractionJobRepository;
    private final TimelineSegmentRepository timelineSegmentRepository;

    @Transactional
    public void process(long jobId) {
        ExtractionJob job = extractionJobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalStateException("job not found: " + jobId));

        // 멱등성 방어
        if (job.getStatus() != ExtractionJob.ExtractionStatus.PENDING){
            return;
        }
        job.updateStatus(ExtractionJob.ExtractionStatus.PROCESSING, "job running");

        TimelineSegment seg = TimelineSegment.create(job.getVideo(), 0, 10, "DUMMY", "DUMMY");
        timelineSegmentRepository.save(seg);
        job.updateStatus(ExtractionJob.ExtractionStatus.DONE, "job Completed");
    }
}
