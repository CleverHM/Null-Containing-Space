<template>
    <div class="message-box-holder">
        <!-- <div class="chat-name">{{ nickname }}</div> -->
        <div class="message-box">{{ content }}</div>
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
.message-box-holder {
  width: 100%;
  margin: 0 0 10px 0;
  display: flex;
  flex-flow: column;
  align-items: flex-end;
}
.message-box {
  margin: 10px 10px 0 10px;
  padding: 6px 10px;
  border-radius: 6px 0 6px 0;
  position: relative;
  background: #D3E8EB;
  border: 2px solid #BAD1CC;
  color: #464545;
  font-size: 14px;
  white-space: pre-wrap;
  word-break: break-all;
}
.message-box:after {
  content: "";
  position: absolute;
  border: 10px solid transparent;
  border-top: 10px solid #BAD1CC;
  border-right: none;
  bottom: -22px;
  right: 10px;
}
</style>