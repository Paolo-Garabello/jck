<script lang="ts">
	import type { PrivateMessage } from '$lib/types/PrivateMessage';
	import type { PublicMessage } from '$lib/types/PublicMessage';
	import MessageComponent from './Message.svelte';

  export let data: PrivateMessage;
  export let myUsername: string;

  let msgData: PublicMessage = {
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
