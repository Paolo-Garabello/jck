<script lang="ts">
  import Chat from './ChatWrapper.svelte'

  export let title: string;
  export let websocket: WebSocket;
  export let chat: string;

  let enabled: boolean = false;

  function displayChat() {
    enabled = !enabled;
  }
</script>


{#if !enabled}
  <button on:click={displayChat}>Enable {title}</button>
{:else}
  <Chat
    title={title}
    websocket={websocket}
    chat={chat}
  />
{/if}



<style lang="scss">
  button {
    padding: 10px;
    border-radius: 16px;
    border: 2px solid var(--border);
    background-color: var(--primary-light);
    color: var(--secondary-content);

    transition: all .5s;

    &:hover {
      background-color: var(--primary-dark);
      color: var(--copy);
    }
  }
</style>
