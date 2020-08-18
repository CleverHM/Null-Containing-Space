# Frontend Construction

## Directory

```python
FRONTEND/
	public/
		favicon.ico				# favicon 이미지
		index.html				# 기본 template 화면
        
	src/
		api/
			UserApi.js			# server 통신
		assets/
			css/
			images/				# front image 폴더 - user default_image 포함
            
		components/				# component 파일 들어가는 디렉토리
			__tests__/
            chat/				# 실시간 채팅에 들어가는 components
            	chatMe.vue
                chatOther.vue
			common/				# 공통 디렉토리 (필요없는 부분 생략)
				Navigation.vue
				subnav.vue
			css/				# 생략
            notice/				# 알람에 들어가는 components
            	SNSAlarm.vue
                TeamAlarm.vue
            search/				# 검색에 들어가는 components
            	peopleItem.vue
                searchPostItem.vue
                tagItem.vue
			SNS/				# SNS에 들어가는 components
            	RecommendUser.vue
				SNSCommentItem.vue
                SNSItem.vue
            team/				# team에 들어가는 components
            	MatchUser.vue
                MatchUserSmall.vue
                memberImg.vue
                NoTeam.vue
                Spectification.vue
                TeamIn.vue
                TeamOut.vue
                TeamUser.vue
			user/				# 사용자 관련 components
                checkAbility.vue
                followItem.vue
                joinform.vue
				Logo.vue
                myPostItem.vue
                passwordform.vue
                profileAbility.vue
                step1.vue
                step2.vue
                
		util/
			http-common.js		# axios 요청
            
		views/					# 사용자에게 보여주기 위한 views
        	chat/				# 실시간 채팅
            	Chat.vue
            search/				# 검색창
            	SearchMain.vue
                TagResult.vue
			SNS/				# SNS feed 관련
            	CommentFeed.vue
                CreateFeed.vue
                DetailFeed.vue
				IndexFeed.vue
            team/				# Team 관련
            	createTeam.vue
                MatchMember.vue
                subjectForm.vue
                TeamInfo.vue
                teamMatch.vue
			user/				# user 관련
            	/join
                	getAbility.vue
                    step1.vue
                    step2.vue
                    step3.vue
                    step4.vue
                    step5.vue
				FindPassword.vue
                followinglist.vue
				Join.vue
				Login.vue
                modifyAbility.vue
                modifyPassword.vue
                myLikePost.vue
				profile.vue
                UserModify.vue
			Main.vue			# Main 화면
            Notice.vue
            
		vuex/					# 생략
		App.vue
		main.js
		routes.js
        
	.gitignore
	babel.config.js
	eslintrc.js
	jest.config.js
	package.json
	README.md
```



## File Definition

### :black_medium_square: 상위 컴포넌트

