package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.MyPageData;
import com.ssafy.pjt1.model.TeamData;
import com.ssafy.pjt1.model.TeamPersonData;
import com.ssafy.pjt1.service.TeamService;
import com.ssafy.pjt1.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })

@RestController
public class TeamController {

	@Autowired
	private UserService userservice;

	@Autowired
	private TeamService teamservice;

	@PostMapping("/team/binTeam")
	@ApiOperation(value = "1 번팀 생성", notes = "1번 팀 생성 기능을 구현")
	public void binTeam() {
		Team team = new Team("default 팀 입니다.", "default", Integer.MAX_VALUE);
		teamservice.join(team);
	}

	@PostMapping("/team/create")
	@ApiOperation(value = "팀 생성", notes = "팀 생성 기능을 구현")
	public void teamCreate(@Valid @RequestParam String title, String teamintro, int cnt, int prePro, Boolean[] preTech,
			String nickname) {

		Optional<Team> t = teamservice.findone(1);

		if (!t.isPresent()) {
			Team team = new Team("default 팀 입니다.", "default", Integer.MAX_VALUE);
			teamservice.join(team);
		}

		Optional<User> optionaluser = userservice.findtwo(nickname);
		User user = optionaluser.get();

		Team team = new Team(title, teamintro, cnt, prePro, preTech[0], preTech[1], preTech[2], preTech[3], preTech[4],
				preTech[5], preTech[6], preTech[7], preTech[8], preTech[9], preTech[10], preTech[11], preTech[12],
				preTech[13], preTech[14]);

//		Team team = new Team(title, teamintro, cnt, prePro,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
		teamservice.join(team);

		user.setLeader(true);
		user.setTeam(team);
		user.setMatchok(false);
		userservice.signUp(user);
	}

	@PostMapping("/team/join")
	@ApiOperation(value = "팀 가입", notes = "팀 가입 기능을 구현")
	public void teamjoin(@Valid @RequestParam String nickname, int teamid) {
		System.out.println(nickname);
		Optional<User> optionalUser = userservice.findtwo(nickname);
		Optional<Team> optionalTeam = teamservice.findone(teamid);

		User user = optionalUser.get();
		Team team = optionalTeam.get();

		// 팀: 유저 이어주기
		user.setTeam(team);
		user.setMatchok(false);
		userservice.signUp(user);

	}

	@PostMapping("/team/leave")
	@ApiOperation(value = "팀 탈퇴", notes = "팀 탈퇴 기능을 구현")
	public void teamleave(@Valid @RequestParam String nickname) {
		Optional<User> optionalUser = userservice.findtwo(nickname);
		Optional<Team> optionalTeam = teamservice.findone(1);

		Team team = optionalTeam.get();

		User user = optionalUser.get();

		user.setTeam(team);
		// 팀: 유저 끊어주기

		userservice.signUp(user);
	}

