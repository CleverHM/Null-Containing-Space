<template>
    <div id="createTeam">
        <Navbar :subjectCheck="subjectCheck"></Navbar>
        <subNav/>
        <div class="team-form">
            <!-- 프로젝트 이름 -->
            <div class="inputForm">
                <input v-model="team.title" id="title" placeholder="프로젝트 팀 이름(3글자 이상 입력해주세요)" type="text" />
            </div>

            <!-- 프로젝트 인원 -->
            <div class="cnt-area">
                인원
                <b-icon-chevron-down scale="1.2" font-scale="1.5" class="cnt-down" @click="cntDown"></b-icon-chevron-down>
                <div>{{ team.cnt }}</div>
                <b-icon-chevron-up scale="1.2" font-scale="1.5" class="cnt-up" @click="cntUp"></b-icon-chevron-up>
            </div>

            <!-- 프로젝트 기술 -->
            <div class="team-use">
                프로젝트 사용 기술
                <span class="team-use-sub">
                    (필요한 기술을 클릭해주세요)
                </span>
            </div>
            <!-- 전체 선택 취소하기 위한 버튼 -->
            <!-- <div class="button-all">
                <div v-for="i in 1" :key="i + '^'" :id="i + '^'">
                    <div @click="clickAll"
                    :class="{boxCheckOn: basic.clickTFAll[i-1], boxCheckOff: !basic.clickTFAll[i-1]}">
                    </div>
                    <div @click="clickAll">{{ basic.techalllist[i-1] }}</div>
                    <div style="display: none;">{{ team.click }}</div>
                </div>
            </div> -->

            <div v-for="n in 4" :key="n-1" :id="n + '/'" class="mx-1 mb-2">
                <div class="displaytags">
                    {{ team.techName[n-1] }}
                </div>
                <div class="button-area">
                    <div v-for="m in team.techLen[n-1]"
                        :key="team.techs[n-1][m-1]"
                        :id="m + ','"
                        :class="{checkOn: team.click[n-1][m-1], checkOff: !team.click[n-1][m-1]}"
                        @click="clickTech">
                        {{ team.techs[n-1][m-1] }}
                    </div>
                </div>
                <div style="display:none">{{ team.clicktech[n-1] }}</div>
            </div>

            
            <!-- 프로젝트 요약 -->
            <div class="textareaForm">
                <div class="textlength d-flex justify-content-between">
                    <div>
                        프로젝트 요약
                    </div>
                    <div style="font-weight: lighter; font-size:13px;">
                        {{ team.intro.length }}/
                    </div>
                </div>
                <textarea class="d-flex flex-fill" v-model="team.intro" placeholder="프로젝트와 관련된 내용을 적어주세요."/>
            </div>

            <div class="d-flex justify-content-center">
                <button class="submit-button" @click="teamCreate">팀 개설하기</button>
            </div>

        </div>

    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
    name: "createTeam",

    props: ['subjectCheck'],

    components: {
        Navbar,
        subNav,
    },

    data() {
        return {
            team: {
                title: '',
                techName: [
                    'Backend', 'Frontend', 'DataBase', 'Fremework',
                ],
                clicktech: [
                    [], [], [], [],
                ],
                cnt: 5,
                intro: '',
                techs: [
                    ['cpp', 'java', 'python', 'php',],
                    ['html', 'css', 'javascript', ],
                    ['sql', 'nosql',  ],
                    ['spring', 'django', 'bootstrap', 'vue', 'react', ],
                    // 마지막에 algo는 보내줄 땐 반드시 true로 보낼 것
                ],
                click: [
                    [], [], [], [], 
                ],
                techLen: [],
            },
            basic: {
                // 전체 클릭 시
                clicktechAll: [
                    [], [], [], [],
                ],
                clickAll: [
                    [], [], [], [], 
                ],

                // 전체 취소 시
                clicktechNo: [
                    [], [], [], [],
                ],
                clickNo: [
                    [], [], [], [],
                ],

                // 클릭 상태
                techalllist: [
                    '전체', 'Back', 'Front', 'DB', 'Frame',
                ],
                clickTFAll: [
                    false, false, false, false, false,
                ],
            },
        }
    },

    created() {
        var checkLen;
        for (let step = 0; step < 4; step++) {
            checkLen = this.team.techs[step].length;
            this.team.techLen.push(checkLen);
            for (let step2 = 0; step2 < checkLen; step2++) {
                this.team.click[step].push(false)

                // 전체 선택을 위한 All 배열 만들기
                this.basic.clickAll[step].push(true)
                this.basic.clicktechAll[step].push(step2)
            }
        }

        // 빈 배열 값 복사 (이중배열 deep copy)
        this.basic.clickNo = this.team.click.slice();
        console.log(this.basic.clickNo)
    },

    methods: {

        // 클릭한 기술 가져오기
        clickTech(event) {
            // 이중 배열의 m, n 순으로 가져오기 (배열에서는 [n][m]임)
            // console.log(event.target.innerText)
            var sidx = Number(event.target.id.split(',')[0]) - 1
            var pidx = Number(event.target.parentElement.parentElement.id.split('/')[0]) - 1
            this.team.click[pidx][sidx] = !this.team.click[pidx][sidx]
            // true면 넣기 / false면 빼기
            if (this.team.click[pidx][sidx]) {
                this.team.clicktech[pidx].push(sidx)
            } else {
                this.team.clicktech[pidx].splice(this.team.clicktech[pidx].indexOf(sidx),1)
                this.basic.clickTFAll[0] = false
            }
        },

        // 인원 수 위
        cntUp () {
            this.team.cnt = this.team.cnt + 1;
        },

        // 인원 수 아래
        cntDown () {
            if (this.team.cnt == 1) {
                console.log('안됨')
            } else {
                this.team.cnt = this.team.cnt - 1;
            }
        },

        // 안 됨...
        // clickAll(event) {
        //     var cidx = Number(event.target.parentElement.id.split('^')[0] - 1)

        //     // true - false를 변경
        //     this.basic.clickTFAll[cidx] = !this.basic.clickTFAll[cidx]
        //     console.log(cidx)
        //     console.log(this.basic.clickTFAll[cidx])

        //     // 전체는 해당 방식으로 바꿀 수 있음
        //     if (cidx == 0) {
        //         if (this.basic.clickTFAll[cidx]) {
        //             for (const i in this.team.click) {
        //                 this.team.clicktech[i] = []
        //                 for (const j in this.team.click[i]) {
        //                     this.team.click[i][j] = true
        //                     this.team.clicktech[i].push(Number(j))
        //                 }
        //             }
        //             this.basic.clickTFAll = [true, true, true, true, true];
        //         } else {
        //             for (const i in this.team.click) {
        //                 this.team.clicktech[i] = []
        //                 for (const j in this.team.click[i]) {
        //                     this.team.click[i][j] = false
        //                 }
        //             }
        //             this.basic.clickTFAll = [false, false, false, false, false];
        //         }
        //     } else {
        //         // 전체가 아니면 하나하나 for문을 돌아야하는 듯
        //         if (this.basic.clickTFAll[cidx]) {
        //             this.team.click[cidx-1] = this.basic.clickAll[cidx-1]
        //             this.team.clicktech[cidx-1] = this.basic.clicktechAll[cidx-1]
                    
        //             console.log('부분만 클릭 확인', this.team.click[cidx-1])
        //             console.log('부분만 받은 것 확인', this.team.clicktech[cidx-1])
        //         } else {
        //             this.team.click[cidx-1] = this.basic.clickNo[cidx-1]
        //             this.team.clicktech[cidx-1] = []
        //             this.basic.clickTFAll[0] = false

        //             console.log('부분만 클릭 확인', this.team.click[cidx-1])
        //             console.log('부분만 받은 것 확인', this.team.clicktech[cidx-1])

        //         } 
        //     }
            
        //     console.log('전체에서 클릭된 지 확인', this.team.click)
        //     console.log('전체에서 받아온 지 확인', this.team.clicktech)
        // },

        // 팀 개설 제출
        teamCreate() {
            var step;
            var techList = new Array();

            // 기술들을 하나의 리스트(true/false)로 변환한다.
            // 1. 기술 4개(back - front - db - framework)의 리스트를 하나의 리스트로 합치기
            for (step = 0; step < 4; step++) {
                techList = techList.concat(this.team.click[step])
            }

            // 2. 원래 ability의 algo가 있으므로 algo는 true로 보내준다.
            techList = techList.concat(true)

            console.log(techList)
            // 보내줘야할 데이터들을 formData 안에 넣는다.
            let formData = new FormData();
            formData.append("nickname", storage.getItem("NickName"));
            formData.append("title", this.team.title);
            formData.append("teamintro", this.team.intro);
            formData.append("prePro", this.subjectCheck);
            formData.append("preTech", techList);
            formData.append("cnt", 5);  // 인원수 (디폴트 5)

            http
            .post("/team/create", formData)
            .then((res) => {
                console.log('완료')
            })
            .catch((err) => {
                console.log(err)
            })

        },
        
    }

}
</script>

