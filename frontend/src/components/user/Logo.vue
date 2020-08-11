<template>
    <div class=" d-flex flex-column align-items-center">
        <div>
            <img src="../../assets/images/LogoImage.png">
        </div>
        <h5 class="my-5" style="color:#464545">
            SW 개발자를 위한 SNS
        </h5>
    </div>
    
</template>

<script>
import LogoImage from "../../assets/images/LogoImage.png";
export default {
    name: "Logo",
    data: () => {
        return { LogoImage };
    }
}
</script>


