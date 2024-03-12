<script lang="ts">
  import type { PrivateMessage } from '$lib/types/PrivateMessage.d.ts'

  export let userID: number;
  export let data: PrivateMessage;

  function formatDate(timestamp: number) {
    const date = new Date(timestamp);
    return `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`;
  }

  let chatID: number;
  let chatName: string;

  if(userID === data.recipient) {
    chatID = data.sender;
    chatName = data.username;
  } else {
    chatID = data.recipient;
    chatName = data.recipientUsername;
  }

</script>


<a href="/dm/{chatID}">
  <li>
    <strong>@{chatName}:</strong> {data.message} - {formatDate(data.date)}
  </li>
</a>

<style lang="scss">
  a {
    margin: 2em;
    color: var(--primary-content);

    li {
      padding: 2em;
      list-style: none;
      background-color: var(--primary-dark);
      border-radius: 15px;
    }
  }
</style>
