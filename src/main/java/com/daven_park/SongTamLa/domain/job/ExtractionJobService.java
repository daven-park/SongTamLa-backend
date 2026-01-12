package com.daven_park.SongTamLa.domain.job;

import com.daven_park.SongTamLa.domain.video.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtractionJobService  {

    private final ExtractionJobRepository extractionJobRepository;

    @Transactional
    public ExtractionJob createJob(Video video) {
        ExtractionJob job = ExtractionJob.builder()
                .video(video)
                .status(ExtractionJob.ExtractionStatus.PENDING)
                .message("Job created")
                .build();
        return extractionJobRepository.save(job);
    }

    @Transactional
    public ExtractionJob updateStatus(Long jobId,
                                      ExtractionJob.ExtractionStatus status, String message) {
        ExtractionJob job = extractionJobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        job.updateStatus(status, message);
        return job;
    }

    @Transactional(readOnly = true)
    public List<ExtractionJob> getJobsByVideo(Video video){
        return extractionJobRepository.findByVideo(video);
    }
}
