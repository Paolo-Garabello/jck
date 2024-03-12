<script lang="ts">
  import { derived, writable } from 'svelte/store';
  import type { PrivateMessage } from '$lib/types/PrivateMessage.d.ts';
  import UserChat from './UserChat.svelte';
	import { goto } from '$app/navigation';

  export let websocket: WebSocket;

  const messages = writable<PrivateMessage[]>([]);

  const userID: number = parseInt(localStorage.getItem('user.id') || '-1', 10);

  if(userID == -1) goto('/login');

  const chats = derived(messages, ($messages) => {
    return $messages.filter((msg, index, self) => {
      if (userID === msg.recipient) {
        return self.findLastIndex(m => m.sender === msg.sender || (m.recipient == msg.sender && m.sender === msg.recipient)) === index;
      } else {
        return self.findLastIndex(m => m.recipient === msg.recipient) === index;
      }
    });
  });

  websocket.addEventListener('message', (event) => {
    let msg = sessionStorage.getItem('websocket.message');
    if(!msg) return;

    let data = JSON.parse(msg);
    if(data.statusCode === 206) {
      messages.update(existingMessages => [...existingMessages, ...data.messages.messages]);

      const messagesValue = messages.subscribe(value => {
        localStorage.setItem('dmMessages', JSON.stringify(value));
      });
    }
  });


  function sendWebSocketRequest() {
    if (websocket.readyState === WebSocket.OPEN) {
      websocket.send(JSON.stringify({ request: 'getChats' }));
    }
  }

  if (websocket.readyState === WebSocket.OPEN) {
    sendWebSocketRequest();
  } else {
    websocket.addEventListener('open', sendWebSocketRequest);
  }
</script>

<h1>Private Chats</h1>

{#if $messages}
  <ul>
    {#each $chats.slice().reverse() as msg}
      <UserChat
        userID={userID}
        data={msg}
      />
    {/each}
  </ul>
{/if}

<style lang="scss">
  ul {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
</style>
