<script lang="ts">
  import { onMount, afterUpdate } from 'svelte';
  import type { PrivateMessage } from '$lib/types/PrivateMessage';
  import MessageComponent from "./Message.svelte";
  import PrivateMessageComponent from './PrivateMessage.svelte';
	import { goto } from '$app/navigation';
	import type { WebSocketResponse } from '$lib/types/WebSocketResponse';
	import type { PublicMessage } from '$lib/types/PublicMessage';

  export let websocket: WebSocket;
  export let chatType: string;
  export let previousMessages: PrivateMessage[] | null = null;

  let messages: (PublicMessage | PrivateMessage)[] = previousMessages ?? [];

  let userData = localStorage.getItem('user');

  if(!userData && chatType === 'DM') goto('/login');

  let user = JSON.parse(userData ?? '{}');

  websocket.addEventListener('message', () => {
    let msg = sessionStorage.getItem('websocket.message');
    if(!msg) return;

    const wsdata: WebSocketResponse = JSON.parse(msg);

    if((wsdata.statusCode === 202 || wsdata.statusCode === 206) && wsdata.data.chat === chatType) {
      messages = [...messages, wsdata.data];
      if(chatType === 'DM' && wsdata.statusCode === 202) {
        let prev = JSON.parse(localStorage.getItem('dmMessages') ?? '[]');
        prev.push(wsdata.data);
        localStorage.setItem('dmMessages', JSON.stringify(prev));
      }
      sessionStorage.removeItem('websocket.message');
    }
  });

  let listElement: HTMLElement;
  const scrollToBottom = () => {
    listElement.scrollTop = listElement.scrollHeight;
  };

  onMount(() => scrollToBottom());
  afterUpdate(() => scrollToBottom());

  function toPrivateMessage(msg: PublicMessage | PrivateMessage): PrivateMessage {
    return <PrivateMessage>msg;
  }

  function toPublicMessage(msg: PublicMessage | PrivateMessage): PublicMessage {
    return <PublicMessage>msg;
  }
</script>

<ul bind:this={listElement}>
  {#each messages as message}

    {#if message.chat === "public"}
      <MessageComponent
        data={toPublicMessage(message)}
      />
    {:else}
      <PrivateMessageComponent
        data={toPrivateMessage(message)}
        myUsername={user.username}
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
