<template>
  <div id="TeamMatching">
    <div class="matching-part">
      <div v-if="loading">
        loading
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "StudyMatching",

  data() {
    return {
      loading: false,
    }
  },

  created() {
    this.loading = true;
  },

  methods: {
    // 버튼이 눌리거나 첫 화면
    matchingRequest() {
      this.loading = true;
    },


  },
}
</script>

<style>

</style>