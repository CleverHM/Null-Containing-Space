
		feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(), d.toString(), 
				list, post.getUser().getNickname(), post.getUser().getEmail(), post.getImg().getFilename(), post.getImg().getFileurl());
		
		return feedDetailData;
	}
	
	// 해당이메일 게시물 보내주기
	@PostMapping("/post/getHashtagPost")
	@ApiOperation(value = "게시물 해쉬태그 클릭시", notes = "게시물 해쉬태그 클릭시 기능을 구현.")
	public List<FeedData> getHashtagPost(@Valid @RequestParam String email, String[] hashtag) {
		
		System.out.println(email);
		
		List<Post> postList = new LinkedList<>();
		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();
		Set<User> followList = user.getFollowing();
		List<FeedData> res = new LinkedList<>();
		List<String> tag = new LinkedList<String>();
		
		// 자기가 올린 게시문
		Set<Post> myPost = user.getPosts();
		postList.addAll(myPost);
		// 팔로우 들의 게시문
		for (User u : followList) {
			Optional<User> optionalUser1 = userservice.findone(u.getEmail());
			User user1 = optionalUser1.get();
			Set<Post> followPost = user1.getPosts();
			postList.addAll(followPost);
		}
		
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}
		
		// 태그들 포함 여부
		List<Post> hasftagPostList = new LinkedList<>();
		// Iterator<Post> iterator = myPost.iterator();
		for (Post p : postList) {
			System.out.println("=================");
			for (int i = 0; i < hashtag.length; i++) {
				for (Tag t : p.getTags()) {
					if (hashtag[i].equals(t.getName())) {
						hasftagPostList.add(p);
					}
				}
			}
		}
		
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < hasftagPostList.size(); i++) {
			System.out.println(hasftagPostList.get(i).getPid());
			
			for (Tag t : hasftagPostList.get(i).getTags()) {
				tag.add(t.getName());
			}
			
			Date d = new Date();
			res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),d.toString(),hasftagPostList.get(i).getTitle(), hasftagPostList.get(i).getUser().getNickname(),
					hasftagPostList.get(i).getImg().getFilename(), hasftagPostList.get(i).getImg().getFileurl(), tag));
		}
		
		return res;
	}
	
	
}
=======
	}
	