	// nick name 으로 프로젝트 페이지판별 하기
	@PostMapping("/team/exist")
	@ApiOperation(value = "페이지판별", notes = "페이지판별 기능을 구현")
	public Object exist(@Valid @RequestParam String nickname) throws MalformedURLException, IOException {

		Optional<User> optionalUser = userservice.findtwo(nickname);
		User user = optionalUser.get();
		System.out.println(user.getTeam().getTeamid());

		TeamData teamdata = null;
		List<TeamData> teamdatas = new LinkedList<TeamData>();

		if (user.getTeam().getTeamid() == 1) {
			if (user.isMatchok() == true) {
				System.out.println("현재팀없음");
				System.out.println("팀원 등록은 했음");
				final BasicResponse result = new BasicResponse();
				result.status = false;
				result.matchok = true;
				result.data = "팀없음";

				// 선호하는 프로젝트 의 리스트 넘겨줘야함
				// 팀장 사진, 닉네임 , 팀아이디
				int prePro = user.getPreferProject();

				List<Team> teamList = teamservice.fineall();

				for (int i = 0; i < teamList.size(); i++) {
					Team t = teamList.get(i);
					if (t.getPreferProject() == prePro) {
						// 팀리더 정보 추가

						end: for (User u : t.getUsers()) {
							System.out.println(u.getNickname() + " " + u.getLeader());

							if (u.getLeader() == true) {
								TeamPersonData leaderNickname = null;
								// 이미지
								System.out.println("나리더");
								byte[] reportBytes = null;
								File result3 = new File(u.getProfile().getFileurl() + u.getProfile().getFilename());

								if (result3.exists()) {
									System.out.println("있음");
									InputStream inputStream = new FileInputStream(
											u.getProfile().getFileurl() + u.getProfile().getFilename());
									String type = result3.toURL().openConnection()
											.guessContentTypeFromName(user.getProfile().getFilename());

									byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

									leaderNickname = new TeamPersonData(u.getNickname(), out);
								} else {
									leaderNickname = new TeamPersonData(u.getNickname(), reportBytes);
									System.out.println("프로필 파일 없음");
								}

								// teamid 저장
								System.out.println(t.getTeamid() + " " + leaderNickname.getNickname());
								TeamData TeamData1 = new TeamData(t.getTeamid(), leaderNickname);
								teamdatas.add(TeamData1);

								break end;
							}
						} // forend

					} // ifend
				}
				System.out.println("다왔다!");
				result.teamdates = teamdatas;
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				System.out.println("현재팀없음");
				System.out.println("팀원 등록 안함");
				final BasicResponse result = new BasicResponse();
				result.status = false;
				result.matchok = false;
				result.data = "팀없음";
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} else {

			// 팀 원 넣기
			List<TeamPersonData> mems = new LinkedList<TeamPersonData>();
			List<Boolean> preferTech = new LinkedList<Boolean>();
			TeamPersonData leaderNickname = null;

			preferTech.add(user.getTeam().isBack_cpp());
			preferTech.add(user.getTeam().isBack_java());
			preferTech.add(user.getTeam().isBack_python());
			preferTech.add(user.getTeam().isBack_php());
			preferTech.add(user.getTeam().isFront_html());
			preferTech.add(user.getTeam().isFront_css());
			preferTech.add(user.getTeam().isFront_javascript());
			preferTech.add(user.getTeam().isDb_sql());
			preferTech.add(user.getTeam().isDb_nosql());
			preferTech.add(user.getTeam().isFrame_spring());
			preferTech.add(user.getTeam().isFrame_django());
			preferTech.add(user.getTeam().isFrame_bootstrap());
			preferTech.add(user.getTeam().isFrame_vue());
			preferTech.add(user.getTeam().isFrame_react());
			preferTech.add(user.getTeam().getAlgo());

			for (User u : user.getTeam().getUsers()) {
				System.out.println(u.getNickname() + " " + u.getLeader());
				if (u.getLeader() == true) {
					// 이미지
					System.out.println("나리더");
					byte[] reportBytes = null;
					File result = new File(u.getProfile().getFileurl() + u.getProfile().getFilename());

					if (result.exists()) {
						System.out.println("있음");
						InputStream inputStream = new FileInputStream(
								u.getProfile().getFileurl() + u.getProfile().getFilename());
						String type = result.toURL().openConnection()
								.guessContentTypeFromName(user.getProfile().getFilename());

						byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

						leaderNickname = new TeamPersonData(u.getNickname(), out);
					} else {
						leaderNickname = new TeamPersonData(u.getNickname(), reportBytes);
						System.out.println("프로필 파일 없음");
					}
				} else {
					// 이미지
					byte[] reportBytes = null;
					File result = new File(u.getProfile().getFileurl() + u.getProfile().getFilename());

					if (result.exists()) {
						System.out.println("있음");
						InputStream inputStream = new FileInputStream(
								u.getProfile().getFileurl() + u.getProfile().getFilename());
						String type = result.toURL().openConnection()
								.guessContentTypeFromName(user.getProfile().getFilename());

						byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

						mems.add(new TeamPersonData(u.getNickname(), out));
					} else {

						mems.add(new TeamPersonData(u.getNickname(), reportBytes));
						System.out.println("프로필 파일 없음");
					}
				}
			}

			System.out.println(leaderNickname.getNickname());
			teamdata = new TeamData(user.getTeam().getCreateDate(), user.getTeam().getMemberCnt(), mems,
					user.getTeam().getTeamIntro(), user.getTeam().getTitle(), user.getTeam().getPreferProject(),
					preferTech, leaderNickname);

			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.teamdate = teamdata;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@PostMapping("/team/memberRegister")
	@ApiOperation(value = "팀원으로 등록", notes = "팀원으로 등록 기능을 구현")
	public void memberRegister(@Valid @RequestParam String nickname, int preferProject) {
		Optional<User> optionalUser = userservice.findtwo(nickname);

		User user = optionalUser.get();

		user.setMatchok(true);
		user.setPreferProject(preferProject);

		userservice.signUp(user);
	}

	// 팀 아이디로 정보 보내기
	@PostMapping("/team/teamInfo")
	@ApiOperation(value = "팀페이지(팀아이디로)", notes = "팀페이지(팀아이디로) 기능을 구현")
	public Object teamInfo(@Valid @RequestParam int teamid) throws MalformedURLException, IOException {

		// 팀 원 넣기
		Optional<Team> optionalTeam = teamservice.findone(teamid);
		Team team = optionalTeam.get();
		
		List<TeamPersonData> mems = new LinkedList<TeamPersonData>();
		List<Boolean> preferTech = new LinkedList<Boolean>();
		TeamPersonData leaderNickname = null;

		preferTech.add(team.isBack_cpp());
		preferTech.add(team.isBack_java());
		preferTech.add(team.isBack_python());
		preferTech.add(team.isBack_php());
		preferTech.add(team.isFront_html());
		preferTech.add(team.isFront_css());
		preferTech.add(team.isFront_javascript());
		preferTech.add(team.isDb_sql());
		preferTech.add(team.isDb_nosql());
		preferTech.add(team.isFrame_spring());
		preferTech.add(team.isFrame_django());
		preferTech.add(team.isFrame_bootstrap());
		preferTech.add(team.isFrame_vue());
		preferTech.add(team.isFrame_react());
		preferTech.add(team.getAlgo());

		for (User u : team.getUsers()) {
			System.out.println(u.getNickname() + " " + u.getLeader());
			if (u.getLeader() == true) {
				// 이미지
				System.out.println("나리더");
				byte[] reportBytes = null;
				File result = new File(u.getProfile().getFileurl() + u.getProfile().getFilename());

				if (result.exists()) {
					System.out.println("있음");
					InputStream inputStream = new FileInputStream(
							u.getProfile().getFileurl() + u.getProfile().getFilename());

					byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

					leaderNickname = new TeamPersonData(u.getNickname(), out);
				} else {
					leaderNickname = new TeamPersonData(u.getNickname(), reportBytes);
					System.out.println("프로필 파일 없음");
				}
			} else {
				// 이미지
				byte[] reportBytes = null;
				File result = new File(u.getProfile().getFileurl() + u.getProfile().getFilename());

				if (result.exists()) {
					System.out.println("있음");
					InputStream inputStream = new FileInputStream(
							u.getProfile().getFileurl() + u.getProfile().getFilename());

					byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

					mems.add(new TeamPersonData(u.getNickname(), out));
				} else {

					mems.add(new TeamPersonData(u.getNickname(), reportBytes));
					System.out.println("프로필 파일 없음");
				}
			}
		}

		System.out.println(leaderNickname.getNickname());
		TeamData teamdata = new TeamData(team.getCreateDate(), team.getMemberCnt(), mems,
				team.getTeamIntro(), team.getTitle(), team.getPreferProject(), preferTech,
				leaderNickname);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.teamdate = teamdata;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
