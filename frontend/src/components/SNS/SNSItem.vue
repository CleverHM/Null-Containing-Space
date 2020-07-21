<template>
  <div id="SNSItem">

    <!-- user 부분 -->
    <div class="user-part d-flex flex-row align-items-center">
      <div class="user-img"></div>
      <div class="flex-column ml-2">
        <div class="user-name">{{ SNS.username}}</div>
        <div class="date-diff">{{ SNS.data}}</div>
      </div>
    </div>

    <!-- SNS 이미지, 제목 부분 -->
    <div class="SNS-img">
      <b-img v-bind="mainProps" rounded alt="Rounded image"></b-img>
    </div>

    <!-- 제목 -->
    <div class="SNS-content">
      {{ SNS.title }}
    </div>

    <!-- 해시태그 -->
    <div class="hash-tags mb-3">
      <span>Algorithm</span>
      <span>Python</span>
    </div>
    
    <!-- SNS 좋아요, 댓글수 부분  -->
    <div class="icon-part d-flex justify-content-around">
      <div>
        <b-icon icon="heart-fill" font-scale="1.2" class="style-icon"></b-icon>
        <span>0</span>
      </div>
      <div>
        <b-icon icon="chat-square-fill" font-scale="1.2" class="style-icon"></b-icon>
        <span>0</span>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "SNSItemn",
  
  data() {
      return {
        mainProps: { 
          blank: true,
          blankColor: '#c6dfd6',
          height: 200,
          class: 'm1'
        },
        SNS: {
            username: '알골마스터',
            data: '9시간 전',
            title: '.....ABCDEFGHIJK',
        }
      }
  },
}
</script>

<style scoped>
#SNSItem {
  background-color: white;
  border-radius: 5px;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 10px;
}

.user-img {
  background-color: #C4BCB8;
  border-radius: 50%;
  width: 50px;
  height: 50px;
}

.user-name {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #464545;
  font-weight: bold;
  font-size: 16px;
}

.date-diff {
  color: #C4BCB8;
  font-size: 14px;
}

.SNS-img > img {
  width: 100%;
  margin: 5px 0px 10px 0px;
}

.icon-part {
  margin: 5px 5px 0px 5px;
}

.icon-part > div > span {
  color: #C4BCB8;
  margin: 0px 0px 0px 7px;
}

.hash-tags > span {
  background-color: #c6dfd6;
  border-radius: 20px;
  padding: 7px;
  font-size: 13px;
  margin-right: 10px;
}

.style-icon {
  color: #C4BCB8;
}

.SNS-content {
  margin: 0px 0px 10px 5px;
  font-weight: bold;
  font-size: 16px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

</style>