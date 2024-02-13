<script lang="ts">
  import { onMount, getContext, setContext } from 'svelte';
  import type { Message } from '$lib/types/Message.d.ts'
  import MessageComponent from "./Message.svelte";

  export let websocket: WebSocket;

  let messages: Message[] = [];

  setContext('websocket', websocket);

  function handleMessage(event: MessageEvent) {
    const data = JSON.parse(event.data);

    if(data.username) {
      localStorage.setItem('public_username', data.username);
    } else {
      messages = [...messages, data];
    }

  }


  onMount(() => {
    if (websocket) {
      websocket.onmessage = handleMessage;
    }
  });

  $: {
    if (websocket) {
      websocket.onmessage = handleMessage;
    }
  }

</script>

<ul>
  {#each messages as message}
    <MessageComponent
      data={message}
    />
  {/each}
</ul>

<style lang="scss">
  ul {
    display: flex;
    height: 100%;
    flex-direction: column;
    overflow-x: hidden;
    overflow-y: scroll;
    padding-left: 0;
  }
</style>
