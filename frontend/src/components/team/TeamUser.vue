<template>
  <div id="TeamUser">
    <div class="user-area d-flex flex-row">
        <!-- 유저 이미지 -->
        <div class="img-area d-flex align-content-center">
            <img v-if="imgExist" :src="'data:image/png;base64, ' + teamData.leaderNickname.file" alt="user-image">
            <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
            <div class="leaderImg"></div>
        </div>

        <!-- 유저 정보 -->
        <div class="info-area">
            <!-- 팀이름 -->
            <div class="texttags">
                팀명: {{ teamData.title }}
            </div>
            <!-- 유저 이름 -->
            <div class="texttags">
                팀장: {{ teamData.leaderNickname.nickname }}
            </div>

            <!-- 버튼 영역 -->
            <div class="btn-area">
                <button class="btn-style" style="background-color: #ACCCC4; width: 100%;" @click="goTeamDetail">팀 정보보기</button>
            </div>

        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "TeamUser",
    props: ["teamData"],
    data() {
        return {
            imgExist: false,  
        }
    },

    created() {
        if (this.teamData.leaderNickname.file == null) {
            this.imgExist = false
        } else {
            this.imgExist = true
        }

    },

    methods: {
        goTeamDetail() {
            this.$router.push({ name: 'TeamInfo', params: { teamId: this.teamData.teamid }})
        }
    },

}
</script>

<style scoped>
#TeamUser {
  position: relative;
}
.user-area {
    margin: 15px 10px 15px 10px;
    padding: 15px;
    border-radius: 20px;
    /* background-color: rgb(252, 252, 252); */
    border: 2px solid white;
}

.img-area {
    display: inline-block;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-left: 10px;
    width: 90px;
    height: 90px;
    border-radius: 100%;
    background-color: grey;
    overflow: hidden;
}

.img-area > img {
    max-width: 100%;
    height: auto; 
}

.leaderImg {
  position: absolute;
  top: 16px;
  left: 30px;
  width: 99px;
  height: 99px;
  border-radius: 100%;
  border: 7px solid #FFCC33;
}

.info-area {
    width: 55%;
    margin-left: 20px;
}


.texttags {
    width: 90%;
    text-align: left;
    margin-bottom: 7px;
    margin-left: 8px;
    font-size: 14px;
    font-weight: bold;
    color: #464545;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    white-space: nowrap;
    word-wrap: break-word;
    text-overflow: ellipsis;
    overflow: hidden;
}

.btn-area {
    margin-left: 5px;
}

.btn-style {
    padding: 6px;
    color: white;
    border-radius: 10px;
}



</style>