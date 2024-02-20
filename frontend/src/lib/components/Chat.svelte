<script lang="ts">
	import { fade } from "svelte/transition";
  import { onMount, setContext } from 'svelte';
  import { Shadow } from 'svelte-loading-spinners';
  import { getNotificationsContext } from 'svelte-notifications';

  import MessageList from "./MessageList.svelte";
  import SendMessage from "./SendMessage.svelte";

  export let title: string;
  export let chat: string;
  export let websocket: WebSocket;

  const { addNotification } = getNotificationsContext();

  let loading = true;
  let connected = false;

  function handleSocket() {
    loading = false;
    connected = true;

    websocket.onopen = () => {
      console.log(`Connected successfully to "${title}".`);
      connected = true;

      addNotification({
        text: 'Connected',
        type: 'success',
        position: 'bottom-right',
        removeAfter: 3000
      });
    };

    websocket.onerror = () => {
      console.log(`There was an error connecting to "${title}".`);
      connected = false;

      addNotification({
        text: 'Connection Error',
        type: 'error',
        position: 'bottom-right',
        removeAfter: 3000
      });
    };

    websocket.onclose = () => {
      console.log(`You've been disconnected from "${title}".`);
      connected = false;

      addNotification({
        text: 'Disconnected',
        type: 'warning',
        position: 'bottom-right',
        removeAfter: 2000
      });
    };
  }

  onMount(() => {
    if (websocket) {
      handleSocket();
    }
  });

  $: {
    if (websocket) {
      handleSocket();
    }
  }

</script>

<div class="wrapper" transition:fade>
  <h1>{title}</h1>

  <div class="messages">
    {#if !loading}
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
      active={!loading && connected}
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
