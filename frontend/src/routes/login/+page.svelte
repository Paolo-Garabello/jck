<script lang="ts">
  import AccessForm from '$lib/components/AccessFormWrapper.svelte';

  export let data;
  let websocket: WebSocket = data.websocket;
</script>

<AccessForm
  requestType="login"
  goToUrl="/signup"
  goToText="Don't have an account? Signup"
  websocket={websocket}
/>
