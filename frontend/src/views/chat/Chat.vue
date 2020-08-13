<template>
  <div id="app">
   <Navbar></Navbar>
   <subNav/>

    <!-- 유저이름: 
    <input
      v-model="userName"
      type="text"
    > -->
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <p>{{ item.userName }}: {{ item.content }} </p>
    </div>


    내용: <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >

  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import http from '@/util/http-common.js'

const storage = window.sessionStorage;

export default {
  name: 'Chat',

   components: {
    Navbar,
    subNav,
  },
  
  props: [
    'teamId',
    ],


  data() {
    return {
      // userName: "",
      message: "",
      recvList: []
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

    sendMessage (e) {
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
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