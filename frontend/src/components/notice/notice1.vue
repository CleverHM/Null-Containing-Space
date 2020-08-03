<template>
    <div>
        <div class="scrap">
            <b-icon-bookmark-fill class="scrap-icon"></b-icon-bookmark-fill>
            <p class="notice">
            <!-- 알골마스터님이 당신의 게시글을 스크랩 하였습니다.<br /> -->
            알골마스터님이 당신을 팀으로 초대하였습니다.<br />
            <small class="noticeday">07/21 21:00</small>
            </p>
        </div>
        <hr>
    </div>
    
</template>

<script>
export default {
    name: 'notice1',
}
</script>

<style scoped>
.scrap{
    padding: 15px 0 15px 0;
    
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
    margin: 0 10px 0 10px;
    border: 1px solid green;
    border-radius: 100%;
    color: green;

}
</style>