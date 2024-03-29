<script lang="ts">
	import PrivateChatsList from "$lib/components/PrivateChatsList.svelte";

  export let data;

  let websocket: WebSocket = data.websocket;
</script>

<main>
  <h1>Private Chats</h1>

  <PrivateChatsList
    websocket={websocket}
  />
</main>


<style lang="scss">
  main {
    display: flex;
    flex-direction: column;

    h1 {
      text-align: center;
    }
  }
</style>
