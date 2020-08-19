# Backend Construction

## Directory

```python
SNS_Backend/
	src/main/java
    	com.saffy.pjt1/
    		CustomMailSender.java
    		SnsBackendApplication.java
    
    	com.saffy.pjt1.component/
    		JwtInterceptor.java
    
    	com.saffy.pjt1.config/
    		SwaggerConfig.java
    		WebConfig.java
    		WebSocketConfig.java
    
    	com.saffy.pjt1.controller/			# 컨트롤러
    		AlarmController.java
    		AuthController.java
    		ChatController.java
    		FollowController.java
    		LikeController.java
    		MatchingController.java
    		PostController.java
    		ReplyController.java
    		SearchController.java
    		TeamController.java
    		UserController.java
    		
    	com.saffy.pjt1.dao/					# Dao
    		AlarmDao.java
    		AuthDao.java
    		ChatDao.java
    		FilesDao.java
    		PostDao.java
    		PostLikeDao.java
    		PostTagDao.java
    		ProfileDao.java
    		ReplyDao.java
    		TagDao.java
    		TagFollowDao.java
    		TeamDao.java
    		UserDao.java
    		UserFollowDao.java
    		
    	com.saffy.pjt1.dto/					# Dto
    		Ability.java
    		Alarm.java
    		Auth.java
    		Chat.java
    		Files.java
    		Greeting.java
    		HelloMessage.java
    		Post.java
    		PostLike.java
    		PostTag.java
    		Profile.java
    		Reply.java
    		Tag.java
    		TagFollow.java
    		User.java
    		UserFollow.java
    
    	com.saffy.pjt1.exception/
    		UnauthorizedException.java
    
    	com.saffy.pjt1.model/				# Model
    		AlarmResponse.java
    		AuthenticationRequest.java
    		BasicResponse.java
    		FeedData.java
    		FeedDetailData.java
    		FeedResponse.java
    		FollowList.java
    		HashAllData.java
    		HashSearchResponse.java
    		LoginRequest.java
    		MatchingData.java
    		MatchingMemberData.java
    		MyAlarm.java
    		MyPageData.java
    		PersonData.java
    		PostRequest.java
    		RecommendUser.java
    		ReplyData.java
    		ReplyRequest.java
    		SignupRequest.java
    		TeamData.java
    		TeamPersonData.java
    
    	com.saffy.pjt1.service/				# Service
    		AlarmService.java
    		AlarmServiceImpl.java
    		AuthService.java
    		AuthServiceImpl.java
    		ChatService.java
    		ChatServiceImpl.java
    		FilesService.java
    		FilesServiceImpl.java
    		FollowService.java
    		FollowServiceImpl.java
    		JwtService.java
    		JwtServiceImpl.java
    		LikeService.java
    		LikeServiceImpl.java
    		MatchingService.java
    		MatchingServiceImpl.java
    		PostService.java
    		PostServiceImpl.java
    		ProfileService.java
    		ProfileServiceImpl.java
    		ReplyService.java
    		ReplyServiceImpl.java
    		TeamService.java
    		TeamServiceImpl.java
    		UserService.java
    		UserServiceImpl.java
    
    src/main/resources/
    	static/
    	templates/
    	application.properties
    
    JRE System Library/
    Maven Dependencies/
    target
    Dockerfile
    mvnw
    mvnw.cmd
    pom.xml
```



## File Definition

### :black_medium_square: src/main/java/com.saffy.pjt1.component

| File 이름           | 기술                                              |
| ------------------- | ------------------------------------------------- |
| JwtInterceptor.java | 요청한 사용자의 token이 유효한지 아닌지 검사한다. |

### :black_medium_square: src/main/java/com.saffy.pjt1.config

| File 이름            | 기술                                                         |
| -------------------- | ------------------------------------------------------------ |
| SwaggerConfig.java   | 테스트를 위한 Swagger와 연결한다.                            |
| WebConfig.java       | 보안 관련 configuration. 요청이 왔을 때 사용자를 인증한다.   |
| WebSocketConfig.java | 실시간 채팅 기능을 위해 서버와 클라이언트 간의 실시간 통신을 한다. |

### :black_medium_square: src/main/java/com.saffy.pjt1.controller

> MVC 패턴의 C(Controller)부분. 브라우저에서 보여줄 View에 정보를 전달한다.

