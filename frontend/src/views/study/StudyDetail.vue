<template>
  <div id="studyDetail">
    <Navbar></Navbar>
    <subNav></subNav>

  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'

export default {
  name: "studyDetail",
  components: {
    Navbar,
    subNav,
  },
}
</script>

<style>

</style>