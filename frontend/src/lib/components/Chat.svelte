<script lang="ts">
	import { fade } from "svelte/transition";
  import MessageList from "./MessageList.svelte";
  import SendMessage from "./SendMessage.svelte";

  export let title: string;
  export let chat: string;
  export let websocket: WebSocket;
</script>

<div class="wrapper" transition:fade>
  <h1>{title}</h1>

  <div class="messages">
    <MessageList
      websocket={websocket}
    />
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
    }

    .send-message {
      width: 100%;
    }
  }
</style>
