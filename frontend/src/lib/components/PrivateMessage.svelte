<script lang="ts">
	import type { PrivateMessage } from '$lib/types/PrivateMessage';
	import type { Message } from '$lib/types/Message';
	import MessageComponent from './Message.svelte';

  export let data: PrivateMessage;
  export let myUsername: string;

  let msgData: Message = {
    message: data.message,
    name: "@" + data.username,
    chat: data.chat,
    date: data.date
  };
</script>


<MessageComponent
  data={msgData}
  myUsername={"@" + myUsername}
/>
