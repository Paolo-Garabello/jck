<script lang="ts">
  import { onMount } from 'svelte';
  import Chat from "$lib/components/Chat.svelte";
  import { PUBLIC_WEBSOCKET_URL } from '$env/static/public';

  let websocket: WebSocket;

  onMount(() => {
    websocket = new WebSocket(PUBLIC_WEBSOCKET_URL);

    websocket.onopen = () => {
      console.log('WebSocket connection established');
    };

    websocket.onclose = () => {
      console.log('WebSocket connection closed.');
    }

    websocket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

  });
</script>

<main>
  <Chat
    title="Public Square"
    websocket={websocket}
    chat="public"
  />
</main>


<style>
  main {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100vw;
    height: 100vh;
  }
</style>
