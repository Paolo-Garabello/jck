<script lang="ts">
  import AccessForm from '$lib/components/AccessFormWrapper.svelte';

  export let data;
  let websocket: WebSocket = data.websocket;
</script>

<AccessForm
  requestType="signup"
  goToUrl="/login"
  goToText="Already have an account? Login"
  websocket={websocket}
/>
