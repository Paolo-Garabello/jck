<script lang="ts">
  import MessageList from "./MessageList.svelte";
  import SendMessage from "./SendMessage.svelte";

  export let title: string;
  export let chatType: string;
  export let websocket: WebSocket;
</script>

<div class="wrapper">
  <h1>{title}</h1>

  <div class="messages">
    <MessageList
      websocket={websocket}
    />
  </div>

  <SendMessage
    websocket={websocket}
    chatType={chatType}
  />

</div>


<style lang="scss">
  .wrapper {
    display: flex;
    align-items: center;
    flex-direction: column;
    border: 2px solid var(--border);
    border-radius: 10px;
    width: 50vw;
    min-height: 90vh;

    .messages {
      height: 70vh;
      width: 95%;
    }
  }
</style>
