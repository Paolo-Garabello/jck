<script lang="ts">
  import type { SquareSectionBlocker } from "$lib/types/SquareSectionBlocker";

  export let title: string;
  export let description: string;
  export let link: string;
  export let blocker: SquareSectionBlocker | null = null;
</script>

<a href="{blocker ? blocker.url : link}" class="wrapper">
  <section>
      <div class:blur={blocker}>
      <h2>{title}</h2>
      <p>{description}</p>
    </div>

    {#if blocker}
      <p class="blocker">{blocker.displayString}</p>
    {/if}
  </section>
</a>

<style lang="scss">
  .wrapper {
    text-decoration: none;
    color: var(--copy);

    section {
      background-color: var(--secondary);
      padding: 20px;
      border-radius: 20px;
      position: relative;
      text-align: center;

      .blur {
        filter: blur(2.5px);
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
  }
</style>
