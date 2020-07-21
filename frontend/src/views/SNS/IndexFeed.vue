<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <Navbar></Navbar>
      <subNav></subNav>
      <div class="d-flex justify-content-end align-items-center">
          <button class="btn-sort" style="background-color: #C4BCB8;">전체</button>
          <button class="btn-sort" style="background-color: blue;">QnA</button>
          <button class="btn-sort" style="background-color: black;">Tip</button>
      </div>

      <FeedItem />
      <FeedItem />
      <FeedItem />
      <FeedItem />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "../../components/SNS/FeedItem.vue";
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'

export default {
  props: ["keyword"],

  components: { 
    FeedItem,
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