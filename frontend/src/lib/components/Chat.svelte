<script lang="ts">
  import MessageList from "./MessageList.svelte";
  import SendMessage from "./SendMessage.svelte";

  export let title: string;
  export let chat: string;
  export let websocket: WebSocket;
</script>

<div class="wrapper">
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
    height: 90vh;
    text-align: center;
    margin: 50px;

    h1 {
      font-size: clamp(20px, 5vw, 40px);
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
