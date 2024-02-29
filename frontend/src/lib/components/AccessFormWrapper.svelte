<script lang="ts">
  import Notifications from 'svelte-notifications';
  import AccessForm from './AccessForm.svelte';

  export let requestType: string;
  export let goToUrl: string;
  export let goToText: string;
  export let websocket: WebSocket;
  export let onSuccess: Function;
</script>

<Notifications>
  <AccessForm
    requestType={requestType}
    goToUrl={goToUrl}
    goToText={goToText}
    websocket={websocket}
    onSuccess={onSuccess}
  />
</Notifications>
