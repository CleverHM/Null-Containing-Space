<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
        <div class="contentArea">
            <!-- 로딩 페이지 -->
            <div v-if="loadingOn">
                <img src="@/assets/images/lionLoading.gif" alt="" style="width:100%;">
            </div>

            <!-- 매칭된 화면 -->
            <div v-else>
                <div v-if="matchNodetail">
                    <!-- 매칭 간략화 화면 -->
                    <div class="memberImg-area d-flex justify-content-center">
                        <MatchUserSmall v-for="member in members" :key="member.nickname" :userData="member"></MatchUserSmall>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button class="btnForm" @click="memberDetailOn">자세히 보기</button>
                        <button class="btnForm ml-2" @click="reMatchOn">다시 매칭하기</button>
                    </div>

                </div>

                <div v-else>
                    <div class="match-area">
                        <MatchUser v-for="member in members" :key="member.nickname" :userData="member"></MatchUser>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button class="btnForm" @click="reMatchOn">다시 팀원 매칭하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import MatchUser from '../../components/team/MatchUser.vue'
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
        MatchUser,
        MatchUserSmall,
    },
    props: ["isLeader"],

    data() {
        return {
            loadingOn: true,
            matchNodetail: true,
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
            this.matchNodetail = false;
        },

        // 다시 매칭하기
        reMatchOn() {
            this.loadingOn = true;
            this.matchNodetail = true;
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

</style>