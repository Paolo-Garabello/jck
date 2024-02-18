<script lang="ts">
  import Notifications from 'svelte-notifications';
  import Chat from './Chat.svelte';

  export let title: string;
  export let chat: string;
  export let websocket: WebSocket;
</script>

<Notifications>
  <Chat
    title={title}
    chat={chat}
    websocket={websocket}
  />
</Notifications>
