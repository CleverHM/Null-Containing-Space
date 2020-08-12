<template>
    <div v-if="User" class="follow-item">
        <div class="follow-img" @click="goProfile">
            <img v-if="User.file" :src="'data:image/png;base64, ' + User.file" alt="image">
            <img v-else src="@/assets/images/default_image.png">
        </div>
        <div class="follow-nickname" @click="goProfile">{{ User.nickname }}</div>
        <div v-if="!IsMe">
            <button v-if="User.followFlag" class="unfollow-button" @click="unfollow">팔로우 취소</button>
            <button v-else class="follow-button" @click="follow">팔로우</button>
        </div>
        <hr />
    </div>
</template>

<script>
import http from '@/util/http-common.js'

const storage = window.sessionStorage

export default {
    name: 'followItem',
    props: [
        'User',
    ],
    created() {
        console.log(this.User)
    },
    computed: {
        IsMe() {
            if (storage.NickName === this.User.nickname) return true
            else return false
            
        },
    },
    methods: {
        goProfile() {
            this.$router.push({ name: 'profile', params: { nickname: this.User.nickname }});
        },
        // 팔로우상태 -> 1, 언팔로우상태 -> 0
        follow() {
            this.User.followFlag = 1
            var InputData = new FormData()
            InputData.append("From", window.sessionStorage.NickName)
            InputData.append("To", this.User.nickname)
            http.post("follow/user", InputData)
            .then(() => {
                this.alarm()
            })
        },
        unfollow() {
            this.User.followFlag = 0
            var InputData = new FormData()
            InputData.append("From", window.sessionStorage.NickName)
            InputData.append("To", this.User.nickname)
            http.post("follow/user", InputData)

        },
        alarm(){
            // 팔로우 알림 보내기
            var AlarmData = new FormData()
            AlarmData.append("mynickname", storage.NickName)
            AlarmData.append("tonickname", this.User.nickname)
            http.post("/alarm/followAlarm", AlarmData)
        },

    }

}
</script>

<style scoped>
.follow-item {
    position: relative;
    height: 60px;
    padding: 10px 10px 20px 10px;
    /* border-bottom: 1px solid #E2DFD8; */
}
.follow-img, .follow-nickname, .follow-button{
    cursor: pointer;
}
.follow-img {
    position: absolute;
    width: 50px;
    height: 50px;
    background-color: #464545;
    margin-bottom: 10px;
    border-radius: 100%;
    overflow: hidden;
}
.follow-img img {
    width: 100%;
    height: 100%;
    margin-bottom: 10px;
    
}
.follow-nickname {
    position: absolute;
    left: 80px;
    top: 20px;
    margin-bottom: 10px;
}
.follow-button {
    position: absolute;
    top: 15px;
    right: 15px;
    background-color: #464545;
    color: #f7f7f7;
    padding: 3px 7px;
    border-radius: 5px;
    font-size: 16px;
    border: 0;
    outline: 0;

}
.unfollow-button {
    position: absolute;
    top: 15px;
    right: 15px;
    background-color: #C4BCB8;
    color: #f7f7f7;
    padding: 3px 7px;
    border-radius: 5px;
    font-size: 16px;
    border: 0;
    outline: 0;

}
hr {
    margin-top: 55px;
}
</style>