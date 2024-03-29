<script lang="ts">
  import Chat from "$lib/components/ChatWrapper.svelte";
  import EnableChat from "$lib/components/EnableChat.svelte";
  import type { PrivateMessage } from '$lib/types/PrivateMessage.d.ts';

  export let data;

  const dmMessages: PrivateMessage[] = JSON.parse(localStorage.getItem('dmMessages') ?? '[]');

  function getUsernameFromId(id: number): string {
    return dmMessages.find(m => m.id === id)?.username ?? "id: " + id.toString();
  }

  let websocket: WebSocket = data.websocket;
</script>

<main>

  <EnableChat
    title="Public Square"
    websocket={websocket}
    chat="public"
  />

  <Chat
    title={getUsernameFromId(parseInt(data.id))}
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
