<template>
    <div class="chat-box">
        <!-- <div class="chat-name">{{ nickname }}</div> -->
        <div class="chat-content">{{ content }}</div>
    </div>
</template>

<script>
export default {
    name: "chatMe",
    props: [
        'nickname',
        'content',
    ]
    
}
</script>

<style scoped>
.chat-box{
    /* position: relative; */
}
.chat-name{
    text-align: right;
    padding: 0 40px 0 0;
    right: 0;
}
.chat-content{
    width: 40vw;
    background: #c6dfd6;
    padding: 15px;
    text-align: right;
    font-weight: 900;
    border-radius: 10px;
    color: #fff;
    margin: 0 0 10px 0;
    white-space: pre-wrap;
    word-break: break-all;
    position: relative;
    left: 55vw;
}
.chat-content:before{
    content: "";
    width: 0px;
    height: 0px;
    position: absolute;
    border-left: 10px solid #c6dfd6;
    border-right: 10px solid transparent;
    border-top: 10px solid #c6dfd6;
    border-bottom: 10px solid transparent;
    right: -14px;
    top: 6px;
}
</style>