<script lang="ts">
  import type { SquareSectionBlocker } from "$lib/types/SquareSectionBlocker";

  export let title: string;
  export let description: string;
  export let link: string;
  export let blocker: SquareSectionBlocker | null = null;
</script>

<section>
  <a href="{link}">
    <div class:blur={blocker}>
      <h2>{title}</h2>
      <p>{description}</p>
    </div>

    {#if blocker}
      {#if blocker.url}
        <a class="blocker" href="{blocker.url}">{blocker.displayString}</a>
      {:else}
        <p class="blocker">{blocker.displayString}</p>
      {/if}
    {/if}
  </a>
</section>

<style lang="scss">
  section {
    background-color: var(--secondary);
    padding: 20px;
    border-radius: 20px;
    position: relative;
    text-align: center;

    .blur {
      filter: blur(2.5px);
    }

    a {
      text-decoration: none;
      color: var(--copy);
    }

    h2 {
      font-size: xx-large;
    }

    .blocker {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: var(--copy);
      background-color: var(--primary-dark);
      padding: 5px;
      border-radius: 10px;
    }

  }
</style>
