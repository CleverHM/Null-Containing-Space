<template>
    <div>
        <input type="radio" class="button-open-check" name="temp">
        <div class="scrap">
            <b-icon-bookmark-fill class="scrap-icon"></b-icon-bookmark-fill>
            <p class="notice">
            {{ snsData.content }}<br />
            <small class="noticeday">{{ diffTime }}</small>
            </p>
            <div class="button-list">
                <button v-if="snsData.flag == 2" class="button-left" @click="goDetail">게시물로 이동</button>
                <button v-else class="button-left" @click="goProfile">상대방 프로필</button>
                <button class="button-right" @click="deleteAlarm">삭제</button>
            </div>
        </div>
        
        <hr>
    </div>
    
</template>

<script>
import http from '@/util/http-common.js'


var now = new Date(); // 현재 시간 받아오기

export default {
    name: 'SNSNotice',
    props: [
        'snsData',
    ],
    created() {
        var postDate = new Date(this.snsData.createDate)
        this.diffTime = this.dateCheck(postDate);
        console.log(this.snsData)
    },
    data() {
        return {
            diffTime: null,
            checkedbutton: -1,
        }
    },
    methods: {
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
        goProfile() {
            this.$router.push({ name: 'profile', params: { nickname: this.snsData.who }})
        },
        deleteAlarm() {
            let formData = new FormData;
            formData.append('aid', this.snsData.aid)

            http.post('/alarm/delete', formData)
            .then(() => { this.$router.go() })
        },
        goDetail() {
            this.$router.push({ name: 'FeedDetail', params: { postId: this.snsData.pid }})
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
    height: 110px;
}
p{
    margin: 0;
    padding: 0;
    font-size: 14px;
}
hr{
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
.button-list{
    padding: 10px 0 0 0px ;
    width: 100%;
}
.button-list button{
    border: 0;
    outline: 0;
    width: 41%;
    color: #464545;
    font-size: 14px;
    border-radius: 3px;
    padding: 5px;
    
}
.button-left{
    background-color: #ACCCC4;
    margin: 0 6% 0 6%;
}
.button-right{
    background-color: #E2DFD8;
    margin: 0 2% 0 0;
}
</style>