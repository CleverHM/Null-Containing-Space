<template>
    <div>
        <!-- PAGE ONE -->
        <section id="page-1" class="page">
            <h1>Css Transition</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat aliquid sed dolor, sunt possimus numquam deleniti repellendus ad adipisci cum natus molestiae consequatur deserunt porro beatae asperiores odio fuga quae quos debitis quaerat unde! Consequatur nulla dolore consectetur atque sapiente minus nobis recusandae exercitationem ad, illum, doloribus, fugiat laboriosam ratione.</p>  
            <div>
                <a href="#page-2" class="btn" @click="clickbtn">
                Next Page <i class="fas fa-arrow-circle-down"></i>
                </a>
            </div>
        </section>

        <!-- PAGE TWO -->
        <section id="page-2" class="page">
            <h1>Page 2</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat aliquid sed dolor, sunt possimus numquam deleniti repellendus ad adipisci cum natus molestiae consequatur deserunt porro beatae asperiores odio fuga quae quos debitis quaerat unde! Consequatur nulla dolore consectetur atque sapiente minus nobis recusandae exercitationem ad, illum, doloribus, fugiat laboriosam ratione.</p>  
            <div>
                <a href="#page-1" class="btn btn-dark" @click="clickbtn">
                Prev Page <i class="fas fa-arrow-circle-up"></i>
                </a>
                
                <a href="#page-3" class="btn" @click="clickbtn">
                Next Page <i class="fas fa-arrow-circle-down"></i>
                </a>
            </div>
        </section>

        <!-- PAGE THREE -->
        <section id="page-3" class="page">
            <h1>Page 3</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat aliquid sed dolor, sunt possimus numquam deleniti repellendus ad adipisci cum natus molestiae consequatur deserunt porro beatae asperiores odio fuga quae quos debitis quaerat unde! Consequatur nulla dolore consectetur atque sapiente minus nobis recusandae exercitationem ad, illum, doloribus, fugiat laboriosam ratione.</p>  
            <div>
                <a href="#page-2" class="btn btn-dark" @click="clickbtn">
                Prev Page <i class="fas fa-arrow-circle-up"></i>
                </a>
                
                <a href="#page-4" class="btn" @click="clickbtn">
                Next Page <i class="fas fa-arrow-circle-down"></i>
                </a>
            </div>
        </section>

        <!-- PAGE FOUR -->
        <section id="page-4" class="page">
            <h1>Page 4</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat aliquid sed dolor, sunt possimus numquam deleniti repellendus ad adipisci cum natus molestiae consequatur deserunt porro beatae asperiores odio fuga quae quos debitis quaerat unde! Consequatur nulla dolore consectetur atque sapiente minus nobis recusandae exercitationem ad, illum, doloribus, fugiat laboriosam ratione.</p>  
            <div>
                <a href="#page-3" class="btn" @click="clickbtn">
                Prev Page <i class="fas fa-arrow-circle-up"></i>
                </a>
            </div>  
        </section>
    
    </div>
</template>

<script>

export default {
    name: 'getAbility2',
    methods: {
        clickbtn() {
            if (this.hash !== '') {
                event.preventDefault();
                const hash = this.hash;
                
                $('html, body').animate({
                scrollTop: $(hash).offset().top
                },800)
            }
        },
    }

}
</script>

<style>
:root {
  --page-1-color: steelblue;
  --page-2-color: tan;
  --page-3-color: teal;
  --page-4-color: slateblue;
  --animation-speed: 1.2s;
}

body {
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.4;
  color: #fff;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

/* PAGE COLORS */
#page-1 {
  background: var(--page-1-color);  
}

#page-2 {
  background: var(--page-2-color)
}
#page-3 {
  background: var(--page-3-color)
}
#page-4 {
  background: var(--page-4-color)
}

.page {
  display: flex;
  flex-direction: column;
  height: 100vh;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 0 4rem;
}

.page h1 {
  font-size: 4rem;
  line-height: 1.2;
  margin: 1rem;
}

.page p {
  font-size: 1.3rem;
}

.btn {
  display: inline-block;
  padding: 1rem 2rem;
  background: #f4f4f4;
  color: #333;
  text-decoration: none;
  border: none;
  margin-top: 1rem;
  font-size: 1.1rem;
  transition: all .3s ease-in;
}

.btn:hover,
.btn-dark {
  background: #333;
  color: #fff;
}

.btn-dark:hover {
  background: #f4f4f4;
  color: #333;
}

/* PAGE ANIMATION */

#page-1 h1 {
  transform: translateY(-1200px);
  animation: heading var(--animation-speed) forwards ease-in;
}

#page-1 p {
  transform: translateX(-1800px);
  animation: text var(--animation-speed) forwards ease-in 1s;
}

@keyframes heading {
  to {
    transform: translateY(0);
  }
}

@keyframes text {
  to {
    transform: translateY(0);
  }
}
</style>