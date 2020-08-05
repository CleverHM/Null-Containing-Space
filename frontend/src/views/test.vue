<template>
    <div class="css-slider">
        <input id="slide-1" type="radio" name="slides" checked>
        <section class="slide slide-one">
            <h1>Pure CSS Slider</h1>
            <nav>
            <label for="slide-3" class="prev">&#10094;</label>
            <label for="slide-2" class="next">&#10095;</label>
            </nav>
        </section>
        <input id="slide-2" type="radio" name="slides">
        <section class="slide slide-two">
            <h1>Slide Two</h1>
            <nav>
            <label for="slide-1" class="prev">&#10094;</label>
            <label for="slide-3" class="next">&#10095;</label>
            </nav>
        </section>
        <input id="slide-3" type="radio" name="slides">
        <section class="slide slide-three">
            <h1>Slide Three</h1>
            <nav>
            <label for="slide-2" class="prev">&#10094;</label>
            <label for="slide-1" class="next">&#10095;</label>
            </nav>
        </section>
        <header>
            <label for="slide-1" id="slide-1"></label>
            <label for="slide-2" id="slide-2"></label>
            <label for="slide-3" id="slide-3"></label>
        </header>
    </div>
</template>

<script>
export default {
    name: 'test'
}
</script>

<style scoped>
.css-slider {
  height: 100vh;
  width: 100vh;
  position: relative;
  overflow: hidden;
  background: white;
  color: #000;
  text-align: center;
}
label {
  cursor: pointer;
  display: inline-block;
}
.slide {
  height: 100%;
  width: 100%;
  max-width: 1920px;
  position: absolute;
  top: 0;
  left: 100%;
  z-index: 10;
  padding: 8em 0px;
  background-color: white;
  background-position: 50% 50%;
  background-size: cover;
  -webkit-transition: left 0s .75s;
  transition: left 0s .75s;
}
[id^="slide"]:checked + .slide {
  left: 0;
  z-index: 100;
  -webkit-transition: left .65s ease-out;
  transition: left .65s ease-out;
}

</style>