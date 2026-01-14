# SongTamLa

> 비디오 음악 추출 타임라인 프로젝트

송탐라는 영상파일에서 진행되는 음악을 유추하고 추출하여 타임라인 형태로 출력하는 프로젝트입니다.
주로 음악 라이브 스트리밍에서 송출되는 음악에 대한 정보와 함께 추후 해당 비디오 파일을 편리하게 편집하기 위한 용도입니다.

## 아키텍쳐



## commit convention

```
feat : 함수 기능
docs : 문서 수정
chore : 환경 설정 및 파일 변경
refactor : 코드 리팩터링
style : css 및 스타일 변경
test : 테스트 코드 작성
```

## 프로젝트 데이터 플로우
1. 비디오 업로드 및 유튜브 링크 첨부
2. ffmpeg로 음성파일 추출
3. ai api를 통해 음성파일에서 음악 유추
4. 음악 시작 시간 타임라인 클라이언트 표시

## 챌린지
1. 대용량 비디오 파일 처리 방식
2. 정확도, 신뢰도 향상 처리
3. 저비용 배포 및 운영


## 기능 개발 진행사항
- [X] 비디오 필수 기능 엔터티
  - Video 엔터티
  - ExtractionJob 엔터티
  - TimelineSegment 엔터티
- [X] 필수 api 로직 구현
  - VideoRepository
  - ExtractionJobRepository
  - TimelineSegmentRepository
- [ ] DB 연동
  - local postgresql test
- [ ] 음악 유추 및 추출 api 연동
- [ ] 클라우드 배포
- [ ] ci/cd 파이프라인 구축
- [ ] 로그인, 회원가입 기능 구현
- 