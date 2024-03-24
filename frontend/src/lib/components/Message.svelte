<script lang="ts">
	import type { PublicMessage } from '$lib/types/PublicMessage';
	import { slide } from 'svelte/transition';

  export let data: PublicMessage;
  export let myUsername: string | null = null;

  let MyUsername: string|null = myUsername ?? sessionStorage.getItem('public_username');

  function getFormattedDate(unix_timestamp: number) {
    let date = new Date(unix_timestamp);
    let minutes = "0" + date.getMinutes();
    return date.getHours() + ':' + minutes.substring(minutes.length - 2);
  }

</script>

{#if data.message}
  <li
    data-sender={data.name}
    data-date={getFormattedDate(data.date)}
    class:me={MyUsername === data.name}
    transition:slide={{
      axis: "x",
      duration: 300
    }}
  >
    {#each data.message.split('\n') as line}
      <div>{line}</div>
    {/each}
  </li>
{/if}

<style lang="scss">
  li {
    list-style: none;
    padding: 20px 10px;
    border-radius: 20px;
    margin: 5px 0;
    width: fit-content;
    min-width: 100px;
    max-width: 40%;
    background-color: var(--primary-light);
    color: var(--primary-content);
    position: relative;
    word-wrap: break-word;
    line-height: 1.2em;
  }

  li:before {
    content: attr(data-sender);
    font-weight: bold;
    font-size: small;
    position: absolute;
    top: 0;
  }

  li::after {
    content: attr(data-date);
    font-weight: lighter;
    font-size: smaller;
    position: absolute;
    bottom: 0;
    right: 10px;
  }

  li.me {
    align-self: flex-end;
    background-color: var(--primary-dark);
  }
</style>
