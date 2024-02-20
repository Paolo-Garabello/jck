<script lang="ts">
  export let websocket: WebSocket;
  export let chat: string;
  export let active: boolean;

  let message: string = "";

  function sendMessage() {
    if(!active) return;

    if (message.trim() !== '') {
      websocket.send(JSON.stringify({
        request: "sendMessage",
        content: {
          message: message,
          chat: chat
        }
      }));

      message = '';
    }
  }

  function handleKeyDown(event: KeyboardEvent) {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      sendMessage();
    }
  }
</script>

<div class="send-message">
  <textarea disabled={!active} bind:value={message} id="public-message-input" placeholder="Type your message..." on:keydown={handleKeyDown}></textarea>
  <button id="public-send-button" on:click={sendMessage}>Send</button>
</div>

<style lang="scss">
  .send-message {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 90%;
    margin: 20px;

    textarea {
      font-family: inherit;
      width: clamp(10px, 30vw, 1000px);
      padding: 10px;
      background-color: var(--secondary-content);
      color: var(--copy);
      font-size: 16px;
      border: 2px solid var(--secondary-dark);
      border-radius: 5px;
      resize: unset;
      margin-right: 10px;
    }

    button {
      padding: 10px 20px;
      font-size: 16px;
      background-color: var(--primary);
      color: var(--copy);
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    button:hover {
      background-color: var(--primary-dark);
    }
  }
</style>
