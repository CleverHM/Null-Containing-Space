<template>
    <div class="message-box-holder">
        <div class="message-sender">{{ nickname }}</div>
        <div class="message-box message-partner">{{ content }}</div>
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
  margin: 0 0 10px;
  display: flex;
  flex-flow: column;
  align-items: flex-end;
}

.message-sender {
  font-size: 14px;
  margin: 10px 0 15px 10px;
  color: #464545;
  align-self: flex-start;
   color: #464545;
  text-decoration: none;
}

.message-box {
 
  padding: 6px 10px;
  border-radius: 6px 0 6px 0;
  position: relative;
  background: #E2DFD8;
  border: 2px solid #C4BCB8;
  color: #464545;
  font-size: 14px;
}
.message-box:after {
  content: "";
  position: absolute;
  border: 10px solid transparent;
  border-top: 10px solid #C4BCB8;
  border-right: none;
  bottom: -22px;
  right: 10px;
}

.message-partner {
  margin: 0 10px;
  background: #E2DFD8;
  border: 2px solid #C4BCB8;
  align-self: flex-start;
}

.message-partner:after {
  right: auto;
  bottom: auto;
  top: -22px;
  left: 9px;
  border: 10px solid transparent;
  border-bottom: 10px solid #C4BCB8;
  border-left: none;
}
</style>