| File 이름          | 기술                                                         |
| ------------------ | ------------------------------------------------------------ |
| `App.vue`          | vue.js의 기본적인 App.vue를 정의합니다.                      |
| views/`Main.vue`   | 로그인 후 사용자가 보게 될 프로젝트 화면을 정의합니다.       |
| views/`Notice.vue` | 사용자 알림 화면을 정의합니다. ([알림 페이지 구조](./Alarm_Construction.md/#construction)) |

### :black_medium_square: src/components

- **src/components/chat**

  | File 이름       | 기술                                                         |
  | --------------- | ------------------------------------------------------------ |
  | `chatMe.vue`    | 실시간 채팅에서 내 채팅 부분의 component를 정의합니다.       |
  | `chatOther.vue` | 실시간 채팅에서 다른 유저의 채팅 부분을 component를 정의합니다. |

- **src/components/common**

  | File 이름        | 기술                                                |
  | ---------------- | --------------------------------------------------- |
  | `Navigation.vue` | 전체 화면의 상단 네비게이션 component를 정의합니다. |
  | `subnav.vue`     | 전체 화면의 하단 네비게이션 component를 정의합니다. |

- **src/components/notice**

  > [알림 페이지 파일 정의](./Alarm_Construction.md/#file-definition)

  | File 이름       | 기술                                             |
  | --------------- | ------------------------------------------------ |
  | `SNSAlarm.vue`  | 알림 창의 SNS 관련 알람 component를 정의합니다.  |
  | `TeamAlarm.vue` | 알림 창의 Team 관련 알람 component를 정의합니다. |

- **src/components/search**

  > [검색 페이지 파일 정의](./Search_Construction.md/#file-definition)

  | File 이름            | 기술                                          |
  | -------------------- | --------------------------------------------- |
  | `peopleItem.vue`     | 검색 결과 검색된 사람 component를 정의합니다. |
  | `searchPostItem.vue` | 검색 결과 게시글 component를 정의합니다.      |
  | `tagItem.vue`        | 검색 결과 tag component를 정의합니다.         |

- **src/components/SNS**

  > [뉴스피드 파일 정의](./SNSFeed_Construction.md/#file-definition)

  | File 이름            | 기술                                                       |
  | -------------------- | ---------------------------------------------------------- |
  | `RecommendUser.vue`  | 뉴스피드 추천인 component를 정의합니다.                    |
  | `SNSCommentItem.vue` | 뉴스피드 게시물 상세페이지의  댓글 component를 정의합니다. |
  | `SNSItem.vue`        | 뉴스피드에 들어갈 게시물 component를 정의합니다.           |

- **src/components/team**

  | File 이름            | 기술                                                         |
  | -------------------- | ------------------------------------------------------------ |
  | `MatchUser.vue`      | 매칭 결과 팀원 component를 정의합니다.                       |
  | `MatchUserSmall.vue` | 매칭 결과 팀원 모습만 보여주는 component를 정의합니다.       |
  | `memberImg.vue`      | 프로젝트 화면의 팀 멤버 component를 정의합니다.              |
  | `NoTeam.vue`         | 팀원으로 시작한 경우 화면을 정의합니다.                      |
  | `Specification.vue`  | 프로젝트 명세서 component를 정의합니다.                      |
  | `TeamIn.vue`         | 팀이 있는 경우 화면을 정의합니다.                            |
  | `TeamOut.vue`        | 팀이 없는 경우 화면을 정의합니다.                            |
  | `TeamUser.vue`       | 팀원으로 시작한 경우 해당 주제의 팀을 출력하기 위한 component를 정의합니다. |

- **src/components/user**

  > [유저 로그인 & 회원가입 파일 정의](./User(Login_Join)_Construction.md/#file-definition) / [유저 프로필 파일 정의](./User(profile)_Construction.md/#file-definition)
  
  | File 이름            | 기술                                                         |
  | -------------------- | ------------------------------------------------------------ |
  | `checkAbility.vue`   | 사용자의 능력치 입력 component를 정의합니다.                 |
  | `followItem.vue`     | 팔로우 유저 component를 정의합니다.                          |
  | `joinform.vue`       | 회원가입을 위한 입력 폼 component를 정의합니다.              |
  | `Logo.vue`           | 로그인 창의 Logo component를 정의합니다.                     |
  | `myPostItem.vue`     | 내가 쓴 게시글 component를 정의합니다.                       |
  | `passwordform.vue`   | 사용자의 비밀번호 변경 페이지를 정의합니다.                  |
  | `profileAbility.vue` | 유저 프로필 페이지의 능력치 출력을 위한 component를 정의합니다. |
  | `step1.vue`          | 회원가입을 위한 이메일 인증을 보내는 component를 정의합니다. |
  | `step2.vue`          | 회원가입 이메일 인증 component를 정의합니다.                 |

### :black_medium_square: src/util

| File 이름        | 기술                                                         |
| ---------------- | ------------------------------------------------------------ |
| `http-common.js` | 이후 다른 컴포넌트에서 baseURL과 headers를 별도의 작성 없이 하기 위해 axios 요청을 정의합니다. |

### :black_medium_square: src/views

- **src/views/chat**

  | File 이름  | 기술                             |
  | ---------- | -------------------------------- |
  | `Chat.vue` | 실시간 채팅 페이지를 정의합니다. |

- **src/views/search**

  > [검색 페이지 구조](./Search_Construction.md/#construction)

  | File 이름        | 기술                          |
  | ---------------- | ----------------------------- |
  | `SearchMain.vue` | 검색 페이지를 정의합니다.     |
  | `TagResult.vue`  | Tag 결과 페이지를 정의합니다. |

- **src/views/SNS**

  > [뉴스피드 구조](./SNSFeed_Construction.md/#construction)

  | File 이름        | 기술                                            |
  | ---------------- | ----------------------------------------------- |
  | `CreateFeed.vue` | 뉴스피드 게시물을 생성하는 페이지를 정의합니다. |
  | `Detail.vue`     | 뉴스피드 게시물 상세 페이지를 정의합니다.       |
  | `IndexFeed.vue`  | 뉴스피드를 위한 페이지를 정의합니다.            |

- **src/views/team**

  | File 이름         | 기술                                           |
  | ----------------- | ---------------------------------------------- |
  | `createTeam.vue`  | 프로젝트 팀 생성 페이지를 정의합니다.          |
  | `MatchMember.vue` | 매칭된 유저들을 보여주는 페이지를 정의합니다.. |
  | `subjectForm.vue` | 주제 선택 페이지를 정의합니다.                 |
  | `TeamInfo.vue`    | 팀 정보 확인 페이지를 정의합니다.              |
  | `teamMatch.vue`   | 팀원 매칭을 위한 페이지를 정의합니다.          |

- **src/views/user**

  > [유저 로그인 & 회원가입 구조](./User(Login_Join)_Construction.md/#construction) / [유저 프로필 구조](./User(profile)_Construction.md/#construction)
  
  | File 이름            | 기술                                           |
  | -------------------- | ---------------------------------------------- |
  | `FindPassword.vue`   | 비밀번호 찾기를 위한 페이지를 정의합니다.      |
  | `followinglist.vue`  | 팔로우 유저 목록을 위한 페이지를 정의합니다.   |
  | `Join.vue`           | 사이트 회원 가입을 위한 페이지를 정의합니다.   |
  | `Login.vue`          | 로그인을 위한 페이지를 정의합니다.             |
  | `modifyAbility.vue`  | 본인 능력치 수정을 위한 페이지를 정의합니다.   |
  | `modifyPassword.vue` | 비밀번호 변경을 위한 페이지를 정의합니다.      |
  | `myLikePost.vue`     | 내가 좋아요한 게시글 목록 페이지를 정의합니다. |
  | `myPost.vue`         | 내가 작성한 게시글 목록 페이지를 정의합니다.   |
| `profile.vue`        | 사용자 계정에 대한 페이지를 정의합니다.        |
  | `UserModify.vue`     | 유저 정보 수정을 위한 페이지를 정의합니다.     |

  - **src/views/user/join**
  
    | File 이름                 | 기술                                           |
    | ------------------------- | ---------------------------------------------- |
    | `getAbility.vue`          | 사용자 능력치 입력 페이지를 정의합니다.        |
    | `step1.vue` ~ `step5.vue` | 회원가입을 위한 페이지를 step 별로 정의합니다. |



## 세부 사항 Link 모음

- [뉴스피드 파일 정의](./SNSFeed_Construction.md/#file-definition)
- [유저 로그인 & 회원가입 구조](./User(Login_Join)_Construction.md/#construction)
- [유저 프로필 구조](./User(profile)_Construction.md/#construction)
- [알림 페이지 구조](./Alarm_Construction.md/#construction)
- [팀 페이지 구조]
- [검색 페이지 구조](./Search_Construction.md/#construction)





