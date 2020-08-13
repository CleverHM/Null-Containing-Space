<template>
  <div id="app" >
   <Navbar></Navbar>
   <!-- <subNav/> -->

    <!-- 유저이름: 
    <input
      v-model="userName"
      type="text"
    > -->
    <!-- <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <p>{{ item.userName }}: {{ item.content }} </p>
    </div> -->
    <div v-for="(item, idx) in recvList" :key="idx">
      <chatMe :content="item.content" v-if="item.userName === serverUser"/>
      <chatOther :nickname="item.userName" :content="item.content" v-if="item.userName != serverUser"/>

    </div>


    <div class="chat-input">
      <!-- <label for="name">내용: </label> -->
      <input
        v-model="message"
        name="message"
        type="text"
        @keyup.enter="sendMessage"
        autocomplete="off"
      >
      <button @click="sendMessage">입력</button>
    </div>

  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Navbar from '../../components/common/Navigation.vue'
import chatMe from '@/components/chat/chatMe.vue'
import chatOther from '@/components/chat/chatOther.vue'
import http from '@/util/http-common.js'

const storage = window.sessionStorage;

export default {
  name: 'Chat',

   components: {
    Navbar,
    chatMe,
    chatOther,
  },
  
  props: [
    'teamId',
    ],


  data() {
    return {
      // userName: "",
      message: "",
      recvList: [],
      serverUser: storage.NickName,
    }
  },
  created() {
    // Chat.vue가 생성되면 소켓 연결을 시도합니다.
    this.initialize(),
    this.connect()
  },
  methods: {
    initialize(){
      http.get(`/chat/initialize/${this.teamId}`).then(({data}) => {
            this.recvList = data;
      });
    },

    sendMessage () {
      if(this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    

    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          userName: storage.NickName,
          content: this.message,
          fakeid: this.teamId,
        };
        this.stompClient.send("/publish/chat/message",JSON.stringify(msg),{});
      }
    },

    connect() {
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          console.log(this.teamId)
          this.stompClient.subscribe("/subscribe/chat/"+this.teamId, res => {
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            console.log(JSON.parse(res.body));
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    }
  }
}
</script>

<style scoped>
#app {
  padding-top: 10px;
}
/* chat 입력창 */
.chat-input{
  width: 100%;
  bottom: 0px;
  position: fixed;
  display: flex;
  background-color: #EDECEA;
}
.chat-input input{
  border: 0;
  flex: 1;
}
.chat-input button{
  position: absolute;
  right: 0;
  border: 0;
  outline: 0;
  width: 60px;
  height: 50px;
}
</style>