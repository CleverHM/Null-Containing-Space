<template>
  <div id="app">
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>
<script>
import "./components/css/style.scss";
export default {
  name: "app",
 
};
</script>
