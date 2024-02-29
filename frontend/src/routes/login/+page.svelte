<script lang="ts">
  import AccessForm from '$lib/components/AccessFormWrapper.svelte';

  export let data;
  let websocket: WebSocket = data.websocket;

  function onSuccess() {
    localStorage.setItem('logged', true);
  }
</script>

<AccessForm
  requestType="login"
  goToUrl="/signup"
  goToText="Don't have an account? Signup"
  websocket={websocket}
  onSuccess={onSuccess}
/>
