<template>
    <div class="fixed-top">
        <div class="Navi">
            <b-icon-arrow-left class="arrow-left-icon" scale="1.3"></b-icon-arrow-left>
            <p>블라블라 SNS</p>
            <div>
            <b-icon-search class="search-icon mx-4" scale="1.3"></b-icon-search>
            <b-icon-person-fill class="person-icon" scale="1.3"></b-icon-person-fill>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  name:"NavBar",
};
</script>

<style scoped>
.Navi{
    background-color: #ACCCC4;
    height: 50px;

}
.Navi p{
    margin: 0;
    line-height:50px;
    text-align : center;
    font-weight: bold;
    font-size: 1.11em;
}
.arrow-left-icon{
    position: fixed;
    left:20px;
    top:0;
    height:50px;
}
.search-icon{
    position: fixed;
    right:35px;
    top:0;
    height:50px;
}
.person-icon{
    position: fixed;
    right:20px;
    top:0;
    height:50px;
}
</style>