| File 이름               | 기술                                                         |
| ----------------------- | ------------------------------------------------------------ |
| AlarmController.java    | 알림 요청을 받아 처리한 후 Front로 정보를 전달한다.          |
| AuthController.java     | 회원 가입 및 로그인 인증 등의 관련 요청을 받아 처리한 후 Front로 정보를 전달한다. |
| ChatController.java     | 실시간 채팅 요청을 받아 처리한 후 Front로 정보를 전달한다.   |
| FollowController.java   | 팔로우 요청을 받아 처리한 후 Front로 정보를 전달한다.        |
| LikeController.java     | 좋아요 요청을 받아 처리한 후 Front로 정보를 전달한다.        |
| MatchingController.java | 팀원 매칭 요청을 받아 처리한 후 Front로 정보를 전달한다.     |
| PostController.java     | 뉴스피드의 게시글 요청을 받아 처리한 후 Front로 정보를 전달한다. |
| ReplyController.java    | 뉴스피드 게시글의 댓글 요청을 받아 처리한 후 Front로 정보를 전달한다. |
| SearchController.java   | 검색 요청을 받아 처리한 후 Front로 정보를 전달한다.          |
| TeamController.java     | 프로젝트 팀 요청을 받아 처리한 후 Front로 정보를 전달한다.   |
| UserController.java     | 유저 정보와 관련된 요청을 받아 처리한 후 Front로 정보를 전달한다. |

### :black_medium_square: src/main/java/com.saffy.pjt1.`dao` & `dto`

> DAO(Data Access Object): DB를 사용해 데이터를 조회하거나 조작하는 기능
>
> DTO(Data Transfer Object): DB에서 Data를 얻어 Service나 Controller 등으로 보낼 때 사용하는 객체. 즉, 각 계층간 데이터 교환을 위한 자바빈즈

### :black_medium_square: src/main/java/com.saffy.pjt1.exception

| File 이름                  | 기술                    |
| -------------------------- | ----------------------- |
| UnauthorizedException.java | 계정 권한 유효성을 확인 |

### :black_medium_square: src/main/java/com.saffy.pjt1.model

> DB model의 필드 형식 지정

### :black_medium_square: src/main/java/com.saffy.pjt1.service

> 비지니스 로직이 들어가는 부분으로 DAO로 데이터베이스를 접근하고, DTO로 데이터를 전달받은 다음, 적절한 처리해서 반환

### :black_medium_square: src/main/resources

| File 이름              | 기술                                       |
| ---------------------- | ------------------------------------------ |
| application.properties | application 구동 시 자동으로 로딩하는 파일 |



## Reference

- [JPA 사용법](https://jobc.tistory.com/120) / [JPA CRUD](https://wonit.tistory.com/131?category=738059) / [JPA - MariaDB](https://siyoon210.tistory.com/25)
- [JPA 팔로우](http://blog.naver.com/PostView.nhn?blogId=codingspecialist&logNo=221550194774) / [JPA N:M](https://ict-nroo.tistory.com/127)
- [이메일 인증 메일 보내기](https://rooted.tistory.com/2)
- [jsonignore 작동 관련](https://cnpnote.tistory.com/entry/SPRING-Spring-JsonIgnore가-작동하지-않음)
- [vue + spring boot 파일 업로드](https://kwongyo.tistory.com/34)
- [JPA](http://blog.naver.com/PostView.nhn?blogId=rorean&logNo=221479709787&categoryNo=11&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=search)
- [JWT 토큰 인증](https://goodteacher.tistory.com/97)
- [JPQL 블로그](https://github.com/sujinhope/SHARFY_SSAFY_SNS/blob/master/문서/[Backend] JPA%2C JPQL 사용법.md)
- [spring 알람기능](https://web-inf.tistory.com/22)
- [spring with docker](https://spring.io/guides/gs/spring-boot-docker/)
- [Docker-compose.yml DB연결 블로그 글](https://velog.io/@melangyun/Docker-compose.yml-DB와-연결)
- [같은 포트 파일 분리 블로그 글](https://medium.com/@donggyu9410/nginx-같은-포트에서-각기-다른-파일-보여주기-d8793b96bbe3)
- [CORS 블로그 글](velog.io/@wlsdud2194/cors)
- [Websocket github 블로그 글](supawer0728.github.io/2018/03/30/spring-websocket)