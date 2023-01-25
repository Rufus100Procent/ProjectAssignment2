<template>
  <footer class="mt-5">
    <div class="w-100 bg-secondary py-5 text-white text-left fw-bold">
      <div class="container py-5 px-0">
        <div class="row align-content-stretch">
          <div class="col-8 pe-4">
            <p> MENU </p>
            <hr class="my-4">
            <div class="d-flex w-100 justify-content-between">
              <div class="col-4">
                <router-link
                  to="/about"
                  class="text-decoration-none text-white"
                >
                  <p> ABOUT SALT </p>
                </router-link>
              </div>
              <div class="col-4">
                <p> FEMALE </p>
                <p> MALE </p>
              </div>
              <div class="col-4">
                <p> HATS </p>
                <p> JACKETS </p>
                <p> T-SHIRTS </p>
                <p> BAG </p>
              </div>
            </div>
          </div>
          <div class="col-4 ps-4">
            <p> FOLLOW US </p>
            <hr class="my-4">
            <p> FACEBOOK </p>
            <p> INSTAGRAM </p>
            <p> LINKEDIN </p>
          </div>
          <div class="col-8 pe-4">
            <hr class="my-4">
          </div>
          <div class="col-4 ps-4">
            <hr class="my-4">
          </div>
        </div>
      </div>
    </div>
    <div class="container py-4 d-flex justify-content-between px-0">
      <img
        alt=" campaign picture"
        src="../assets/images/salt.svg"
        style="width: 75px"
      >
      <p class="fw-semibold mb-0">
        &copy; COPYRIGHT SALT 2022
      </p>
    </div>
  </footer>
</template>
