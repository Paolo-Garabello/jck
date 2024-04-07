<script lang="ts">
  import { derived, writable } from 'svelte/store';
  import type { PrivateMessage } from '$lib/types/PrivateMessage.d.ts';
  import UserChat from './UserChat.svelte';
	import { goto } from '$app/navigation';

  export let websocket: WebSocket;

  const messages = writable<PrivateMessage[]>([]);

  let prevMessages: PrivateMessage[] = JSON.parse(localStorage.getItem('dmMessages') ?? '[]');

  let highestId: number | null = null;

  if(prevMessages) {
    highestId = prevMessages.length === 0 ? null : prevMessages.reduce((prev, curr) => (curr.id > prev.id ? curr : prev)).id;
    messages.update(existingMessages => [...existingMessages, ...prevMessages]);
  }


  const user: string|null = localStorage.getItem('user');

  if(!user) goto('/login');

  let userData = JSON.parse(user ?? "");

  let userID = userData.id;

  const chats = derived(messages, ($messages) => {
    return $messages.filter((msg, index, self) => {
      return self.findLastIndex(m =>
        (m.sender === msg.sender && m.recipient === msg.recipient) ||
        (m.sender === msg.recipient && m.recipient === msg.sender)
      ) === index;
    });
  });

  websocket.addEventListener('message', (event) => {
    let msg = sessionStorage.getItem('websocket.message');
    if(!msg) return;

    let data = JSON.parse(msg);
    if(data.statusCode === 206) {
      messages.update(existingMessages => [...existingMessages, ...data.data.messages]);

      messages.subscribe(value => {
        localStorage.setItem('dmMessages', JSON.stringify(value));
      });
    }
  });

  function sendWebSocketRequest() {
    websocket.send(JSON.stringify({
      request: 'getChats',
      data: highestId
    }));
  }

  if (websocket.readyState === WebSocket.OPEN) {
    sendWebSocketRequest();
  } else {
    websocket.addEventListener('open', sendWebSocketRequest);
  }

  function getRandomUserID() {
    return userID + (userID % 2 === 0 ? -1 : +1);
  }
</script>

<div>
  {#if $messages.length > 0}
    <ul>
      {#each $chats.slice().reverse() as msg}
        <UserChat
          userID={userID}
          data={msg}
        />
      {/each}
    </ul>

  {:else}
    <div class="not-found">
      <h3>No chats where found</h3>
      <a href="/dm/{getRandomUserID()}">Click here to Start a Conversation with a random user</a>
    </div>
  {/if}
</div>

<style lang="scss">
  div {
    ul {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .not-found {
      text-align: center;

      a {
        color: var(--copy);
      }
    }
  }
</style>
