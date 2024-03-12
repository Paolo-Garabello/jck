<script lang="ts">
	import PrivateChatsList from "$lib/components/PrivateChatsList.svelte";
  
  export let data;

  let websocket: WebSocket = data.websocket;
</script>

<main>
  <PrivateChatsList
    websocket={websocket}
  />
</main>