<style scoped>
.team-form {
    margin: 55px 0px 50px 0px;
}

.input-with-label{
    width: 100%;
}
.inputForm{
    margin: 0px 5px 10px 5px;
}


.team-use {
    margin: 20px 10px 10px 10px;
    color: #464545;
    font-size: 15px;
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
}

.team-use-sub {
    color:#757474;
    font-size: 14px;
    margin-left: 15px;
    font-weight: normal;
}

.cnt-area {
    display: inline-block;
    margin: 10px;
    color: #464545;
    font-size: 15px;
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
}
.cnt-area > div {
    display: inline-block;
    font-weight: lighter;
}

.cnt-down {
    margin: 0px 10px 0px 10px;
    background-color: #ACCCC4;
    border-radius: 50%;
    color: white;
    padding: 5px;
}

.cnt-up {
    margin: 0px 10px 0px 10px;
    background-color: #ACCCC4;
    border-radius: 50%;
    color:white;
    padding: 5px;
}

.button-all {
    display: inline-block;
    margin: 10px 10px 0px 10px;
    color: #464545;
    font-size: 13px;
    font-family: Arial, Helvetica, sans-serif;
}

.button-all > div {
    display: inline-block;
    margin: 5px 10px 5px 0px;
}

.button-all > div > div {
    display: inline-block;
    margin-left: 5px;
    text-align: center;
}

