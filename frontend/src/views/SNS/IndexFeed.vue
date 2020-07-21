<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <Navbar></Navbar>
      <subNav></subNav>
      <h1>뉴스피드</h1>

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

