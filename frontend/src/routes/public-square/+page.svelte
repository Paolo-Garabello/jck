<script lang="ts">
  import Chat from "$lib/components/ChatWrapper.svelte";

  export let data;

  let websocket: WebSocket = data.websocket;
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