.boxCheckOff {
    display: inline-block;
    width: 15px;
    height: 15px;
    border: 1px solid #464545;
}

.boxCheckOn {
    display: inline-block;
    width: 15px;
    height: 15px;
    border: 1px solid #464545;
    background-color: #464545;
}

input {
    padding: 0 10px 0 10px;
    width: 100%;
    height: 40px;
    border: none;
    border-bottom: 1px solid black;
}

input::placeholder {
    font-size: 13px;
    font-weight: normal;
    color: #464545;
}


.btn-tags{
    border-radius: 10px;
    margin: 3px;
    padding: 1px 5px 1px 5px;
    background-color: #C4BCB8;
    font-weight:bold;
    color: #f7f7f7;
}

.button-area {
    width: 98%;
    padding: 0px 10px 5px 10px;
    margin-bottom: 5px;
}

.displaytags{
    width: 30%;
    padding: 10px;
    font-size: 14px;
    font-weight: bold;
    color: #464545;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.checkOff {
    display: inline-block;
    margin: 5px;
    padding: 0px 5px 5px 5px;
    border-radius: 10px;
    font-size: 13px;
    color: #464545;
    background-color: #E2DFD8;
}

.checkOn {
    display: inline-block;
    margin: 5px;
    padding: 0px 5px 5px 5px;
    border-radius: 10px;
    font-size: 13px;
    font-weight: bold;
    color: white;
    background-color: #ACCCC4;
}

.textareaForm {
    margin: 20px 15px 0px 15px;
    border: none;
    background-color: none;
}

.textlength{
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    border-bottom: 1px solid #464545;
    font-size: 15px;
    font-weight: bold;
}

.textareaForm textarea {
    width: 100%;
    height: 100px;
    padding: 5px;
    overflow: auto;
    resize: vertical;
    text-rendering: auto;
    color: -internal-light-dark(#464545;, white);
    background-color: #f7f7f7;
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    font-size: 13px;
    font-weight: normal;
}

.submit-button {
    margin: 10px 0px 10px 0px;
    padding: 10px;
    color: white;
    background-color: #ACCCC4;
    border-radius: 10px;
}
</style>