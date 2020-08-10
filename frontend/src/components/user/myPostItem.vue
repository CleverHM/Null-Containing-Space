<template>
    <div class="myPostItem">
        <!-- post image -->
        <div class="mypost-image">

        </div>
        <div class="mypost-info">
            <!-- title -->
            <div class="mypost-title"></div>
            <!-- 조회수, 좋아요, 댓글 -->
            <div class="mypost-etc">
                <!-- 조회수 -->
                <div class="mypost-view"></div>
                <!-- 좋아요 -->
                <div class="mypost-like"></div>
                <!-- 댓글 -->
                <div class="mypost-comment"></div>


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

</style>