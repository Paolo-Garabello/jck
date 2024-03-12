<script lang="ts">
  import { onMount, afterUpdate } from 'svelte';

  import type { PrivateMessage } from '$lib/types/PrivateMessage';
  import type { Message } from '$lib/types/Message';

  import MessageComponent from "./Message.svelte";
  import PrivateMessageComponent from './PrivateMessage.svelte';
	import { goto } from '$app/navigation';

  export let websocket: WebSocket;
  export let chatType: string;
  export let previousMessages: PrivateMessage[] | null = null;

  let messages: (Message | PrivateMessage)[] = previousMessages ?? [];

  let myUsername = localStorage.getItem('user.username') ?? "";

  if(!myUsername) goto('/login');

  websocket.addEventListener('message', () => {
    let msg = sessionStorage.getItem('websocket.message');
    if(!msg) return;

    const data = JSON.parse(msg);

    if(data.chat && data.chat == chatType) {
      messages = [...messages, data];
      sessionStorage.removeItem('websocket.message');
    }
  });

  let listElement: HTMLElement;
  const scrollToBottom = () => {
    listElement.scrollTop = listElement.scrollHeight;
  };

  onMount(() => scrollToBottom());
  afterUpdate(() => scrollToBottom());
</script>

<ul bind:this={listElement}>
  {#each messages as message}

    {#if message.chat === "public"}
      <MessageComponent
        data={message}
      />
    {:else}
    <PrivateMessageComponent
        data={message}
        myUsername={myUsername}
      />
    {/if}

  {/each}
</ul>


<style lang="scss">
  ul {
    display: flex;
    height: 100%;
    flex-direction: column;
    overflow-x: hidden;
    overflow-y: scroll;
    padding-left: 0;
  }
</style>
