<script lang="ts">
	import { fade } from "svelte/transition";
  import { onMount, setContext } from 'svelte';
  import { Shadow } from 'svelte-loading-spinners';
  import MessageList from "./MessageList.svelte";
  import SendMessage from "./SendMessage.svelte";

  export let title: string;
  export let chat: string;
  export let websocket: WebSocket;

  let connected: boolean = false;

  onMount(() => {
    if (websocket) {
      connected = true;
    }
  });

  $: {
    if (websocket) {
      connected = true;
    }
  }

</script>

<div class="wrapper" transition:fade>
  <h1>{title}</h1>

  <div class="messages">
    {#if connected}
      <MessageList
        websocket={websocket}
      />
    {:else}
      <div class="loader">
        <Shadow />
      </div>
    {/if}
  </div>

  <div class="send-message">
    <SendMessage
      websocket={websocket}
      chat={chat}
    />
  </div>

</div>


<style lang="scss">
  .wrapper {
    display: flex;
    align-items: center;
    flex-direction: column;
    border: 2px solid var(--border);
    border-radius: 10px;
    width: calc(100% - 20vw);
    max-width: 700px;
    height: 90vh;
    margin: 50px;

    h1 {
      font-size: clamp(20px, 5vw, 40px);
      text-align: center;
    }

    .messages {
      height: 70vh;
      width: 95%;
      position: relative;

      .loader {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
      }
    }

    .send-message {
      width: 100%;
    }
  }
</style>
