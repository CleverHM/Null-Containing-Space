## 2020. 07. 23

- 조대흠
  - 유저 해쉬태그 팔로우 기능(Spring)
  - 해쉬태그사용한 유저 찾기 기능(Spring)
  - 유저 게시물 올리기 기능(Spring)

- 박상현

  - 실시간 채팅 기능 backend 구현(Spring)

  - 실시간 채팅 기능 frontend 구현(vue)

  - 실시간 채팅방 개수 증가 구현중.
- 김호한
  - Tag 엔티티 설계
  
  - post 엔티티 설계
  
    

## 2020. 07. 24

- 조대흠
  - 이미지 엔티티, 태그 엔티티, 게시물 엔티티 설계
  - 태그 엔티티 vs 게시물 엔티티 (N:N 관계)
  - 이미지 엔티티 vs 게시물 엔티티(1:1관계) 
  
- 박상현

  - 실시간 채팅 기능 backend 구현(Spring)
  - 실시간 채팅방 기능 backend 구현(Spring)
- 김호한
  - 회원가입 이메일 체크 기능 구현(Spring)
  - 비밀번호 찾기 이메일 체크 기능 구현(Spring)
  - 비밀번호 찾기 이메일 전송 구현(Spring) 
  
  

## 2020. 07. 27

- 조대흠
  - 뉴스피드 CRUD 중 C 구현완료(Spring)
- 박상현
  - Ability 엔티티 설계
  - Ability 엔티티 vs User 엔티티 (1:1 관계)
  - 팀원 매칭 알고리즘 설계 중
- 김호한
  - Token Create, 인증 부분 개발중 (Spring)



## 2020. 07. 28

- 조대흠
  - 로그인 토큰, 뉴스피드 작성 마무리.
- 박상현
  - 팀원 매칭 알고리즘 설계 및 1차 코딩 완료(Spring, Hash 알고리즘)
- 김호한
  - 로그인 토큰 backend 완성(JWT Spring)

## 2020. 07. 29

- 조대흠
  - 회원 삭제 기능(테이블 꼬여서 수정.)
- 박상현
  - 회원 삭제 Service, Dao 구현(Spring)
  - 회원 삭제 시, 겹치는 중계 테이블 처리(Spring)
- 김호한
  - 뉴스피드 게시물 팔로우/ 내 게시물 전송
  - 게시물 해쉬태크 눌렀을때  해당되는 해쉬태그로 정렬

## 2020. 07. 30

- 조대흠
  - 다대다 관계(유저 : 유저, 유저 : 태그,  게시물 : 태그) 를 일대다, 다대일로 분해.(Spring)
  - Cascade 속성을 이용해서, 원본이 삭제되면 참조값이 자동삭제되도록 엔티티 설계(Spring)
- 박상현
  - 좋아요 Dto, Dao, Service, Controller 구현(Spring)
  - 사용자의 팔로워, 팔로잉 개수를 보여주는 기능 구현(Spring)
  - 게시물의 좋아요 개수를 보여주는 기능 구현(Spring)
- 김호한
  - 게시물 조회수 기능 구현(Spring)
  - 게시물 데이터 전달 Model 기능 구현(Spring)



## 2020. 08. 03

- 조대흠
  - AWS에 도커설치.
  - 도커에 Maria-db 컨테이너 설치.
  - 도커에 스프링부트 jar 파일 업로드 작업중.
- 박상현
  - 댓글 Dto, Dao, Service, Controller 구현(Spring)
  - 댓글 C, D 구현(Spring)
  - 개인 능력치 Dto, Dao, Service, Controller 병합(Spring)
  - 회원정보 수정에서 프로필사진 추가 기능 구현(Spring)
- 김호한
  - 게시물 Vue 데이터 전달 (Spring)
  - 게시물 Update 기능 구현 (Spring)
  - 댓글수/회원 수정/댓글 UID 전달 구현 (Spring)

## 2020. 08. 04

- 조대흠
  - 서버 배포 작업 ing(현재 외부에서 ip주소로 접근불가능함)
- 박상현
  - MyPage Controller 구현(Spring)
  - User Dto 수정(Spring)
  - User삭제 Controller 수정(Spring)
- 김호한
  - 게시판 업데이트 사진 부분 완료 (Spring)
  - 해쉬태그를 이용한 게시물 정렬 기능 구현 완료 (Spring)
  - 프로필 사진 추가 (Spring)

## 2020. 08. 05

- 조대흠
  - 서버 배포 작업(도커)
- 박상현
  - 팀원 매칭 알고리즘 추가 설계 및 2차 코딩 중(Spring, Hash 알고리즘)
- 김호한
  - Team Create, Delete, join 구현(Spring)
  - 이메일 중복 체크 구현(Spring)

## 2020. 08. 07

- 조대흠
  - 서버 배포 작업
  - vue, spring, mariadb 이미지화
  - docker 컨테이너에 삽입
  - dockerfile 및 docker-compose.yml 파일 작성
  - docker를 통해 배포
- 박상현
  - 서버 배포 작업
  - vue, spring, mariadb 이미지화
  - docker 컨테이너에 삽입
  - dockerfile 및 docker-compose.yml 파일 작성
  - docker를 통해 배포
- 김호한
  - 팀원으로 등록하기 구현(Spring)
  - 팀이 없고 팀원으로 등록한경우 선호 프로젝트 띄우기 기능 구현(Spring)
  - 비밀번호 이전꺼랑 같으니지 체크 하는 기능 구현(Spring)
  - 램덤 유저 Controller 생성(Spring) -> 덜구현

## 2020. 08. 08

- 조대흠
- 박상현
  - MatchingController 오류 수정 및 vue와 연결(Spring)
- 김호한
  - 램덤 유저 Controller 생성 (Spring) 
  - 알람 Dto ,Dao, Service 구현 (Spring)
  - 알람 보내기, 내가 받은 알람 목록 보기 구현 (Spring)