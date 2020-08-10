<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
        <div class="contentArea">
            <!-- 로딩 페이지 -->
            <div v-if="loadingOn">
                <img src="@/assets/images/lionLoading.gif" alt="" style="width:100%;">
            </div>

            <!-- 매칭 간략화 화면 -->
            <div v-else>
                <div class="memberImg-area">
                    <MatchUserSmall v-for="member in members" :key="member.nickname" :userData="member" class="m-2"></MatchUserSmall>
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
    props: ["isLeader"],

    data() {
        return {
            loadingOn: true,
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
                setTimeout(this.delayfinish, 4000);
            }
        },

        // 딜레이 화면
        delayfinish(){
            this.loadingOn = false;
        },

        // 디테일 화면 켜기
        memberDetailOn() {
            this.$router.push({ name: 'MatchMember', params: { members: this.members }})
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
                this.members = res.data.member
                console.log(res.data)
                this.MatchPossible();
                storage.setItem("members", this.members);
                
            })
            .catch((err) => {
                console.log(err)
            })
        },

        MatchPossible() {
            if (this.members.leader == false) {
                this.$router.push({ name: 'Main' })
            }
        },

    }

}
</script>

<style scoped>

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

</style>