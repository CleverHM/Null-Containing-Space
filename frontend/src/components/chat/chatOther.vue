<template>
    <div class="chat-box">
        <div class="chat-name">{{ nickname }}</div>
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
    position: relative;
}
.chat-name{
    text-align: left;
    font-weight: bold;
    padding: 0 0 0 20px;
    right: 0;
}
.chat-content{
    width: 40vw;
    background: #C4BCB8;
    padding: 15px;
    text-align: left;
    font-weight: 900;
    border-radius: 10px;
    color: #fff;
    margin: 0 0 10px 0;
    white-space: pre-wrap;
    word-break: break-all;
    position: relative;
    left: 20px;
}
.chat-content:before{
    content: "";
    width: 0px;
    height: 0px;
    position: absolute;
    border-left: 10px solid transparent;
    border-right: 10px solid #C4BCB8;
    border-top: 10px solid #C4BCB8;
    border-bottom: 10px solid transparent;
    left: -14px;
    top: 6px;
}
</style>