//	// 해당이메일 게시물 보내주기
//		@PostMapping("/post/getPost")
//		@ApiOperation(value = "게시물 Vue로보내기", notes = "게시물 Vue로보내기 기능을 구현.")
//		public List<FeedData> getPost(@Valid @RequestBody String email) {
//
//			System.out.println(email);
//			List<Post> postList = new LinkedList<>();
//			List<FeedData> res = new LinkedList<>();
//			Optional<User> optionalUser = userservice.findone(email);
//			User user = optionalUser.get();
//			Set<User> followList = user.getFollowing();
//			// 자기가 올린 게시문
//			Set<Post> myPost = user.getPosts();
//			postList.addAll(myPost);
//			// 게시물 확인
//			System.out.println("==============내게시물==================");
//			for (int i = 0; i < postList.size(); i++) {
//				System.out.println(postList.get(i).getTitle());
//			}
//			// 팔로우 들의 게시문
//			for (User u : followList) {
//				Optional<User> optionalUser1 = userservice.findone(u.getEmail());
//				User user1 = optionalUser1.get();
//				Set<Post> followPost = user1.getPosts();
//				postList.addAll(followPost);
//			}
//			// 게시물 확인
//			System.out.println("==============내게시물+팔로우==================");
//			for (int i = 0; i < postList.size(); i++) {
//				System.out.println(postList.get(i).getTitle());
//				List<String> tag = new LinkedList<String>();
//
//				for (Tag t : postList.get(i).getTags()) {
//					tag.add(t.getName());
//				}
//				Date d = new Date();
//				res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),d.toString(),postList.get(i).getTitle(), postList.get(i).getUser().getNickname(),
//						postList.get(i).getImg().getFilename(), postList.get(i).getImg().getFileurl(), tag));
//			}
//			return res;
//		}
//
//		@PostMapping("/post/postDetail")
//		@ApiOperation(value = "게시물 디테일 페이지", notes = "게시물 디테일 페이지 기능을 구현.")
//		public FeedDetailData postDetail(@Valid @RequestBody String pid){
//			FeedDetailData feedDetailData = null;
//			
//			int currentPid = Integer.parseInt(pid);
//			Optional<Post> optionalPost = postdao.findPostByPid(currentPid);
//			List<String> list = new LinkedList<String>();
//			
//			Post post = optionalPost.get();
//			
//			System.out.println(post.getPid());
//			System.out.println(post.getTitle());
//			System.out.println(post.getContent());
//			System.out.println(post.getImg().getFilename());
//			
//			for(Tag t : post.getTags()) {
//				System.out.println(t.getName());
//				list.add(t.getName());
//			}
//			
//			System.out.println(post.getTags());
//			System.out.println(post.getUser().getEmail());
//			
//			Date d = new Date();
//			feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(), d.toString(), 
//					list, post.getUser().getNickname(), post.getUser().getEmail(), post.getImg().getFilename(), post.getImg().getFileurl());
//			
//			return feedDetailData;
//		}
//		
//		// 해당이메일 게시물 보내주기
//		@PostMapping("/post/getHashtagPost")
//		@ApiOperation(value = "게시물 해쉬태그 클릭시", notes = "게시물 해쉬태그 클릭시 기능을 구현.")
//		public List<FeedData> getHashtagPost(@Valid @RequestParam String email, String[] hashtag) {
//			
//			System.out.println(email);
//			
//			List<Post> postList = new LinkedList<>();
//			Optional<User> optionalUser = userservice.findone(email);
//			User user = optionalUser.get();
//			Set<User> followList = user.getFollowing();
//			List<FeedData> res = new LinkedList<>();
//			List<String> tag = new LinkedList<String>();
//			
//			// 자기가 올린 게시문
//			Set<Post> myPost = user.getPosts();
//			postList.addAll(myPost);
//			// 팔로우 들의 게시문
//			for (User u : followList) {
//				Optional<User> optionalUser1 = userservice.findone(u.getEmail());
//				User user1 = optionalUser1.get();
//				Set<Post> followPost = user1.getPosts();
//				postList.addAll(followPost);
//			}
//			
//			// 게시물 확인
//			System.out.println("==============내게시물+팔로우==================");
//			for (int i = 0; i < postList.size(); i++) {
//				System.out.println(postList.get(i).getTitle());
//			}
//			
//			// 태그들 포함 여부
//			List<Post> hasftagPostList = new LinkedList<>();
//			// Iterator<Post> iterator = myPost.iterator();
//			for (Post p : postList) {
//				System.out.println("=================");
//				for (int i = 0; i < hashtag.length; i++) {
//					for (Tag t : p.getTags()) {
//						if (hashtag[i].equals(t.getName())) {
//							hasftagPostList.add(p);
//						}
//					}
//				}
//			}
//			
//			// 게시물 확인
//			System.out.println("==============내게시물+팔로우==================");
//			for (int i = 0; i < hasftagPostList.size(); i++) {
//				System.out.println(hasftagPostList.get(i).getPid());
//				
//				for (Tag t : hasftagPostList.get(i).getTags()) {
//					tag.add(t.getName());
//				}
//				
//				Date d = new Date();
//				res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),d.toString(),hasftagPostList.get(i).getTitle(), hasftagPostList.get(i).getUser().getNickname(),
//						hasftagPostList.get(i).getImg().getFilename(), hasftagPostList.get(i).getImg().getFileurl(), tag));
//			}
//			
//			return res;
//		}
	

	
}
>>>>>>> 53ab0ccdcf58f4d414b165fb0659a535177d8232
