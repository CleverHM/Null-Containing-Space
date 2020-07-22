<template>
  <div id="sns-comment">
    <!-- user 부분 -->
    <div class="user-part d-flex justify-content-between align-items-center mb-2">
      <div class="d-flex align-items-center">
        <div class="comment-img"></div>
        <div class="comment-name ml-2">{{ Comment.user }}</div>
      </div>
      <div class="comment-date">{{ Comment.created_at }}</div>
    </div>
    <!-- 내용 -->
    <div class="comment-content">
      {{ Comment.content }}
    </div>
  </div>
</template>

<script>
export default {
  name: "SNSComment",
  data() {
    return {
      Comment: {
        user: '알골병아리',
        created_at: '2020-07-21',
        content: 'Korean Lorem Ipsum in Hangul script is a mix of all Korean chars according to common frequency.'
      }
    }
  },

}
</script>

<style>

#sns-comment {
  margin: 15px 3px 0px 3px;
}

.comment-img {
  border: 1px solid;
  color: #C4BCB8;
  border-radius: 50%;
  width: 30px;
  height: 30px;
}

.comment-name {
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size: 13px;
  font-weight: bold;
}

.comment-date {
  font-size: 12px;
  color: #E2DFD8;
}

.comment-content {
  font-size: 14px;
  color: #464545;
}

</style>