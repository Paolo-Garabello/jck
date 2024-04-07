<template>
  <div>
    <QRCodeVue :value="link" :size="40"></QRCodeVue>
  </div>
</template>

<script>
import QRCodeVue from 'vue-qrcode';

export default {
  components: {
    QRCodeVue,
  },
  props: {
    link: {
      type: String,
      required: true
    }
  }
};
</script>
