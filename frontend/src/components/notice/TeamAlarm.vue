<template>
    <div>
        <div class="scrap" @click="buttonOpen = !buttonOpen">
            <b-icon-people-fill class="scrap-icon"></b-icon-people-fill>
            <div class="notice">
                {{ teamData.content }} <br/>
            <small class="noticeday ml-1">{{ diffTime }}</small>
            </div>
            <div v-if="buttonOpen" class="buttonArea">
                버튼버튼
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
        }
    },

    created() {
        var postDate = new Date(this.teamData.createDate)
        this.diffTime = this.dateCheck(postDate);
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
    }
    
}
</script>

<style scoped>
.scrap {
    padding: 15px 0 15px 0;
    
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
    margin: 0 10px 0 10px;
    border: 1px solid green;
    border-radius: 100%;
    color: green;
}

.buttonArea {
    margin-top: 10px;
}
</style>