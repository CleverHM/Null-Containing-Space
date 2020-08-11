<template>
  <div id="MatchUser">
      
    <div class="user-area d-flex flex-row">
        <!-- 유저 이미지 -->
        <div class="img-area d-flex align-content-center">
            <img v-if="imgExist" :src="'data:image/png;base64, ' + userData.file" alt="user-image">
            <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
        </div>

        <!-- 유저 정보 -->
        <div class="info-area">

            <!-- 유저 이름 -->
            <div class="d-flex align-items-center justify-content-between texttags">
                <span>
                    {{ userData.nickname }}
                </span>
                <span v-if="noprefer">
                    ({{ subjectCheck }})
                </span>
            </div>

            <div v-if="!noprefer" class="d-flex align-items-center texttags">
                {{ subjectCheck }}
            </div>

            <div v-if="noprefer" class="percent-area">
                <b-progress 
                    :value="userData.percent"
                    class="percent-bar"
                    variant="success"
                    :key="userData.nickname"
                    :show-progress="showProgress">
                    <b-progress-bar :value="userData.percent" :label="`${userData.percent}%`"></b-progress-bar>
                </b-progress>
                <div class="back-bar"></div>
            </div>


            <!-- 버튼 영역 -->
            <div class="btn-area d-flex justify-content-between">
                <button style="background-color: #E2DFD8;" @click="goUserProfile">유저 정보</button>
                <button style="background-color: #ACCCC4;" @click="teamJoinRequest">팀원 요청</button>
            </div>

        </div>
    </div>
  </div>
</template>

<script>

import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
    name: "MatchUser",
    props: [
        'userData',
        'noprefer',
        ],

    data() {
        return {
            userImg: false,
            userData2: {
                nickname: '알골마스터',
                file: null,
                preferProject: 1,
            },
            basic: {
                subjects: [
                    '웹 기술 프로젝트',
                    '웹 디자인 프로젝트',
                    'IOT 프로젝트',
                ],
            },
            subjectCheck: '',
            imgExist: false,  
            animated: true,
            showProgress: true,
        }
    },

    created() {
        if (this.userData.file == null) {
            this.imgExist = false
        } else {
            this.imgExist = true
        }

        var num = Number(this.userData.preferProject) - 1
        this.subjectCheck = this.basic.subjects[num]
    },

    methods: {
        goUserProfile() {
            this.$router.push({ name: 'profile', params: { nickname: this.userData.nickname }})
        },

        // 팀 가입 권유
        teamJoinRequest() {
            let formData = new FormData;
            formData.append("mynickname", storage.getItem("NickName"))
            formData.append("tonickname", this.userData.nickname)
            
            http
            .post("/alarm/teamAlarm", formData)
            .then((res) => {
                alert(`${this.userData.nickname}님에게 팀원 요청을 보냈습니다.`)
            })
            .catch((err) => {
                console.log(err)
            })
        }
    },

}
</script>

<style scoped>

*:focus { outline:none; }

.user-area {
    margin: 15px 10px 15px 10px;
    padding: 15px;
    border-radius: 20px;
    /* background-color: rgb(252, 252, 252); */
    border: 2px solid white;
}

.img-area {
    display: inline-block;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 90px;
    height: 90px;
    border-radius: 100%;
    background-color: grey;
    overflow: hidden;
}

.img-area > img {
    width: 100%;
}

.info-area {
    width: 65%;
    margin-left: 20px;
}


.texttags {
  margin: 7px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.btn-area {
    margin-left: 5px;
}

.btn-area > button {
    padding: 6px;
    color: white;
    border-radius: 10px;
    width: 48%;
}

.none-area {
    height: 21px;
}

.percent-area {
    position: relative;
    margin: 13px 7px 13px 7px;
}

/* 애니메이션 적용 */

.percent-bar {
    height: 9px;
    font-size: 8px;
    background-color: #E2DFD8;
    color: #ACCCC4;
    animation: progressbar 3s ease-out;
}

@keyframes progressbar { 0% { width: 0%; } 100% { width: 100%; } }

.back-bar {
    position: absolute;
    top: 0;
    z-index: -1;
    height: 9px;
    width: 100%;
    background-color: #E2DFD8;
    border-radius: 20px;
}

</style>