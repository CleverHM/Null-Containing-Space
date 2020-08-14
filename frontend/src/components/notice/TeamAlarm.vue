<template>
    <div>
        <input type="radio" class="button-open-check" name="temp">
        <div class="scrap" @click="buttonOn">
            <b-icon-people-fill class="scrap-icon"></b-icon-people-fill>
            <div class="notice">
                {{ teamData.content }} <br/>
            <small class="noticeday ml-1">{{ diffTime }}</small>
            </div>
            <div class="button-list buttonArea d-flex justify-content-around">
                <button v-if="!ifLeader" style="background-color: #ACCCC4" @click="goTeamInfo">팀 정보 보기</button>
                <button v-if="ifLeader" style="background-color: #ACCCC4" @click="goUserInfo">유저 정보 보기</button>
                <button style="background-color: #E2DFD8" @click="goReject">거절</button>
                <button style="background-color: #E2DFD8" @click="goAccept">수락</button>
            </div>
        </div>
        <hr>
    </div>
    
</template>

<script>
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;
var now = new Date(); // 현재 시간 받아오기

export default {
    name: 'TeamAlarm',
    props: [
        'teamData'
    ],

    data() {
        return {
            diffTime: null,
            buttonOpen: false,
            ifLeader: false,
        }
    },

    created() {
        var postDate = new Date(this.teamData.createDate)
        this.diffTime = this.dateCheck(postDate);
        
        // teamid가 1이면 본인이 리더임 -> ifLeader = true
        if ( this.teamData.teamid == 1 ) {
            this.ifLeader = true
        }
    },

    methods: {

        // 팀 정보 보기
        goTeamInfo() {
            this.$router.push({ name: 'TeamInfo', params: { teamId: this.teamData.teamid }})
        },

        // 유저 정보 보기
        goUserInfo() {
            this.$router.push({ name: 'profile', params: { nickname: this.teamData.who }})
        },


        // 팀 수락
        goAccept() {
            let formData = new FormData;
            // 팀에 가입 요청일 때 / 팀장이 가입 권유할 때
            if ( this.teamData.teamid == 1 ) {
                formData.append('nickname', this.teamData.who)
                formData.append('leadernickname', storage.getItem("NickName"))

            } else {
                formData.append('nickname', storage.getItem("NickName"))
                formData.append('leadernickname', this.teamData.who)
            }
            
            http
            .post('/team/join', formData)
            .then((res) => {
                alert(`${res.data.data}`)

                if (res.data.status == true) {
                    this.alarmDelete();
                }
            })
            .catch((err) => {
                console.log(err)
            })

        },

        // 알람 삭제
        alarmDelete() {
            
            http
            .delete(`/alarm/${this.teamData.aid}`)
            .then((res) => {
                this.$router.go({ name: 'Notice', params: { tapId: 1 } })
            })
            .catch((err) => {
                console.log(err)
            })
        },

        // 팀 거절
        goReject() {
            alert(`${this.teamData.who}님의 팀 요청을 거절하였습니다.`)
            this.alarmDelete();
        },

        // 버튼 영역 열고 닫기
        buttonOn() {
            this.buttonOpen = !this.buttonOpen
        },
        
        // 날짜 체크
        dateCheck(date) {
        var diff = now - date
        var diff_sec = Math.floor(diff / 1000)
        var diff_min = Math.floor(diff_sec / 60)
        var diff_hour = Math.floor(diff_min / 60)
        var diff_day = Math.floor(diff_hour / 24)
        var diff_month = Math.floor(diff_day / 30)
        var diff_year = Math.floor(diff_month / 12)

        if (diff_year > 0) {
            var calyear = diff_year + '년 전'
            return calyear
        } else if (diff_month > 0) {
            var calmonth = diff_month + '달 전'
            return calmonth
        } else if (diff_day > 0) {
            var calday = diff_day + '일 전'
            return calday
        } else if (diff_hour > 0) {
            var calhour = diff_hour + '시간 전'
            return calhour
        } else if (diff_min > 0) {
            var calmin = diff_min + '분 전'
            return calmin
        } else if(diff_sec > 0) {
            var caltime = diff_sec + '초 전'
            return caltime
        } else {
            return '0초 전'
        }
        },
    }
    
}
</script>

<style scoped>
.button-open-check{
    position: absolute;
    height: 70px;
    width: 100%;
    z-index: 500;
    opacity: 0;
}
.scrap{
    padding: 15px 0 15px 0;
    height: 70px;
    overflow: hidden;
    transition: height 0.3s;

    
}
.button-open-check:checked ~ .scrap{
    height: 115px;
}


.notice {
    font-size: 14px;
    white-space: pre-wrap;
    word-break: break-all;
}

hr {
    margin: 0;
}
.scrap-icon {
    float: left;
    height: 48px;
    width: 48px;
    padding: 10px;
    margin: 0 7px 0 7px;
    border: 1px solid green;
    border-radius: 100%;
    color: green;
}

.buttonArea {
    margin-top: 20px;
    padding-bottom: 5px;
}

.buttonArea > button {
    padding: 5px;
    width: 30%;
    font-size: 14px;
    border-radius: 3px;
}
</style>