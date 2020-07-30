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
    name: 'getAbility2',
    methods: {
       
    }

}
</script>

<style>
.css-slider {
  height: 100vh;
  width: 100vw;
  position: relative;
  overflow: hidden;
  background: #fff;
  color: #fff;
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
  background-color: #ACCCC4;
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

header {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 900;
  width: 100%;
}
header label {
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 50%;
  width: 15px;
  height: 15px;
  margin: 20px 10px;
}
nav {
  position: absolute;
  top: 50%;
  margin-top: -42px;
  z-index: 900;
  width: 100%;
}
nav label { font-size: 50px; }

header label:hover { background: #2e353b; }

.prev {
  position: absolute;
  left: 10px;
}

.next {
  position: absolute;
  right: 10px;
}

.slide .prev, .slide .next { opacity: 0; }
[id^="slide"]:checked + .slide .prev, [id^="slide"]:checked + .slide .next {
  opacity: 1;
  -webkit-transition: all .5s .5s;
  transition: all .5s .5s;
}

.slide h1 {
  opacity: 0;
  -webkit-transform: translateY(100%);
  -ms-transform: translateY(100%);
  transform: translateY(100%);
  -webkit-transition: -webkit-transform .5s .5s, opacity .5s;
  transition: transform .5s .5s, opacity .5s;
}

[id^="slide"]:checked + .slide h1 {
  opacity: 1;
  -webkit-transform: translateY(0);
  -ms-transform: translateY(0);
  transform: translateY(0);
  -webkit-transition: all .5s .5s;
  transition: all .5s .5s;
}
</style>