<template>
    <div class="mypost" @click="goPostDetail">
        <!-- post image -->
        <div class="mypost-image">
            <img :src="'data:image/png;base64, ' + post.file" alt="image" class="img-part">
        </div>
        <div class="mypost-info">
            <div class="mypost-title-data">
                <!-- title -->
                <div class="mypost-title">{{ post.title }}</div>
                <!-- 작성일 -->
                <div class="mypost-date">{{ diffTime }}</div>
            </div>

            <!-- 조회수, 좋아요, 댓글 -->
            <div class="mypost-etc">
                <!-- 조회수 -->
                <div class="etc mypost-view"><b-icon-eye /> {{ post.viewCount }}</div>
                <!-- 좋아요 -->
                <div class="etc mypost-like"><b-icon-heart /> {{ post.likeCount }}</div>
                <!-- 댓글 -->
                <div class="etc mypost-comment"><b-icon-chat-left-dots /> {{ post.replyCount }}</div>


            </div>
        </div>
    </div>
</template>

<script>
var now = new Date(); // 현재 시간 받아오기


export default {
    name: 'myPostItem',
    props: [
        'post'
    ],
    created() {
        var postDate = new Date(this.post.date)
        this.diffTime = this.dateCheck(postDate)
    },
    data() {
        return {
            diffTime: '',
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
        goPostDetail() {
            this.$router.push({ name: 'FeedDetail', params: { postId: this.post.pid }})
        }
    }
    
}
</script>

<style scoped>
.mypost{
    position: relative;
    padding: 5px 0 5px 0;
    border-bottom: 1px solid #E2DFD8;
}
.mypost-image{
    display: inline-block;
    width: 70px; 
    height: 70px;

}
.mypost-image img{
    width: 70px;
    height: 70px;
}
.mypost-info{
    display: inline-block;
}
.mypost-title, .mypost-date{
    position: absolute;
}
.mypost-title {
    top: 5px;
    left: 80px;
    width: 40vw;
    overflow:hidden;
    text-overflow: ellipsis;
    white-space:nowrap;
    font-weight: bold;
    font-size: 18px;
}
.mypost-date{
    right: 10px;
    top: 5px;
}
.mypost-etc {
    position: absolute;
    bottom: 10px;
    left: 80px;
}
.mypost-etc div {
    display: inline;
    padding: 0 10px 0 0;
}
</style>