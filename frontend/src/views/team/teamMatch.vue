<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
        
            <!-- 로딩 페이지 -->
            <div class="loadingpage" v-if="loadingOn">
                <div class="loading">
                    <span>L</span>
                    <span>O</span>
                    <span>A</span>
                    <span>D</span>
                    <span>I</span>
                    <span>N</span>
                    <span>G</span>
                    <span>.</span>
                    <span>.</span>
                    <span>.</span>
                </div>
                
                <img class="loadingimg" src="@/assets/images/bear.gif" alt="">
            </div>

            <!-- 매칭 간략화 화면 -->
            <div v-else>
                <div class="contentArea">
                    <div class="memberImg-area">
                        <MatchUserSmall v-for="member in matchData.prefermember" :key="member.nickname" :userData="member" class="m-2"></MatchUserSmall>
                        <MatchUserSmall v-for="member in matchData.noprefermember" :key="member.nickname" :userData="member" class="m-2"></MatchUserSmall>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button class="btnForm" @click="memberDetailOn">자세히 보기</button>
                        <button class="btnForm ml-2" @click="reMatchOn">다시 매칭하기</button>
                    </div>
                </div>

            </div>
        
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import MatchUserSmall from '../../components/team/MatchUserSmall.vue'
import httpCommon from '../../util/http-common'
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
    name: 'teamMatch',
    components: {
        Navbar,
        subNav,
        MatchUserSmall,
    },

    data() {
        return {
            loadingOn: true,
            MatchData: null,
            members: null,
        }
    },

    created() {
        this.MatchReceive();
        this.isLoading();
    },

    methods: {
        // 로딩하는가?
        isLoading() {
            if (this.loadingOn) {
                setTimeout(this.delayfinish, 5000);
            }
        },

        // 딜레이 화면
        delayfinish(){
            this.loadingOn = false;
        },

        // 디테일 화면 켜기
        memberDetailOn() {
            this.$router.push({ name: 'MatchMember', params: { members1: this.matchData.prefermember, members2: this.matchData.noprefermember }})
        },

        // 다시 매칭하기
        reMatchOn() {
            this.loadingOn = true;
            this.isLoading();
            this.MatchReceive();
        },

        MatchReceive() {
            let formData = new FormData;
            formData.append("nickname", storage.getItem("NickName"));

            http
            .post('/match/teammember', formData)
            .then((res) => {
                this.matchData = res.data
                console.log(res.data)
                this.MatchPossible();
                storage.setItem("members1", JSON.stringify(this.matchData.prefermember));
                storage.setItem("members2", JSON.stringify(this.matchData.noprefermember));
                
            })
            .catch((err) => {
                console.log(err)
            })
        },

        MatchPossible() {
            if (this.matchData.leader == false) {
                this.$router.push({ name: 'Main' })
            }
        },

    }

}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Amatic+SC:wght@700&display=swap');

*:focus { outline:none; }

.contentArea {
    margin-top: 70px;
}

.btnForm {
    padding: 10px;
    margin: 10px 0px 20px 0px;
    border-radius: 20px;
    background-color: #ACCCC4;
    color: white;
    font-weight: bold;
    font-size: 17px;
}

.memberImg-area {
    width: 60%;
    margin-left: 50%;
    margin-top: 0%;
    transform:translate(-40%, 0%);
}

.loading{
    padding-top: 20%;
    position: relative;
    font-family: 'Amatic SC', cursive;
    text-align: center;
    font-size: 50px;
    color: white;
    opacity: 0.7;
}

.loading span {
  display: inline-block;
  margin: 0 -.05em;
  animation: loading .7s infinite;
  padding: 1px 2px 3px 4px;
}
.loading span:nth-child(2) {
  animation-delay: .1s;
}
.loading span:nth-child(3) {
  animation-delay: .2s;
}
.loading span:nth-child(4) {
  animation-delay: .3s;
}
.loading span:nth-child(5) {
  animation-delay: .4s;
}
.loading span:nth-child(6) {
  animation-delay: .5s;
}
.loading span:nth-child(7) {
  animation-delay: .6s;
}
.loading span:nth-child(8) {
  animation-delay: .7s;
}
.loading span:nth-child(9) {
  animation-delay: .8s;
}
.loading span:nth-child(10) {
  animation-delay: .9s;
}
@keyframes loading {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(5px);
  }
}

.loadingpage{
    background-color:#1c272a;
    filter: grayscale(80%) opacity(0.6); 
    height:600px;
}

.loadingimg{
    margin-top: 20.5%;
    width:100%;
    filter: grayscale(80%) opacity(0.6); 
}
.loadingimg:hover { filter: none; }

</style>