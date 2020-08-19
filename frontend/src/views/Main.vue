<template>
    <div id="Main">
        <Navbar></Navbar>
        <subNav/>
        <div v-if="delayOn">
            <div class="loading-container d-flex justify-content-center align-content-center align-items-center">
                <div class="loading">
                </div>
                <div id="loading-text">팀 정보를<br/> 받아오는 중입니다</div>
            </div>
        </div>
        <div v-if="!delayOn" class="main-part">
            <!-- 팀이 있을 때 !-->
            <div v-if="team.status">
                <TeamIn :teamData="team.teamdate" :isMember="true"/>
            </div>
            <!-- 팀이 없을 때 -->
            <div v-if="!team.status">
                <!-- 팀원 등록했을 때 -->
                <div v-if="team.matchok">
                    <NoTeam :userData="team"/>
                </div>

                <!-- 팀원 등록을 안했을 때 -->
                <div v-if="!team.matchok">
                    <TeamOut/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../components/common/Navigation.vue'
import subNav from '../components/common/subnav.vue'
import TeamIn from '../components/team/TeamIn.vue'
import TeamOut from '../components/team/TeamOut.vue'
import NoTeam from '../components/team/NoTeam.vue'
import http from "../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;


export default {
  name:"Main",
  components: {
      Navbar,
      subNav,
      TeamIn,
      TeamOut,
      NoTeam,
  },
  

  data() {
    return {
      team: {
          status: null,
      },
      delayOn: true,
    }
  },
  
  created() {
      
        storage.removeItem("members1");
        storage.removeItem("members2");

        var timerSet = setTimeout(this.delayfinish, 5000);

        http
        .get(`/team/${storage.getItem("NickName")}/${1}`)
        .then((res) => {
            this.team = res.data
            clearTimeout(timerSet);
            this.delayfinish();
        })
        .catch((err) => {
            console.log(err)
        })
    },

    methods: {
        delayfinish(){
            this.delayOn = false;
        },
    },
};
</script>

<style scoped>
.main-part {
    margin: 60px 0px 30px 0px;
}
.btn-study{
    padding: 7px;
    border-radius: 4px;
    font-size: 15px;
    background-color: #464545;
    color: #f7f7f7;

}
/** BEGIN CSS **/
@keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-moz-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-webkit-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-o-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-moz-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-webkit-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-o-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}

@-moz-keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}

@-webkit-keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}

@-o-keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}
.loading-container {
    height: 500px;
}
.loading {
    height: 100px;
    position: relative;
    width: 100px;
    border-radius: 100%;
}


.loading-container { margin: 40px auto }

.loading {
    border: 2px solid transparent;
    border-color: transparent #ACCCC4 transparent #ACCCC4;
    -moz-animation: rotate-loading 1.5s linear 0s infinite normal;
    -moz-transform-origin: 50% 50%;
    -o-animation: rotate-loading 1.5s linear 0s infinite normal;
    -o-transform-origin: 50% 50%;
    -webkit-animation: rotate-loading 1.5s linear 0s infinite normal;
    -webkit-transform-origin: 50% 50%;
    animation: rotate-loading 1.5s linear 0s infinite normal;
    transform-origin: 50% 50%;
}

.loading-container:hover .loading {
    border-color: transparent #464545 transparent #464545;
}
.loading-container:hover .loading,
.loading-container .loading {
    -webkit-transition: all 0.5s ease-in-out;
    -moz-transition: all 0.5s ease-in-out;
    -ms-transition: all 0.5s ease-in-out;
    -o-transition: all 0.5s ease-in-out;
    transition: all 0.5s ease-in-out;
}

#loading-text {
    position: absolute;
    -moz-animation: loading-text-opacity 2s linear 0s infinite normal;
    -o-animation: loading-text-opacity 2s linear 0s infinite normal;
    -webkit-animation: loading-text-opacity 2s linear 0s infinite normal;
    animation: loading-text-opacity 2s linear 0s infinite normal;
    color: #464545;
    font-size: 10px;
    font-weight: bold;
    margin-top: 285px;
    opacity: 0;
    text-align: center;
    text-transform: uppercase;
    top: 0;
    width: 100px;
}
</style>