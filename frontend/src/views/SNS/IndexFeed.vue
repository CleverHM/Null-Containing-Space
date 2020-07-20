<template>
  <div class="feed newsfeed">
    <div class="wrapB">
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

export default {
  props: ["keyword"],

  components: { FeedItem }
};
</script>

