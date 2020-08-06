<template>
    <div id="Main">
        <Navbar></Navbar>
        <subNav/>
        <div class="main-part">
            <!-- 팀이 있을 때 !-->
            <div v-if="teamCheck">
                <TeamIn/>
            </div>
            <!-- 팀이 없을 때 -->
            <div v-else>
                <TeamOut/>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../components/common/Navigation.vue'
import subNav from '../components/common/subnav.vue'
import competitionItem from '../components/main/competitionItem.vue'
import TeamIn from '../components/team/TeamIn.vue'
import TeamOut from '../components/team/TeamOut.vue'
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
  },
  

  data() {
    return {
      teamCheck: false,
    }
  },
  
  created() {
      let formData = new FormData;
      formData.append("nickname", storage.getItem("NickName"));

      http
      .post("/team/exist", formData)
      .then((res) => {
          console.log(res)
          console.log(res.data)
      })
      .catch((err) => {
          console.log(err)
      })
  },
};
</script>

<style scoped>
.main-part {
    margin: 60px 10px 30px 10px;
}
.btn-study{
    padding: 7px;
    border-radius: 4px;
    font-size: 15px;
    background-color: #464545;
    color: #f7f7f7;

}
</style>