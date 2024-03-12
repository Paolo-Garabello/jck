<script lang="ts">
  import Notifications from 'svelte-notifications';
  import Chat from './Chat.svelte';
	import type { PrivateMessage } from '$lib/types/PrivateMessage';

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
