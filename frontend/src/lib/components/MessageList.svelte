<script lang="ts">
  import { onMount, getContext, setContext } from 'svelte';
  import type { Message } from '$lib/types/Message.d.ts'
  import MessageComponent from "./Message.svelte";

  let messages: Message[] = [];
  export let websocket: WebSocket;

  setContext('websocket', websocket);

  function handleMessage(event: MessageEvent) {
    const newMessageData = JSON.parse(event.data);
    messages = [...messages, newMessageData];
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
    margin: 0 10px;
  }
</style>
