<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <Navbar></Navbar>
      <subNav></subNav>
      <div class="d-flex justify-content-end align-items-center">
          <button class="btn-sort" style="background-color: #C4BCB8;">전체</button>
          <button class="btn-sort" style="background-color: #ACCCC4;">QnA</button>
          <button class="btn-sort" style="background-color: #464545;">Tip</button>
      </div>

      <SNSItem />
      <SNSItem />
      <SNSItem />
      <SNSItem />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import SNSItem from "../../components/SNS/SNSItem.vue";
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'

export default {
  props: ["keyword"],

  components: { 
    SNSItem,
    Navbar,
    subNav,
  },
};
</script>


<style scoped>
.btn-sort{
    width: 70px;
    margin: 10px 5px 10px 5px;
    padding: 5px;
    border-radius: 4px;
    font-size: 14px;
    color: #f7f7f7;
}
</style>