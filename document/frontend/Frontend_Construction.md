# Frontend Construction

## Directory

```python
FRONTEND/
	public/
		favicon.ico
		index.html
        
	src/
		api/
			UserApi.js	# server 통신
		assets/
			css/
			images/
            
		components/		# component 파일 들어가는 디렉토리
			__tests__/
			common/		# 공통 디렉토리
				ButtonHalf.vue
				ButtonLarge.vue
				ButtonSmall.vue
				Checkbox.vue
				Curation.vue
				Header.vue
				Input.vue
				KeywordAdd.vue
				KeywordDel.vue
				Navigation.vue
				Select.vue
				subnav.vue
				Switch.vue
				Tab.vue
				Textarea.vue
				Toast.vue
			css/
            	# 생략
                
			main/				# main(공모전, 스터디)에 들어가는 components
				competitionItem.vue
				studyItem.vue
			SNS/				# SNS에 들어가는 components
				SNSCommentItem.vue
                SNSItem.vue
			user/				# 사용자 관련 components
				snsLogin/
					Google.vue
					Kakao.vue
					Select.vue
                checkAbility.vue
                joinform.vue
				Logo.vue
                passwordform.vue
                step1.vue
                step2.vue
                
		util/
			http-common.js		# axios 요청
            
		views/					# 사용자에게 보여주기 위한 views
			SNS/
            	CommentFeed.vue
                CreateFeed.vue
                DetailFeed.vue
				IndexFeed.vue
			user/
				FindPassword.vue
                getAbility.vue
				Join.vue
				Login.vue
				profile.vue
			Components.vue
			Main.vue			# Main 화면
            Notice.vue
		vuex/
        	# 생략
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

| File 이름              | 기술                                                    |
| ---------------------- | ------------------------------------------------------- |
| `App.vue`              | vue.js의 기본적인 App.vue를 정의합니다.                 |
| views/`Main.vue`       | 로그인 후 사용자가 보게 될 기본 Home 화면을 정의합니다. |
| views/`Notice.vue`     | 사용자에게 공지사항 화면을 정의합니다.                  |
| views/`Components.vue` | 사용하기에 유용한 Components를 모아서 정의합니다.       |

### :black_medium_square: src/components

| File 이름                  | 기술                                                         |
| -------------------------- | :----------------------------------------------------------- |
| common/`파일명 생략`       | 해당 디렉토리의 vue 파일들은 이후 화면 구성에서 사용하기 유용한 component를 정의합니다. |
| main/`competitionItem.vue` | main 화면의 공모전 부분에 들어갈 component를 정의합니다.     |
| main/`studyItem.vue`       | main 화면의 스터디 부분에 들어갈 component를 정의합니다.     |
| SNS/`SNSItem.vue`          | 뉴스피드에 들어갈 게시물 component를 정의합니다.             |
| SNS/`SNSCommentItem.vue`   | 뉴스피드 게시물 상세페이지의  댓글 component를 정의합니다.   |
| user/snsLogin/파일명 생략  | 다른 SNS를 연동한 Login시 필요한 component를 정의합니다.     |
| user/`checkAbility.vue`    | 사용자의 능력치 확인 페이지의 component를 정의합니다.        |
| user/`joinform.vue`        | 회원가입을 위한 입력 폼 component를 정의합니다.              |
| user/`step1.vue`           | 회원가입을 위한 이메일 인증을 보내는 component를 정의합니다. |
| user/`step2.vue`           | 회원가입 이메일 인증 component를 정의합니다.                 |
| user/`passwordform.vue`    | 사용자의 비밀번호 변경 페이지를 정의합니다.                  |

### :black_medium_square: src/util

| File 이름      | 기술                                                         |
| -------------- | ------------------------------------------------------------ |
| http-common.js | 이후 다른 컴포넌트에서 baseURL과 headers 작성 없이 편하게 하기 위해 axios 요청을 정의합니다. |

### :black_medium_square: src/views/SNS

| File 이름        | 기술                                            |
| ---------------- | ----------------------------------------------- |
| `CreateFeed.vue` | 뉴스피드 게시물을 생성하는 페이지를 정의합니다. |
| `DetailFeed.vue` | 뉴스피드 게시물 상세 페이지를 정의합니다.       |
| `IndexFeed.vue`  | 뉴스피드를 위한 페이지를 정의합니다.            |

### :black_medium_square: src/views/user

| File 이름          | 기술                                         |
| ------------------ | -------------------------------------------- |
| `FindPassword.vue` | 비밀번호 찾기를 위한 페이지를 정의합니다.    |
| `Join.vue`         | 사이트 회원 가입을 위한 페이지를 정의합니다. |
| `Login.vue`        | 로그인을 위한 페이지를 정의합니다.           |
| `Profile.vue`      | 사용자 계정에 대한 페이지를 정의합니다.      |
| `getAbility.vue`   | 사용자의 능력치 확인 페이지를 정의합니다.    |

