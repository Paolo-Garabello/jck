<script lang="ts">
	import { fade } from "svelte/transition";
  import { getNotificationsContext } from 'svelte-notifications';
	import type { PrivateMessage } from '$lib/types/PrivateMessage';

  import MessageList from "./MessageList.svelte";
  import SendMessage from "./SendMessage.svelte";

  export let title: string;
  export let chat: string;
  export let websocket: WebSocket;
  export let previousMessages: PrivateMessage[] | null = JSON.parse(localStorage.getItem('dmMessages') ?? '[]');

  const { addNotification } = getNotificationsContext();

  let chatType = chat == 'public' ? chat : 'DM';

  let connected = false;

  function wsOpen() {
    console.log(`Connected successfully to "${title}".`);
    connected = true;

    addNotification({
      text: 'Connected',
      type: 'success',
      position: 'bottom-right',
      removeAfter: 3000
    });
  }

  websocket.readyState === WebSocket.OPEN ? wsOpen() : websocket.addEventListener('open', wsOpen);

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
</script>

<div class="wrapper" transition:fade>
  <h1>{title}</h1>

  <div class="messages">
    <MessageList
      websocket={websocket}
      chatType={chatType}
      previousMessages={chatType === 'DM' ? previousMessages : null}
    />
  </div>

  <div class="send-message">
    <SendMessage
      websocket={websocket}
      chat={chat}
      active={connected}
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
    }

    .send-message {
      width: 100%;
    }
  }
</style>
