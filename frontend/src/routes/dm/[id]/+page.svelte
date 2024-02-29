<script lang="ts">
  import Chat from "$lib/components/ChatWrapper.svelte";
  import EnableChat from "$lib/components/EnableChat.svelte";

  export let data;

  let websocket: WebSocket = data.websocket;
</script>

<main>

  <EnableChat
    title="Public Square"
    websocket={websocket}
    chat="public"
  />

  <Chat
    title={data.id}
    websocket={websocket}
    chat="@{data.id}"
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
