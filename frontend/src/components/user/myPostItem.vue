<template>
    <div class="mypost">
        <!-- post image -->
        <div class="mypost-image">

        </div>
        <div class="mypost-info">
            <!-- title -->
            <div class="mypost-title">내게시물 제목</div>
            <!-- 조회수, 좋아요, 댓글 -->
            <div class="mypost-etc">
                <!-- 작성일 -->
                <div class="etc mypost-view">1일 전</div>
                <!-- 조회수 -->
                <div class="etc mypost-view"><b-icon-eye /> 4</div>
                <!-- 좋아요 -->
                <div class="etc mypost-like"><b-icon-heart /> 6</div>
                <!-- 댓글 -->
                <div class="etc mypost-comment"><b-icon-chat-left-dots /> 3</div>


            </div>
        </div>
    </div>
</template>

<script>
var now = new Date(); // 현재 시간 받아오기

export default {
    name: 'myPostItem',
    method: {
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
.mypost{
    position: relative;
    padding: 10px 15px 10px 15px;
    border-bottom: 1.5px solid #464545;
}
.mypost-image, .mypost-info {
    display: inline-block;
}
.mypost-image{
    width: 80px;
    height: 80px;
    background-color: #464545;
}
.mypost-title{
    position: absolute;
    top: 20px;
    left: 110px;
    font-size: 20px;
    font-weight: bold;
}
.mypost-etc {
    position: absolute;
    bottom: 20px;
    left: 110px;
}
.mypost-etc .etc {
    display: inline-block;
    padding-right: 10px;
}
</style>