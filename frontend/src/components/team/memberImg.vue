<template>
  <div id="member-img">
    <router-link :to="{ name: 'profile', params: { nickname: memberData.nickname }}">
      <div class="userImg">
          <img v-if="imgExist" :src="'data:image/png;base64, ' + memberData.file" alt="user-image">
          <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
          <div class="user-name d-flex justify-content-center">
            {{ memberData.nickname }}
          </div>
          <div v-if="isLeader" class="leaderImg"></div>
      </div>
    </router-link>
  </div>
</template>

<script>

export default {
  name: 'member-img',
  props: [
    'memberData',
    'isLeader',
  ],

  data() {
    return {
      imgExist: false,
    }
  },
  
  created() {
    if (this.memberData.file == null) {
      this.imgExist = false
    } else {
      this.imgExist = true
    }
  },


}
</script>

<style scoped>
#member-img {
  position: relative;
}

.leaderImg {
  position: absolute;
  top: -5px;
  left: -5px;
  width: 110px;
  height: 110px;
  border-radius: 100%;
  border: 7px solid #FFCC33;
}

.userImg {
  display: block;
  width: 100px;
  height: 100px;
  border-radius: 100%;
  background-color: #eeeeee;
  overflow: hidden;
}

.userImg img {
  margin: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}


.user-name {
  position: absolute;
  color: #464545;
  font-size: 15px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-weight: bold;
  bottom: 5px;
  width: 100px;
  background-color: rgba( 255, 255, 255, 0.5 );
  white-space: nowrap; 
  overflow: hidden;
  text-overflow: clip;
}

</style>